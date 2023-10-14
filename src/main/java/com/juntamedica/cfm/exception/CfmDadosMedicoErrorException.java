package com.juntamedica.cfm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CfmDadosMedicoErrorException extends RuntimeException {

    public CfmDadosMedicoErrorException(String msg) {
        super(msg);
    }
}
