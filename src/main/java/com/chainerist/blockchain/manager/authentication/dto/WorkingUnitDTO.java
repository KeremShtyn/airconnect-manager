package com.chainerist.blockchain.manager.authentication.dto;

import com.chainerist.blockchain.manager.authentication.entity.UserEntity;
import com.chainerist.blockchain.manager.authentication.entity.UserRole;
import com.chainerist.blockchain.manager.util.base.BaseDTO;
import lombok.Data;

@Data
public class WorkingUnitDTO extends BaseDTO {

    private String workingType;
    private String workingId;
    private UserRole role;
    private String userId;
}
