package com.chainerist.blockchain.manager.authentication.dto;


import com.chainerist.blockchain.manager.authentication.domain.WorkingUnit;
import com.chainerist.blockchain.manager.authentication.entity.UserRole;
import com.chainerist.blockchain.manager.util.base.BaseDTO;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO extends BaseDTO {

    private String firstName;
    private String lastName;
    private String username;
    private UserRole role;
    private String email;

    private Set<WorkingUnitDTO> workingUnits;

}
