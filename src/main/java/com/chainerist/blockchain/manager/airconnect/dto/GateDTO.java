package com.chainerist.blockchain.manager.airconnect.dto;

import com.chainerist.blockchain.manager.airconnect.domain.Airport;
import com.chainerist.blockchain.manager.airconnect.domain.Ticket;
import com.chainerist.blockchain.manager.util.base.BaseDTO;
import lombok.Data;

import java.util.Set;

@Data
public class GateDTO extends BaseDTO {

    private String code;

    //private AirportDTO airport;

    private String airportId;

    private String airportName;

    //private Set<TicketDTO> tickets;
}
