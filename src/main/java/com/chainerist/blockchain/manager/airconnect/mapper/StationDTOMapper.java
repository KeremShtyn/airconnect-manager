package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Station;
import com.chainerist.blockchain.manager.airconnect.dto.StationDTO;
import com.chainerist.blockchain.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StationDTOMapper extends BaseDTOMapper<Station, StationDTO> {
}
