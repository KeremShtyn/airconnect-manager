package com.chainerist.blockchain.manager.authentication.domain;

import com.chainerist.blockchain.manager.authentication.entity.UserEntity;
import com.chainerist.blockchain.manager.authentication.entity.UserRole;
import com.chainerist.blockchain.manager.authentication.entity.WorkingUnitEntity;
import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
public class WorkingUnit extends BaseDomain {

    private String workingType;
    private String workingId;
    private UserRole role;
    private String userId;

}
