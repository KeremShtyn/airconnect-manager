package com.chainerist.blockchain.manager.authentication.controller;

import com.chainerist.blockchain.manager.airconnect.domain.Airline;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Ticket;
import com.chainerist.blockchain.manager.authentication.api.AuthAPI;
import com.chainerist.blockchain.manager.authentication.domain.User;
import com.chainerist.blockchain.manager.authentication.domain.WorkingUnit;
import com.chainerist.blockchain.manager.authentication.dto.*;
import com.chainerist.blockchain.manager.authentication.entity.WorkingUnitEntity;
import com.chainerist.blockchain.manager.authentication.mapper.UserDTOMapper;
import com.chainerist.blockchain.manager.authentication.mapper.UserDataDTOMapper;
import com.chainerist.blockchain.manager.authentication.mapper.UserMapper;
import com.chainerist.blockchain.manager.authentication.mapper.WorkingUnitDTOMapper;
import com.chainerist.blockchain.manager.authentication.service.UserService;
import com.chainerist.blockchain.manager.authentication.service.WorkingUnitService;
import com.chainerist.blockchain.manager.security.JwtTokenUtil;
import com.chainerist.blockchain.manager.util.AuthenticationUtil;
import com.chainerist.blockchain.manager.util.response.ChaineristGenerator;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class AuthController implements AuthAPI {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDTOMapper userDTOMapper;

    private final WorkingUnitService workingUnitService;

    private final UserDataDTOMapper userDataDTOMapper;


    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtTokenUtil jwtTokenUtil, UserDTOMapper userDTOMapper, WorkingUnitService workingUnitService, UserDataDTOMapper userDataDTOMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDTOMapper = userDTOMapper;
        this.workingUnitService = workingUnitService;
        this.userDataDTOMapper = userDataDTOMapper;
    }

    @Override
    public ChaineristResponse<Object> token(TokenRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userService.findOne(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        final UserDataDTO userData = userDataDTOMapper.toDTO(user);
        TokenResponseDTO response = new TokenResponseDTO();
        response.setAccessToken(token);
        response.setUserData(userData);


        return ChaineristGenerator.generateSignResponse(response);
    }

    @Override
    public ChaineristResponse<Object> saveUser(UserDTO userDTO) {

        User domainObject = userDTOMapper.toDomain(userDTO);
        domainObject.setPassword(new BCryptPasswordEncoder().encode(domainObject.getPassword()));
        WorkingUnit workingUnitObject = new WorkingUnit();
        workingUnitObject.setRole(domainObject.getRole());
        workingUnitObject.setWorkingType(domainObject.getRole().toString());
        workingUnitObject.setUserId(domainObject.getId());
        domainObject.setWorkingUnits(Set.of(workingUnitObject));
        domainObject = userService.saveOrUpdate(domainObject);
        workingUnitObject = workingUnitService.findOne(domainObject.getWorkingUnits().stream().findFirst().get().getId());
        workingUnitObject.setWorkingId(domainObject.getId());
        workingUnitService.saveOrUpdate(workingUnitObject);

        return ChaineristGenerator.generateSignResponse(userDTOMapper.toDTO(userService.findOne(domainObject.getUsername())));
    }

    @Override
    public ChaineristResponse<Object> update(UserDTO userDTO) {
        return saveUser(userDTO);
    }

    @Override
    public ChaineristResponse<Object> getByUsername(String username) {
        User user = userService.findByUsername(username);
        return ChaineristGenerator.generateSignResponse(userDTOMapper.toDTO(user));
    }

    @Override
    public ChaineristResponse<Object> getPage(Map<String, Object> header, int page, int size, String[] sortBy) {
        ChaineristPageable<User> userPage = userService.findAll(page, size);
        return ChaineristGenerator.generateSignResponse(userPage);
    }

}
