package com.chainerist.blockchain.manager.authentication.service;


import com.chainerist.blockchain.manager.airconnect.domain.Airline;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.entity.AirlineEntity;
import com.chainerist.blockchain.manager.airconnect.entity.PassengerEntity;
import com.chainerist.blockchain.manager.authentication.domain.User;
import com.chainerist.blockchain.manager.authentication.entity.UserEntity;
import com.chainerist.blockchain.manager.authentication.error.ErrorCodes;
import com.chainerist.blockchain.manager.authentication.mapper.UserMapper;
import com.chainerist.blockchain.manager.authentication.repository.UserRepository;
import com.chainerist.blockchain.manager.util.exception.ChaineristException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

@Service
public class UserService {

    private UserMapper userMapper;

    private UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.THIS_USER_DOES_NOT_EXIST));
    }

    public User findOne(String username) {
        return userRepository.findByUsername(username).map(userMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.ACCESS_DENIED));
    }

    public ChaineristPageable<User> findAll(int page, int size){
        Page<UserEntity> users = userRepository.findAll(PageRequest.of(page, size));
        return new ChaineristPageable<User>(users.getTotalElements(),users.getTotalPages(),users.getPageable(),userMapper.toListDomainObject(users.getContent()));
    }

    public User saveOrUpdate(User user) {
        this.uniquenessUser(user.getUsername());
        UserEntity userEntity = userMapper.toEntity(user);
        if (!CollectionUtils.isEmpty(userEntity.getWorkingUnits())) {
            userEntity.getWorkingUnits().forEach(u -> u.setUser(userEntity));
        }

        return userMapper.toDomainObject(userRepository.save(userEntity));
    }



    private void uniquenessUser(String username) {
        Optional<UserEntity> usernameOpt = userRepository.findByUsername(username);
        if (usernameOpt.isPresent()) {
            throw new ChaineristException(ErrorCodes.THIS_USERNAME_HAS_TAKEN_BEFORE);

        }
    }
}
