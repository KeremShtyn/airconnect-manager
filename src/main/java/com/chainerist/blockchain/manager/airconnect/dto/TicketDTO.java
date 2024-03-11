package com.chainerist.blockchain.manager.airconnect.dto;

import com.chainerist.blockchain.manager.airconnect.domain.*;
import com.chainerist.blockchain.manager.util.base.BaseDTO;
import lombok.Data;

import java.util.Set;

@Data
public class TicketDTO extends BaseDTO {

    private String identifier;

    private String seat;

    //private FlightDTO flight;

    private String flightId;

    private String flightDirection;

    private String flightStatus;

    //private GateDTO gate;

    private String gateId;

    private String gateAirportName;

    //private PassengerDTO passenger;

    private String passengerId;

    private String passengerFirstName;

    private String passengerLastName;

}
