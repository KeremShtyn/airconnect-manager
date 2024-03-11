package com.chainerist.blockchain.manager.airconnect.entity;

import com.chainerist.blockchain.manager.util.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;


@SQLDelete(sql = "UPDATE Airline SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "Airline")
@Table(name= "Airline", indexes = {@Index(columnList = "IATA", name = "airline_iata_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"IATA"})})
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"flights", "aircrafts"})
@ToString(callSuper = true, includeFieldNames = true, exclude = {"flights", "aircrafts"})
public class AirlineEntity extends BaseEntity {

    @Column(name = "IATA", unique = true)
    private String iata;

    @Column(name = "ICAO")
    private String icao;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CALL_SIGN")
    private String callSign;

    @Column(name = "COUNTRY")
    private String country;

    @OneToMany(mappedBy = "airline",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<FlightEntity> flights;

    @OneToMany(mappedBy = "airline", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<AircraftEntity> aircrafts;
}
