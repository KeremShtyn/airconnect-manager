package com.chainerist.blockchain.manager.airconnect.domain;

import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;


@Data
public class ServiceProvider extends BaseDomain {

    private String providerNo;

    private String firstName;

    private String lastName;
}
