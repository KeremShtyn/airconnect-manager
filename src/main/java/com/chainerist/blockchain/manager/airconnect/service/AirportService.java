package com.chainerist.blockchain.manager.airconnect.service;

import com.chainerist.blockchain.manager.airconnect.domain.Aircraft;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Flight;
import com.chainerist.blockchain.manager.airconnect.entity.AircraftEntity;
import com.chainerist.blockchain.manager.airconnect.entity.AirportEntity;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.chainerist.blockchain.manager.airconnect.domain.Airport;
import com.chainerist.blockchain.manager.airconnect.error.ErrorCodes;
import com.chainerist.blockchain.manager.airconnect.mapper.AirportMapper;
import com.chainerist.blockchain.manager.airconnect.repository.AirportRepository;
import com.chainerist.blockchain.manager.util.exception.ChaineristException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class AirportService {

    private AirportRepository airportRepository;

    private AirportMapper airportMapper;

    public AirportService(AirportRepository airportRepository, AirportMapper airportMapper ){
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;

    }

    public ChaineristPageable<Airport> findAll(int page, int size){
        Page<AirportEntity> airports = airportRepository.findAll(PageRequest.of(page, size));
        return new ChaineristPageable<Airport>(airports.getTotalElements(),airports.getTotalPages(),airports.getPageable(),airportMapper.toListDomainObject(airports.getContent()));
    }

    public Airport findByIata(String iata){
        return airportRepository.findByIata(iata).map(airportMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.DATA_NOT_FOUND));
    }

    public Airport createAirport(Airport domainObject){
        this.validateAirport(domainObject);
        return save(domainObject);
    }

    private Airport save(Airport domainObject){
        return airportMapper.toDomainObject(airportRepository.save(airportMapper.toEntity(domainObject)));
    }

    @Transactional
    public ChaineristPageable<Airport> findBySearchCriteria(Specification<AirportEntity> spec, Pageable page){
        Page<AirportEntity> searchResult = airportRepository.findAll(spec, page);
        return new ChaineristPageable<Airport>(searchResult.getTotalElements(),searchResult.getTotalPages(),searchResult.getPageable(),airportMapper.toListDomainObject(searchResult.getContent()));
    }

    private void validateAirport(Airport domainObject){

        if(Objects.isNull(domainObject)){
            throw new ChaineristException(ErrorCodes.AIRPORT_DATA_CAN_NOT_BE_EMPTY);
        }
        if (StringUtils.isEmpty(domainObject.getIata())){
            throw new ChaineristException(ErrorCodes.AIRPORT_IATA_CAN_NOT_BE_EMPTY);
        }
        if (StringUtils.isEmpty(domainObject.getIcao())){
            throw new ChaineristException(ErrorCodes.AIRPORT_ICAO_CAN_NOT_BE_EMPTY);
        }
    }
}
