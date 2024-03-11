package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Aircraft;
import com.chainerist.blockchain.manager.airconnect.domain.Gate;
import com.chainerist.blockchain.manager.airconnect.entity.AircraftEntity;
import com.chainerist.blockchain.manager.airconnect.entity.GateEntity;
import com.chainerist.blockchain.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GateMapper extends BaseMapper<GateEntity, Gate> {

    @Mapping(source = "airportId", target = "airport.id")
    @Mapping(source = "airportName", target = "airport.name")
    GateEntity toEntity(Gate domain);

    @Mapping(target = "airportId", source = "airport.id")
    @Mapping(target = "airportName", source = "airport.name")
    Gate toDomainObject(GateEntity entityObject);

}
