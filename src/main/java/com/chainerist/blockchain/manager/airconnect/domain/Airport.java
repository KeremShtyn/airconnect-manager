package com.chainerist.blockchain.manager.airconnect.domain;

import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;

@Data
public class Airport extends BaseDomain {

    private String iata;

    private String icao;

    private String country;

    private String city;

    private String name;

    private Operator operator;


}
