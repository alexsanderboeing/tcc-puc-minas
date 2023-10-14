package com.juntamedica.cfm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CfmConsultaErrorException extends RuntimeException  {

    public CfmConsultaErrorException() {
        super("Falha ao buscar dados com client");
    }
}
