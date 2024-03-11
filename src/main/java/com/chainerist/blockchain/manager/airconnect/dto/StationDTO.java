package com.chainerist.blockchain.manager.airconnect.dto;

import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.util.base.BaseDTO;
import lombok.Data;



@Data
public class StationDTO extends BaseDTO {

    private String StationNo;

    private String name;

    //private FlightDTO flight;

    private String flightId;

    private String flightDirection;

    private String flightStatus;

    //private AirportDTO destination;

    private String destAirportId;

    private String destAirportName;
}
