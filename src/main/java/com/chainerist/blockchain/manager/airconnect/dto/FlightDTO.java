package com.chainerist.blockchain.manager.airconnect.dto;


import com.chainerist.blockchain.manager.airconnect.enums.FlightDirection;
import com.chainerist.blockchain.manager.airconnect.enums.FlightStatus;
import com.chainerist.blockchain.manager.util.base.BaseDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class FlightDTO extends BaseDTO {

    private Integer flightNo;

    private LocalDateTime time;

    private LocalDateTime actualDate;

    private FlightStatus status;

    private FlightDirection direction;

    private String airlineId;

    private String airlineName;

   // private Set<TicketD> tickets;
}
