package com.juntamedica.webservice.ibge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class IbgeClientErrorException extends RuntimeException {

    public IbgeClientErrorException() {
        super("Falha ao se comunicar com client, para busca de estado");
    }
}
