package com.chainerist.blockchain.manager.airconnect.domain;

import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;

import java.util.Set;

@Data
public class Airline extends BaseDomain {

    private String iata;
    private String icao;
    private String name;
    private String callsign;
    private String country;
    private String flightId;
    private String flightDirection;
    private String flightStatus;

}
