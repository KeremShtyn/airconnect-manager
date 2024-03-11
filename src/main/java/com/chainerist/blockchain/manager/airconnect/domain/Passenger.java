package com.chainerist.blockchain.manager.airconnect.domain;

import com.chainerist.blockchain.manager.util.base.BaseDomain;
import com.chainerist.blockchain.manager.util.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;


@Data
public class Passenger extends BaseDomain {

    private String identifier;

    private String firstName;

    private String lastName;

    private String birthDay;

}
