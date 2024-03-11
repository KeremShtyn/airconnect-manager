package com.chainerist.blockchain.manager.airconnect.entity;


import com.chainerist.blockchain.manager.airconnect.domain.Operator;
import com.chainerist.blockchain.manager.util.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@SQLDelete(sql = "UPDATE Airport SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =?")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "Airport")
@Table(name= "Airport", indexes = {@Index(columnList = "IATA", name = "airport_iata_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"IATA"})})
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"gates", "stations"})
@ToString(callSuper = true, includeFieldNames = true, exclude = {"gates", "stations"} )
public class AirportEntity extends BaseEntity {

    @Column(name = "IATA", unique = true)
    private String iata;

    @Column(name = "ICAO")
    private String icao;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "OPERATOR_ID")
    private OperatorEntity operator;

    @OneToMany(mappedBy = "airport",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<GateEntity> gates;

    @ManyToMany(mappedBy = "airports", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<ServiceProviderEntity> serviceProviders = new HashSet<>();


    @OneToMany(mappedBy = "destination", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<StationEntity> stations;


}
