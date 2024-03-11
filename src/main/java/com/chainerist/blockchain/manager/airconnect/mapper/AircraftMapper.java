package com.chainerist.blockchain.manager.airconnect.mapper;


import com.chainerist.blockchain.manager.airconnect.domain.Aircraft;
import com.chainerist.blockchain.manager.airconnect.domain.Flight;
import com.chainerist.blockchain.manager.airconnect.entity.AircraftEntity;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AircraftMapper extends BaseMapper<AircraftEntity, Aircraft> {

    @Mapping(source = "airlineId", target = "airline.id")
    @Mapping(source = "airlineName", target = "airline.name")
    AircraftEntity toEntity(Aircraft domain);

    @Mapping(target = "airlineId", source = "airline.id")
    @Mapping(target = "airlineName", source = "airline.name")
    Aircraft toDomainObject(AircraftEntity entityObject);

}
