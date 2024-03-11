package com.chainerist.blockchain.manager.airconnect.entity;


import com.chainerist.blockchain.manager.airconnect.enums.ProviderCategory;
import com.chainerist.blockchain.manager.util.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@SQLDelete(sql = "UPDATE Passenger SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "ServiceProvider")
@Table(name = "ServiceProvider", indexes = {@Index(columnList = "PROVIDER_CODE", name = "serviceProvider_providerNo_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"PROVIDER_CODE"})})
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = true)
public class ServiceProviderEntity extends BaseEntity {

    @Column(name = "PROVIDER_CODE", unique = true)
    private String providerCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private ProviderCategory category;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "SERVICE_PROVIDERS", joinColumns = {@JoinColumn(name = "SERVICE_PROVIDER_ID")}, inverseJoinColumns = {@JoinColumn(name = "AIRPORT_ID")})
    private Set<AirportEntity> airports;

}
