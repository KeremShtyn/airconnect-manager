package com.chainerist.blockchain.manager.airconnect.domain;

import com.chainerist.blockchain.manager.util.base.BaseDomain;
import lombok.Data;


@Data
public class Operator extends BaseDomain {

    private String operatorNo;

    private String name;

}

