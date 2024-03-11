package com.chainerist.blockchain.manager.authentication.mapper;

import com.chainerist.blockchain.manager.authentication.domain.WorkingUnit;
import com.chainerist.blockchain.manager.authentication.dto.WorkingUnitDTO;
import com.chainerist.blockchain.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkingUnitDTOMapper extends BaseDTOMapper<WorkingUnit, WorkingUnitDTO> {
}
