package com.chainerist.blockchain.manager.airconnect.entity;

import com.chainerist.blockchain.manager.util.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@SQLDelete(sql = "UPDATE Ticket SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "Ticket")
@Table(name= "Ticket", indexes = {@Index(columnList = "IDENTIFIER", name = "ticket_identifier_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"IDENTIFIER"})})
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = true )
public class TicketEntity extends BaseEntity {

    @Column(name = "IDENTIFIER", unique = true)
    private String identifier;

    @Column(name = "SEAT")
    private String seat;

    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID")
    private FlightEntity flight;

    @ManyToOne
    @JoinColumn(name = "GATE_ID")
    private GateEntity gate;

    @ManyToOne
    @JoinColumn(name = "PASSENGER_ID")
    private PassengerEntity passenger;


}
