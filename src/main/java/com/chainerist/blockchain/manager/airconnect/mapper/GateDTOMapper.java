package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Gate;
import com.chainerist.blockchain.manager.airconnect.dto.GateDTO;
import com.chainerist.blockchain.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GateDTOMapper extends BaseDTOMapper<Gate, GateDTO> {
}
