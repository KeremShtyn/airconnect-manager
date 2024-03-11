package com.chainerist.blockchain.manager.airconnect.service;


import com.chainerist.blockchain.manager.airconnect.domain.*;
import com.chainerist.blockchain.manager.airconnect.entity.AircraftEntity;
import com.chainerist.blockchain.manager.airconnect.entity.AirlineEntity;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.airconnect.error.ErrorCodes;
import com.chainerist.blockchain.manager.airconnect.mapper.FlightMapper;
import com.chainerist.blockchain.manager.airconnect.repository.FlightRepository;
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

@Service
@Slf4j
public class FlightService {

    private FlightRepository flightRepository;

    private FlightMapper flightMapper;

    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper){
        this.flightMapper = flightMapper;
        this.flightRepository = flightRepository;
    }


    public Flight findById(String id){
        return flightRepository.findById(id).map(flightMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.DATA_NOT_FOUND));
    }

    @Transactional
    public Flight createFlight(Flight domainObject){
        this.validateFlight(domainObject);
        return save(domainObject);
    }

    private Flight save(Flight domainObject){
        return flightMapper.toDomainObject(flightRepository.save(flightMapper.toEntity(domainObject)));
    }

    public ChaineristPageable<Flight> findAll(int page, int size){
        Page<FlightEntity> flights = flightRepository.findAll(PageRequest.of(page, size));
        return new ChaineristPageable<Flight>(flights.getTotalElements(),flights.getTotalPages(),flights.getPageable(),flightMapper.toListDomainObject(flights.getContent()));
    }

    @Transactional
    public ChaineristPageable<Flight> findBySearchCriteria(Specification<FlightEntity> spec, Pageable page){
        Page<FlightEntity> searchResult = flightRepository.findAll(spec, page);
        return new ChaineristPageable<Flight>(searchResult.getTotalElements(),searchResult.getTotalPages(),searchResult.getPageable(),flightMapper.toListDomainObject(searchResult.getContent()));
    }

    private void validateFlight(Flight domainObject){

        if(Objects.isNull(domainObject)){
            throw new ChaineristException(ErrorCodes.AIRPORT_DATA_CAN_NOT_BE_EMPTY);
        }
        if (StringUtils.isEmpty(domainObject.getFlightNo())){
            throw new ChaineristException(ErrorCodes.FLIGHT_NO_CAN_NOT_BE_EMPTY);
        }

    }

    public Flight findByAirline(String airport) {
        return flightRepository.findByAirline(airport).map(flightMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.AIRPORT_DATA_CAN_NOT_BE_EMPTY));
    }
}
