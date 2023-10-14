package com.juntamedica.processo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProcessoInvalidOperadoraOrigemException extends RuntimeException {

    public ProcessoInvalidOperadoraOrigemException() {
        super("A operadora de origem não foi informada no processo.");
    }
}
