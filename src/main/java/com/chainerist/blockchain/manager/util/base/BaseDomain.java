package com.chainerist.blockchain.manager.util.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseDomain implements Serializable {

    private String id;

    private Long version = 0L;

    private LocalDateTime deletedDate;

    private LocalDateTime createdDate;

    private LocalDateTime modifyDate;

    private String createUser;

    private String modifyUser;

}
