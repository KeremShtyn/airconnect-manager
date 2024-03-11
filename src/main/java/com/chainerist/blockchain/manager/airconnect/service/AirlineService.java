package com.chainerist.blockchain.manager.airconnect.service;

import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Ticket;
import com.chainerist.blockchain.manager.airconnect.entity.AirlineEntity;
import com.chainerist.blockchain.manager.airconnect.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.chainerist.blockchain.manager.airconnect.domain.Airline;
import com.chainerist.blockchain.manager.airconnect.mapper.AirlineMapper;
import com.chainerist.blockchain.manager.airconnect.repository.AirlineRepository;
import com.chainerist.blockchain.manager.airconnect.error.ErrorCodes;
import com.chainerist.blockchain.manager.util.exception.ChaineristException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AirlineService {

    private AirlineRepository airlineRepository;
    private AirlineMapper airlineMapper;
    //Pageable firstPageWithTwoElements = PageRequest.of(0, 10);

    public AirlineService(AirlineRepository airlineRepository, AirlineMapper airlineMapper){
        this.airlineRepository = airlineRepository;
        this.airlineMapper = airlineMapper;
    }

    public Airline findByIata(String iata){
        return airlineRepository.findByIata(iata).map(airlineMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.DATA_NOT_FOUND));
    }

    public Airline findById(String id){
        return airlineRepository.findById(id).map(airlineMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.DATA_NOT_FOUND));
    }

    public ChaineristPageable<Airline> findAll(int page, int size){
        Page<AirlineEntity> airlines = airlineRepository.findAll(PageRequest.of(page, size));
        return new ChaineristPageable<Airline>(airlines.getTotalElements(),airlines.getTotalPages(),airlines.getPageable(),airlineMapper.toListDomainObject(airlines.getContent()));
    }

    public Airline createAirline(Airline domainObject){
        this.validateAirline(domainObject);
        return save(domainObject);
    }

    @Transactional
    public ChaineristPageable<Airline> findBySearchCriteria(Specification<AirlineEntity> spec, Pageable page){
        Page<AirlineEntity> airlines = airlineRepository.findAll(spec, page);
        return new ChaineristPageable<Airline>(airlines.getTotalElements(),airlines.getTotalPages(),airlines.getPageable(),airlineMapper.toListDomainObject(airlines.getContent()));
    }

    public Airline save(Airline domainObject){
        return airlineMapper.toDomainObject(airlineRepository.save(airlineMapper.toEntity(domainObject)));
    }

    private void validateAirline(Airline domainObject){
        if(Objects.isNull(domainObject)){
            throw new ChaineristException(ErrorCodes.AIRLINE_DATA_CAN_NOT_BE_EMPTY);
        }
        if(StringUtils.isEmpty(domainObject.getIata())){
            throw new ChaineristException(ErrorCodes.AIRLINE_IATA_CAN_NOT_BE_EMPTY);
        }
        if(StringUtils.isEmpty(domainObject.getIcao())){
            throw new ChaineristException(ErrorCodes.AIRLINE_ICAO_CAN_NOT_BE_EMPTY);
        }
    }

}
