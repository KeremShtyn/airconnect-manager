package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Aircraft;
import com.chainerist.blockchain.manager.airconnect.dto.AircraftDTO;
import com.chainerist.blockchain.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AircraftDTOMapper extends BaseDTOMapper<Aircraft, AircraftDTO> {
}
