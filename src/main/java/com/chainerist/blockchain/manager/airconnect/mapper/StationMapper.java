package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Flight;
import com.chainerist.blockchain.manager.airconnect.domain.Station;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.airconnect.entity.StationEntity;
import com.chainerist.blockchain.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StationMapper extends BaseMapper<StationEntity, Station> {

    @Mapping(source = "destAirportId", target = "destination.id")
    @Mapping(source = "destAirportName", target = "destination.name")
    @Mapping(source = "flightId", target = "flight.id")
    @Mapping(source = "flightDirection", target = "flight.direction")
    @Mapping(source = "flightStatus", target = "flight.status")
    StationEntity toEntity(Station domain);

    @Mapping(target = "destAirportId", source = "destination.id")
    @Mapping(target = "destAirportName", source = "destination.name")
    @Mapping(target = "flightId", source = "flight.id")
    @Mapping(target = "flightDirection", source = "flight.direction")
    @Mapping(target = "flightStatus", source = "flight.status")
    Station toDomainObject(StationEntity entityObject);


}
