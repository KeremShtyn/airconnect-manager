package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Airline;
import com.chainerist.blockchain.manager.airconnect.dto.AirlineDTO;
import com.chainerist.blockchain.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirlineDTOMapper extends BaseDTOMapper<Airline, AirlineDTO> {
}
