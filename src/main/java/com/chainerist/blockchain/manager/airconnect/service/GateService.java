package com.chainerist.blockchain.manager.airconnect.service;


import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Flight;
import com.chainerist.blockchain.manager.airconnect.domain.Gate;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.airconnect.entity.GateEntity;
import com.chainerist.blockchain.manager.airconnect.error.ErrorCodes;
import com.chainerist.blockchain.manager.airconnect.mapper.GateMapper;
import com.chainerist.blockchain.manager.airconnect.repository.GateRepository;
import com.chainerist.blockchain.manager.util.exception.ChaineristException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class GateService {

    private GateRepository gateRepository;

    private GateMapper gateMapper;

    public GateService(GateRepository gateRepository, GateMapper gateMapper){
        this.gateMapper = gateMapper;
        this.gateRepository = gateRepository;
    }

    public ChaineristPageable<Gate> findAll(int page, int size){
        Page<GateEntity> gates = gateRepository.findAll(PageRequest.of(page, size));
        return new ChaineristPageable<Gate>(gates.getTotalElements(),gates.getTotalPages(),gates.getPageable(),gateMapper.toListDomainObject(gates.getContent()));
    }

    public Gate findById(String id){
        return gateRepository.findById(id).map(gateMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.DATA_NOT_FOUND));
    }

    public Gate findByCode(String code){
        return gateRepository.findByCode(code).map(gateMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.DATA_NOT_FOUND));
    }

    public Gate save(Gate domainObject){
        return gateMapper.toDomainObject(gateRepository.save(gateMapper.toEntity(domainObject)));
    }

    public Gate createGate(Gate domainObject){
        this.validateGate(domainObject);
        return save(domainObject);
    }

    private void validateGate(Gate domainObject){
        if(Objects.isNull(domainObject)){
            throw new ChaineristException(ErrorCodes.GATE_DATA_CAN_NOT_BE_EMPTY);
        }
        if(StringUtils.isEmpty(domainObject.getCode())){
            throw new ChaineristException(ErrorCodes.GATE_CODE_CAN_NOT_BE_EMPTY);
        }

    }
}
