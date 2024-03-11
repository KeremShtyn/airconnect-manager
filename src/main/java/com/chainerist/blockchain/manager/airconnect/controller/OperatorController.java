package com.chainerist.blockchain.manager.airconnect.controller;

import com.chainerist.blockchain.manager.airconnect.api.OperatorAPI;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Operator;
import com.chainerist.blockchain.manager.airconnect.domain.Passenger;
import com.chainerist.blockchain.manager.airconnect.dto.OperatorDTO;
import com.chainerist.blockchain.manager.airconnect.mapper.OperatorDTOMapper;
import com.chainerist.blockchain.manager.airconnect.service.OperatorService;
import com.chainerist.blockchain.manager.util.response.ChaineristGenerator;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OperatorController implements OperatorAPI {

    public OperatorDTOMapper operatorDTOMapper;
    public OperatorService operatorService;

    public OperatorController(OperatorService operatorService, OperatorDTOMapper operatorDTOMapper) {
        this.operatorService = operatorService;
        this.operatorDTOMapper = operatorDTOMapper;
    }



    @Override
    public ChaineristResponse<Object> getByOperatorNo(String operatorNo) {
        Operator operator = operatorService.findByOperatorNo(operatorNo);
        return ChaineristGenerator.generateSignResponse(operatorDTOMapper.toDTO(operator));
    }

    @Override
    public ChaineristResponse<Object> save(OperatorDTO operatorDTO) {
        Operator operator = operatorDTOMapper.toDomain(operatorDTO);
        operator = operatorService.createOperator(operator);
        return ChaineristGenerator.generateSignResponse(operatorDTOMapper.toDTO(operator));
    }

    @Override
    public ChaineristResponse<Object> update(OperatorDTO operatorDTO) {
        return save(operatorDTO);
    }

    @Override
    public ChaineristResponse<Object> getPage(Map<String, Object> header, int page, int size, String[] sortBy) {
        ChaineristPageable<Operator> operatorPage = operatorService.findAll(page, size);
        return ChaineristGenerator.generateSignResponse(operatorPage);
    }
}
