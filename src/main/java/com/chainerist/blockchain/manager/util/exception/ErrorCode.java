package com.chainerist.blockchain.manager.util.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public interface ErrorCode extends Serializable {
    Integer getCode();

    String getName();

    String langKey();

    HttpStatus getHttpStatus();
}