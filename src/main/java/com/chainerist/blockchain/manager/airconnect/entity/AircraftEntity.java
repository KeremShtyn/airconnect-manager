package com.chainerist.blockchain.manager.airconnect.entity;


import com.chainerist.blockchain.manager.util.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@SQLDelete(sql = "UPDATE Aircraft SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "Aircraft")
@Table(name= "Aircraft", indexes = {@Index(columnList = "TYPE", name = "aircraft_type_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"LEG_NO"})})
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = true )
public class AircraftEntity extends BaseEntity {

    @Column(name = "LEG_NO", unique = true)
    private String legNo;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "PASSENGER_CAPACITY")
    private Integer passengerCapacity;

    @Column(name = "LUGGAGE_CAPACITY")
    private Integer luggageCapacity;

    @ManyToOne
    @JoinColumn(name = "AIRLINE_ID")
    private AirlineEntity airline;

}
