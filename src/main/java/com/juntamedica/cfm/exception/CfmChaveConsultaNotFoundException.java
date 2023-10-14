package com.juntamedica.cfm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CfmChaveConsultaNotFoundException extends RuntimeException {

    public CfmChaveConsultaNotFoundException() {
        super("Chave para consulta é obrigatória");
    }
}
