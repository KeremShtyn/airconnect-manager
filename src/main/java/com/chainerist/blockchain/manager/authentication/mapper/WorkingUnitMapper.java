package com.chainerist.blockchain.manager.authentication.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Aircraft;
import com.chainerist.blockchain.manager.airconnect.entity.AircraftEntity;
import com.chainerist.blockchain.manager.authentication.domain.WorkingUnit;
import com.chainerist.blockchain.manager.authentication.entity.WorkingUnitEntity;
import com.chainerist.blockchain.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkingUnitMapper extends BaseMapper<WorkingUnitEntity, WorkingUnit> {

    @Mapping(source = "userId", target = "user.id")
    WorkingUnitEntity toEntity(WorkingUnit domain);

    @Mapping(target = "userId", source = "user.id")
    WorkingUnit toDomainObject(WorkingUnitEntity entityObject);
}
