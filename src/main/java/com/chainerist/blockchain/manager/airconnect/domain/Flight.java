package com.chainerist.blockchain.manager.airconnect.domain;

import com.chainerist.blockchain.manager.airconnect.entity.AirlineEntity;
import com.chainerist.blockchain.manager.airconnect.entity.TicketEntity;
import com.chainerist.blockchain.manager.airconnect.enums.FlightDirection;
import com.chainerist.blockchain.manager.airconnect.enums.FlightStatus;
import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Data
public class Flight extends BaseDomain {

    private Integer flightNo;

    private LocalDateTime time;

    private LocalDateTime actualDate;

    private FlightStatus status;

    private FlightDirection direction;

    private String airlineId;

    private String airlineName;

    //private Set<Ticket> tickets;
}
