package com.chainerist.blockchain.manager.airconnect.dto;

import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.util.base.BaseDTO;
import lombok.Data;

import java.util.Set;

@Data
public class AirlineDTO extends BaseDTO {

    private String iata;
    private String icao;
    private String name;
    private String callsign;
    private String country;
    private String flightId;
    private String flightDirection;
    private String flightStatus;

}
