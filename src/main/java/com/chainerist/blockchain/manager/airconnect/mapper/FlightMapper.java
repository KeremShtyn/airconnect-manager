package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Flight;
import com.chainerist.blockchain.manager.airconnect.dto.FlightDTO;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.util.base.BaseDTO;
import com.chainerist.blockchain.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface FlightMapper extends BaseMapper<FlightEntity, Flight> {

    @Mapping(source = "airlineId", target = "airline.id")
    @Mapping(source = "airlineName", target = "airline.name")
    FlightEntity toEntity(Flight domain);

    @Mapping(target = "airlineId", source = "airline.id")
    @Mapping(target = "airlineName", source = "airline.name")
    Flight toDomainObject(FlightEntity entityObject);


}
