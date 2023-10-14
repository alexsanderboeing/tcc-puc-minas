package com.juntamedica.webservice.viacep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CepClientErrorException extends RuntimeException {

    public CepClientErrorException() {
        super("Falha ao se comunicar com client para busca de cep");
    }
}
