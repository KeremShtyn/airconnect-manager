package com.chainerist.blockchain.manager.airconnect.entity;

import com.chainerist.blockchain.manager.util.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@SQLDelete(sql = "UPDATE Passenger SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "Passenger")
@Table(name = "Passenger", indexes = {@Index(columnList = "IDENTIFIER", name = "passenger_identifier_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"IDENTIFIER"})})
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"tickets"})
@ToString(callSuper = true, includeFieldNames = true, exclude = {"tickets"})
public class PassengerEntity extends BaseEntity {

    @Column(name = "IDENTIFIER", unique = true)
    private String identifier;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTH_DAY")
    private String birthDay;

    @OneToMany(mappedBy = "passenger", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<TicketEntity> tickets;
}
