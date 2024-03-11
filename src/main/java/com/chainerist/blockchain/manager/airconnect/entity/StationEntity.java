package com.chainerist.blockchain.manager.airconnect.entity;

import com.chainerist.blockchain.manager.util.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@SQLDelete(sql = "UPDATE Station SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =?")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "Station")
@Table(name= "Station", indexes = {@Index(columnList = "STATION_NO", name = "station_stationNo_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"STATION_NO"})})
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = true )
public class StationEntity extends BaseEntity {

    @Column(name = "STATION_NO", unique = true)
    private String stationNo;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID")
    private FlightEntity flight;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private AirportEntity destination;


}
