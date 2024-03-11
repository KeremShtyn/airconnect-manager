package com.chainerist.blockchain.manager.authentication.dto;

import com.chainerist.blockchain.manager.authentication.entity.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class UserDataDTO {

    private String firstName;
    private String lastName;
    private String username;
    private UserRole role;
    private String email;
    private Set<WorkingUnitDTO> workingUnits;
}
