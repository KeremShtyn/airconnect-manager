package com.chainerist.blockchain.manager.airconnect.controller;

import com.chainerist.blockchain.manager.airconnect.api.GateAPI;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Gate;
import com.chainerist.blockchain.manager.airconnect.domain.Operator;
import com.chainerist.blockchain.manager.airconnect.dto.GateDTO;
import com.chainerist.blockchain.manager.airconnect.mapper.GateDTOMapper;
import com.chainerist.blockchain.manager.airconnect.service.GateService;
import com.chainerist.blockchain.manager.util.response.ChaineristGenerator;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class GateController implements GateAPI {

    private GateService gateService;

    private GateDTOMapper gateDTOMapper;

    public GateController(GateService gateService, GateDTOMapper gateDTOMapper){
        this.gateDTOMapper = gateDTOMapper;
        this.gateService = gateService;
    }


    @Override
    public ChaineristResponse<Object> getById(String id) {
        Gate gate = gateService.findById(id);
        return ChaineristGenerator.generateSignResponse(gateDTOMapper.toDTO(gate));
    }

    @Override
    public ChaineristResponse<Object> save(GateDTO gateDTO) {
        Gate gate = gateDTOMapper.toDomain(gateDTO);
        gate = gateService.createGate(gate);
        return ChaineristGenerator.generateSignResponse(gateDTOMapper.toDTO(gate));
    }

    @Override
    public ChaineristResponse<Object> update(GateDTO gateDTO) {
        return save(gateDTO);
    }

    @Override
    public ChaineristResponse<Object> getPage(Map<String, Object> header, int page, int size, String[] sortBy) {
        ChaineristPageable<Gate> gatePage = gateService.findAll(page, size);
        return ChaineristGenerator.generateSignResponse(gatePage);
    }
}
