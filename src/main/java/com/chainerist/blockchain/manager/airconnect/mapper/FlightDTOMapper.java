package com.chainerist.blockchain.manager.airconnect.mapper;


import com.chainerist.blockchain.manager.airconnect.domain.Flight;
import com.chainerist.blockchain.manager.airconnect.dto.FlightDTO;
import com.chainerist.blockchain.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightDTOMapper extends BaseDTOMapper<Flight, FlightDTO> {
}
