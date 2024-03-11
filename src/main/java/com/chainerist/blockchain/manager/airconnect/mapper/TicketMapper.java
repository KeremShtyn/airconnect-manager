package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Station;
import com.chainerist.blockchain.manager.airconnect.domain.Ticket;
import com.chainerist.blockchain.manager.airconnect.entity.StationEntity;
import com.chainerist.blockchain.manager.airconnect.entity.TicketEntity;
import com.chainerist.blockchain.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper extends BaseMapper<TicketEntity, Ticket>{

    @Mapping(source = "gateId", target = "gate.id")
    @Mapping(source = "gateAirportName", target = "gate.airport.name")
    @Mapping(source = "flightId", target = "flight.id")
    @Mapping(source = "flightDirection", target = "flight.direction")
    @Mapping(source = "flightStatus", target = "flight.status")
    @Mapping(source = "passengerId", target = "passenger.id")
    @Mapping(source = "passengerFirstName", target = "passenger.firstName")
    @Mapping(source = "passengerLastName", target = "passenger.lastName")
    TicketEntity toEntity(Ticket domain);

    @Mapping(target = "gateId", source = "gate.id")
    @Mapping(target = "gateAirportName", source = "gate.airport.name")
    @Mapping(target = "flightId", source = "flight.id")
    @Mapping(target = "flightDirection", source = "flight.direction")
    @Mapping(target = "flightStatus", source = "flight.status")
    @Mapping(target = "passengerId", source = "passenger.id")
    @Mapping(target = "passengerFirstName", source = "passenger.firstName")
    @Mapping(target = "passengerLastName", source = "passenger.lastName")
    Ticket toDomainObject(TicketEntity entityObject);

}
