package com.juntamedica.cfm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CfmMedicoNotFoundException extends RuntimeException {

    public CfmMedicoNotFoundException() {
        super("Médico não encontrado");
    }
}
