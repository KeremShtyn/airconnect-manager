package com.chainerist.blockchain.manager.airconnect.service;

import com.chainerist.blockchain.manager.airconnect.domain.Airport;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Flight;
import com.chainerist.blockchain.manager.airconnect.domain.Operator;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.airconnect.entity.OperatorEntity;
import com.chainerist.blockchain.manager.airconnect.error.ErrorCodes;
import com.chainerist.blockchain.manager.airconnect.mapper.OperatorMapper;
import com.chainerist.blockchain.manager.airconnect.repository.OperatorRepository;
import com.chainerist.blockchain.manager.util.exception.ChaineristException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@Slf4j
public class OperatorService {

    private OperatorRepository operatorRepository;

    private OperatorMapper operatorMapper;

    public OperatorService(OperatorRepository operatorRepository, OperatorMapper operatorMapper){
        this.operatorRepository = operatorRepository;
        this.operatorMapper = operatorMapper;
    }

    public Operator findByOperatorNo(String operatorNo){
            return operatorRepository.findByOperatorNo(operatorNo).map(operatorMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.DATA_NOT_FOUND));
    }

    public ChaineristPageable<Operator> findAll(int page, int size){
        Page<OperatorEntity> operators = operatorRepository.findAll(PageRequest.of(page, size));
        return new ChaineristPageable<Operator>(operators.getTotalElements(),operators.getTotalPages(),operators.getPageable(),operatorMapper.toListDomainObject(operators.getContent()));
    }

    public Operator createOperator(Operator domainObject){
        this.validateOperator(domainObject);
        return save(domainObject);
    }

    private Operator save(Operator domainObject){
        return operatorMapper.toDomainObject(operatorRepository.save(operatorMapper.toEntity(domainObject)));
    }

    private void validateOperator(Operator domainObject){

        if(Objects.isNull(domainObject)){
            throw new ChaineristException(ErrorCodes.OPERATOR_DATA_CAN_NOT_BE_EMPTY);
        }
        if (StringUtils.isEmpty(domainObject.getName())){
            throw new ChaineristException(ErrorCodes.OPERATOR_NAME_CAN_NOT_BE_EMPTY);
        }
        if (StringUtils.isEmpty(domainObject.getOperatorNo())){
            throw new ChaineristException(ErrorCodes.OPERATOR_NO_CAN_NOT_BE_EMPTY);
        }
    }
}
