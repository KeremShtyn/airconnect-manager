package com.chainerist.blockchain.manager.authentication.domain;

import com.chainerist.blockchain.manager.authentication.entity.UserRole;
import com.chainerist.blockchain.manager.authentication.entity.WorkingUnitEntity;
import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;


@Data
public class User extends BaseDomain {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private UserRole role;

    private Set<WorkingUnit> workingUnits;

}
