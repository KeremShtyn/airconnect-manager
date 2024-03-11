package com.chainerist.blockchain.manager.airconnect.dto;

import com.chainerist.blockchain.manager.util.base.BaseDTO;
import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;


@Data
public class PassengerDTO extends BaseDTO {

    private String identifier;

    private String firstName;

    private String lastName;

    private String birthDay;

}
