package com.chainerist.blockchain.manager.util.base;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ AuditingEntityListener.class })
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @CreationTimestamp
    @Column(name = "CDATE",nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "UDATE")
    private LocalDateTime modifyDate;

    @CreatedBy
    @Column(name = "CUSER", nullable = false, updatable = false)
    private String createUser;

    @LastModifiedBy
    @Column(name = "UUSER")
    private String modifyUser;

    @Version
    @Column(name = "VERSION")
    private Long version = 0L;

    @Column(name = "DELETED_DATE")
    private LocalDateTime deletedDate;


}
