package com.chainerist.blockchain.manager.airconnect.dto;

import com.chainerist.blockchain.manager.util.base.BaseDTO;
import lombok.Data;


@Data
public class AirportDTO extends BaseDTO {

    private String iata;

    private String icao;

    private String country;

    private String city;

    private String name;

    private OperatorDTO operator;


}

