package com.chainerist.blockchain.manager.airconnect.service;

import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Passenger;
import com.chainerist.blockchain.manager.airconnect.entity.AirlineEntity;
import com.chainerist.blockchain.manager.airconnect.entity.PassengerEntity;
import com.chainerist.blockchain.manager.airconnect.error.ErrorCodes;
import com.chainerist.blockchain.manager.airconnect.mapper.PassengerMapper;
import com.chainerist.blockchain.manager.airconnect.repository.PassengerRepository;
import com.chainerist.blockchain.manager.util.exception.ChaineristException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class PassengerService {

    private PassengerRepository passengerRepository;

    private PassengerMapper passengerMapper;

    public PassengerService(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    public ChaineristPageable<Passenger> findAll(int page, int size){
        Page<PassengerEntity> passengers = passengerRepository.findAll(PageRequest.of(page, size));
        return new ChaineristPageable<Passenger>(passengers.getTotalElements(),passengers.getTotalPages(),passengers.getPageable(),passengerMapper.toListDomainObject(passengers.getContent()));
    }

    public Passenger findById(String id) {
        return passengerRepository.findById(id).map(passengerMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.DATA_NOT_FOUND));
    }

    public Passenger createPassenger(Passenger domainObject) {
        this.validatePassenger(domainObject);
        return saveOrUpdate(domainObject);
    }

    @Transactional
    public ChaineristPageable<Passenger> findBySearchCriteria(Specification<PassengerEntity> spec, Pageable page){
        Page<PassengerEntity> passengers = passengerRepository.findAll(spec, page);
        return new ChaineristPageable<Passenger>(passengers.getTotalElements(), passengers.getTotalPages(), passengers.getPageable(),passengerMapper.toListDomainObject(passengers.getContent()));
    }

    private Passenger saveOrUpdate(Passenger domainObject) {
        return passengerMapper.toDomainObject(passengerRepository.save(passengerMapper.toEntity(domainObject)));
    }

    private void validatePassenger(Passenger domainObject) {

        if (Objects.isNull(domainObject)) {
            throw new ChaineristException(ErrorCodes.PASSENGER_DATA_CAN_NOT_BE_EMPTY);
        }

        if (StringUtils.isEmpty(domainObject.getIdentifier())) {
            throw new ChaineristException(ErrorCodes.PASSENGER_IDENTIFIER_CAN_NOT_BE_EMPTY);
        }
        if (!StringUtils.isEmpty(domainObject.getIdentifier())) {
            this.uniquenessPassenger(domainObject.getIdentifier());
        }

        if (StringUtils.isEmpty(domainObject.getFirstName()) || StringUtils.isEmpty(domainObject.getLastName())) {
            throw new ChaineristException(ErrorCodes.PASSENGER_FIRSTNAME_OR_LASTNAME_CAN_NOT_BE_EMPTY);
        }
    }

    private void uniquenessPassenger(String identifier) {
        Optional<PassengerEntity> passengerOpt = passengerRepository.findByIdentifier(identifier);
        if (passengerOpt.isPresent()) {
            throw new ChaineristException(ErrorCodes.PASSENGER_ALREADY_EXISTS);

        }
    }

    public List<Passenger> findByFirstName(String firstName) {
        List<PassengerEntity> passList = passengerRepository.findByFirstName(firstName);
        return passengerMapper.toListDomainObject(passList);
    }
}
