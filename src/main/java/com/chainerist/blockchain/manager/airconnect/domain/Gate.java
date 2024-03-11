package com.chainerist.blockchain.manager.airconnect.domain;

import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;

@Data
public class Gate extends BaseDomain {

    private String code;

    //private Airport airport;

    private String airportId;

    private String airportName;

    //private Set<Ticket> tickets;
}
