package com.chainerist.blockchain.manager.airconnect.service;


import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Passenger;
import com.chainerist.blockchain.manager.airconnect.domain.Station;
import com.chainerist.blockchain.manager.airconnect.domain.Ticket;
import com.chainerist.blockchain.manager.airconnect.entity.PassengerEntity;
import com.chainerist.blockchain.manager.airconnect.entity.StationEntity;
import com.chainerist.blockchain.manager.airconnect.entity.TicketEntity;
import com.chainerist.blockchain.manager.airconnect.mapper.StationMapper;
import com.chainerist.blockchain.manager.airconnect.repository.StationRepository;
import com.chainerist.blockchain.manager.authentication.error.ErrorCodes;
import com.chainerist.blockchain.manager.util.exception.ChaineristException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class StationService {

    private StationRepository stationRepository;

    private StationMapper stationMapper;

    public StationService(StationRepository stationRepository,StationMapper stationMapper){
        this.stationMapper = stationMapper;
        this.stationRepository = stationRepository;
    }

    public Station findById(String id){
       return stationRepository.findById(id).map(stationMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.THIS_USER_DOES_NOT_EXIST));
    }

    public Station createStation(Station domainObject) {
        this.validatePassenger(domainObject);
        return saveOrUpdate(domainObject);
    }

    private void validatePassenger(Station domainObject) {

        if (Objects.isNull(domainObject)) {
            throw new ChaineristException(com.chainerist.blockchain.manager.airconnect.error.ErrorCodes.STATION_DATA_CAN_NOT_BE_EMPTY);
        }

        if (StringUtils.isEmpty(domainObject.getId())){
            this.uniquenessStation(domainObject.getStationNo());
        }

        if (StringUtils.isEmpty(domainObject.getStationNo())) {
            throw new ChaineristException(com.chainerist.blockchain.manager.airconnect.error.ErrorCodes.STATION_NO_CAN_NOT_BE_EMPTY);
        }


    }

    private void uniquenessStation(String stationNo) {
        Optional<StationEntity> stationOpt = stationRepository.findByStationNo(stationNo);
        if (stationOpt.isPresent()) {
            throw new ChaineristException(com.chainerist.blockchain.manager.airconnect.error.ErrorCodes.STATION_ALREADY_EXISTS);

        }
    }

    private Station saveOrUpdate(Station domainObject) {
        return stationMapper.toDomainObject(stationRepository.save(stationMapper.toEntity(domainObject)));
    }

    public ChaineristPageable<Station> findAll(int page, int size){
        Page<StationEntity> stations = stationRepository.findAll(PageRequest.of(page, size));
        return new ChaineristPageable<Station>(stations.getTotalElements(),stations.getTotalPages(),stations.getPageable(),stationMapper.toListDomainObject(stations.getContent()));
    }
}
