package com.chainerist.blockchain.manager.authentication.entity;

import com.chainerist.blockchain.manager.util.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@SQLDelete(sql = "UPDATE WorkingUnit SET DELETED_AT = CURRENT_TIMESTAMP WHERE id =? and version =? ")
@Where(clause = "DELETED_DATE is null")
@Entity(name = "WorkingUnit")
@Table(name = "WorkingUnit", indexes = {@Index(columnList = "working_id", name = "wrk_working_id_indx")})
@Data
@EqualsAndHashCode(callSuper = false)
@ToString( callSuper = true, includeFieldNames = true )
public class WorkingUnitEntity extends BaseEntity {

    @Column(name = "working_type")
    private String workingType;

    @Column(name = "working_id")
    private String workingId;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
}
