package com.chainerist.blockchain.manager.airconnect.dto;

import com.chainerist.blockchain.manager.util.base.BaseDTO;
import lombok.Data;


@Data
public class AircraftDTO extends BaseDTO {

    private String legNo;

    private String type;

    private String model;

    private Integer passengerCapacity;

    private Integer luggageCapacity;

    //private AirlineDTO airline;

    private String airlineId;

    private String airlineName;
}
