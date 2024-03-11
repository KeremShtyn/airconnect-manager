package com.chainerist.blockchain.manager.airconnect.entity;

import com.chainerist.blockchain.manager.airconnect.enums.FlightDirection;
import com.chainerist.blockchain.manager.airconnect.enums.FlightStatus;
import com.chainerist.blockchain.manager.util.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@SQLDelete(sql = "UPDATE Flight SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "Flight")
@Data
@Table(name = "Flight", indexes = {@Index(columnList = "FLIGHT_NO", name = "flight_flightNo_indx")})
@EqualsAndHashCode(callSuper = false, exclude = {"tickets", "stations"})
@ToString(callSuper = true, includeFieldNames = true, exclude = {"tickets", "stations"})
public class FlightEntity extends BaseEntity{

    @Column(name = "FLIGHT_NO")
    private Integer flightNo;

    @Column(name = "SCHEDULE_TIME")
    private LocalDateTime time;

    @Column(name = "ACTUAL_DATE")
    private LocalDateTime actualDate;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @Column(name = "DIRECTION")
    @Enumerated(EnumType.STRING)
    private FlightDirection direction;

    @ManyToOne
    @JoinColumn(name = "AIRLINE_ID")
    private AirlineEntity airline;

    @OneToMany(mappedBy = "flight",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<TicketEntity> tickets;

    @OneToMany(mappedBy = "flight",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<StationEntity> stations;


}
