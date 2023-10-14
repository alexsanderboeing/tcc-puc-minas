package com.juntamedica.cep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CepNotFoundException extends RuntimeException {

    public CepNotFoundException(Long cep) {
        super(String.format("CEP %d não foi encontrado", cep));
    }
}
