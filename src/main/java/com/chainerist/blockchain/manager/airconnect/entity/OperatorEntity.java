package com.chainerist.blockchain.manager.airconnect.entity;


import com.chainerist.blockchain.manager.airconnect.domain.Airport;
import com.chainerist.blockchain.manager.util.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;


@SQLDelete(sql = "UPDATE Operator SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "Operator")
@Table(name = "Operator", indexes = {@Index(columnList = "OPERATOR_NO", name = "operator_operatorNo_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"OPERATOR_NO"})})
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = true)
public class OperatorEntity extends BaseEntity {


    @Column(name = "OPERATOR_NO", unique = true)
    private String operatorNo;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "operator",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<AirportEntity> airports;

}
