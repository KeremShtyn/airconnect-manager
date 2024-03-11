package com.chainerist.blockchain.manager.airconnect.domain;

import com.chainerist.blockchain.manager.airconnect.entity.AirlineEntity;
import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;


@Data
public class Aircraft extends BaseDomain {

    private String legNo;

    private String type;

    private String model;

    private Integer passengerCapacity;

    private Integer luggageCapacity;

    //private Airline airline;

    private String airlineId;

    private String airlineName;
}
