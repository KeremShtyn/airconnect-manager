package com.chainerist.blockchain.manager.airconnect.domain;


import com.chainerist.blockchain.manager.airconnect.dto.AirportDTO;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;


@Data
public class Station extends BaseDomain {

    private String StationNo;

    private String name;

    //private Flight flight;

    private String flightId;

    private String flightDirection;

    private String flightStatus;

    //private Airport destination;

    private String destAirportId;

    private String destAirportName;
}
