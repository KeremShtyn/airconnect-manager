package com.chainerist.blockchain.manager.authentication.dto;


import com.chainerist.blockchain.manager.authentication.entity.UserRole;
import lombok.Data;

@Data
public class TokenRequestDTO {

    private String username, password;

}
