package com.juntamedica.processo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProcessoNoSuchFieldException extends RuntimeException {

    public ProcessoNoSuchFieldException() {
        super("Campo do processo não encontrado.");
    }

    public ProcessoNoSuchFieldException(String fieldName) {
        super(String.format("Campo do processo não encontrado. Nome do campo: '%s'", fieldName));
    }
}
