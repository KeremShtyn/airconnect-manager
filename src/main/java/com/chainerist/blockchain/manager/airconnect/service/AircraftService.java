package com.chainerist.blockchain.manager.airconnect.service;


import com.chainerist.blockchain.manager.airconnect.domain.*;
import com.chainerist.blockchain.manager.airconnect.entity.AircraftEntity;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.airconnect.error.ErrorCodes;
import com.chainerist.blockchain.manager.airconnect.mapper.AircraftMapper;
import com.chainerist.blockchain.manager.airconnect.repository.AircraftRepository;
import com.chainerist.blockchain.manager.util.exception.ChaineristException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AircraftService {

    private AircraftRepository aircraftRepository;
    private AircraftMapper aircraftMapper;

    public AircraftService(AircraftRepository aircraftRepository, AircraftMapper aircraftMapper){
        this.aircraftMapper = aircraftMapper;
        this.aircraftRepository = aircraftRepository;
    }

    public ChaineristPageable<Aircraft> findAll(int page, int size){
        Page<AircraftEntity> aircrafts = aircraftRepository.findAll(PageRequest.of(page, size));
        return new ChaineristPageable<Aircraft>(aircrafts.getTotalElements(),aircrafts.getTotalPages(),aircrafts.getPageable(),aircraftMapper.toListDomainObject(aircrafts.getContent()));
    }

    @Transactional
    public ChaineristPageable<Aircraft> findBySearchCriteria(Specification<AircraftEntity> spec, Pageable page){
        Page<AircraftEntity> searchResult = aircraftRepository.findAll(spec, page);
        return new ChaineristPageable<Aircraft>(searchResult.getTotalElements(),searchResult.getTotalPages(),searchResult.getPageable(),aircraftMapper.toListDomainObject(searchResult.getContent()));
    }

    public Aircraft findByLegNo(String legNo){
        return aircraftRepository.findByLegNo(legNo).map(aircraftMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.DATA_NOT_FOUND));
    }



    private Aircraft save(Aircraft domainObject){
        return aircraftMapper.toDomainObject(aircraftRepository.save(aircraftMapper.toEntity(domainObject)));
    }

    public Aircraft createAircraft(Aircraft domainObject){
        this.validateAircraft(domainObject);
        return save(domainObject);
    }

    private void validateAircraft(Aircraft domainObject){
        if(Objects.isNull(domainObject)){
            throw new ChaineristException(ErrorCodes.AIRCRAFT_DATA_CAN_NOT_BE_EMPTY);
        }
        if(StringUtils.isEmpty(domainObject.getLegNo())){
            throw new ChaineristException(ErrorCodes.AIRCRAFT_LEG_NO_CAN_NOT_BE_EMPTY);
        }
        if(StringUtils.isEmpty(domainObject.getType())){
            throw new ChaineristException(ErrorCodes.AIRCRAFT_TYPE_CAN_NOT_BE_EMPTY);
        }
    }

    private void uniqueness(String legNo){
        Optional<AircraftEntity> object = aircraftRepository.findByLegNo(legNo);
        if(object.isPresent()){
            throw new ChaineristException(ErrorCodes.AIRCRAFT_ALREADY_EXISTS);
        }
    }

}
