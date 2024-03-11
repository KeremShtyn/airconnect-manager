package com.chainerist.blockchain.manager.airconnect.domain;

import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class Ticket extends BaseDomain {

    private String identifier;

    private String seat;

    //private Flight flight;

    private String flightId;

    private String flightDirection;

    private String flightStatus;

    //private Gate gate;

    private String gateId;

    private String gateAirportName;

    //private Passenger passenger;

    private String passengerId;

    private String passengerFirstName;

    private String passengerLastName;

}
