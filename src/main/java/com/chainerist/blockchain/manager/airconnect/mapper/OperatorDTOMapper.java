package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Operator;
import com.chainerist.blockchain.manager.airconnect.dto.OperatorDTO;
import com.chainerist.blockchain.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperatorDTOMapper extends BaseDTOMapper<Operator, OperatorDTO> {
}
