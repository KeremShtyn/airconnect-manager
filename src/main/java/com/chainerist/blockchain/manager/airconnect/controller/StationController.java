package com.chainerist.blockchain.manager.airconnect.controller;

import com.chainerist.blockchain.manager.airconnect.api.StationAPI;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Passenger;
import com.chainerist.blockchain.manager.airconnect.domain.Station;
import com.chainerist.blockchain.manager.airconnect.dto.StationDTO;
import com.chainerist.blockchain.manager.airconnect.entity.StationEntity;
import com.chainerist.blockchain.manager.airconnect.mapper.StationDTOMapper;
import com.chainerist.blockchain.manager.airconnect.service.StationService;
import com.chainerist.blockchain.manager.util.response.ChaineristGenerator;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StationController implements StationAPI {

   private StationService stationService;

   private StationDTOMapper stationDTOMapper;

   public StationController(StationService stationService, StationDTOMapper stationDTOMapper){
       this.stationDTOMapper = stationDTOMapper;
       this.stationService = stationService;
   }

    @Override
    public ChaineristResponse<Object> save(StationDTO stationDTO) {
        Station domainObject = stationDTOMapper.toDomain(stationDTO);
        domainObject = stationService.createStation(domainObject);
       return ChaineristGenerator.generateSignResponse(stationDTOMapper.toDTO(domainObject));
    }

    @Override
    public ChaineristResponse<Object> update(StationDTO stationDTO) {
        return save(stationDTO);
    }

    @Override
    public ChaineristResponse<Object> getById(String id) {
       Station station = stationService.findById(id);
       return ChaineristGenerator.generateSignResponse(stationDTOMapper.toDTO(station));
    }

    @Override
    public ChaineristResponse<Object> getPage(Map<String, Object> header, int page, int size, String[] sortBy) {
        ChaineristPageable<Station> stationPage = stationService.findAll(page, size);
        return ChaineristGenerator.generateSignResponse(stationPage);
    }
}
