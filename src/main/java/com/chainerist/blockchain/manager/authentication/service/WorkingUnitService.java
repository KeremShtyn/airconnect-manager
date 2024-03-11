package com.chainerist.blockchain.manager.authentication.service;

import com.chainerist.blockchain.manager.authentication.domain.User;
import com.chainerist.blockchain.manager.authentication.domain.WorkingUnit;
import com.chainerist.blockchain.manager.authentication.error.ErrorCodes;
import com.chainerist.blockchain.manager.authentication.mapper.WorkingUnitMapper;
import com.chainerist.blockchain.manager.authentication.repository.WorkingUnitRepository;
import com.chainerist.blockchain.manager.util.exception.ChaineristException;
import org.springframework.stereotype.Service;

@Service
public class WorkingUnitService {

    private WorkingUnitMapper workingUnitMapper;

    private WorkingUnitRepository workingUnitRepository;

    public WorkingUnitService(WorkingUnitMapper workingUnitMapper, WorkingUnitRepository workingUnitRepository){
        this.workingUnitMapper = workingUnitMapper;
        this.workingUnitRepository = workingUnitRepository;
    }

    public WorkingUnit findOne(String id) {
        return workingUnitRepository.findById(id).map(workingUnitMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.THIS_USER_DOES_NOT_EXIST));
    }

    public WorkingUnit saveOrUpdate(WorkingUnit workingUnit) {
        return workingUnitMapper.toDomainObject(workingUnitRepository.save(workingUnitMapper.toEntity(workingUnit)));
    }
}
