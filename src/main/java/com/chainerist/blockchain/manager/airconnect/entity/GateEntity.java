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


@SQLDelete(sql = "UPDATE Gate SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =?")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "Gate")
@Table(name = "Gate", indexes = {@Index(columnList = "CODE", name = "gate_code_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"CODE"})})
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"tickets"})
@ToString(callSuper = true, includeFieldNames = true, exclude = {"tickets"})
public class GateEntity extends BaseEntity {

    @Column(name = "CODE", unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "AIRPORT")
    private AirportEntity airport;

    @OneToMany(mappedBy = "gate", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<TicketEntity> tickets;
}
