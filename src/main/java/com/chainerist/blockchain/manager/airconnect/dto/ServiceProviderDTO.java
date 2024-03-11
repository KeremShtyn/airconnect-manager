package com.chainerist.blockchain.manager.airconnect.dto;

import com.chainerist.blockchain.manager.util.base.BaseDTO;
import lombok.Data;

@Data
public class ServiceProviderDTO extends BaseDTO {

    private String providerNo;

    private String firstName;

    private String lastName;
}
