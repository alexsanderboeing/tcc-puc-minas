package com.juntamedica.webservice.ibge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IbgeEstadoNotFoundException extends RuntimeException {

    public IbgeEstadoNotFoundException() {
        super("Dados de estado, no IBGE, não encontrados");
    }

    public IbgeEstadoNotFoundException(String sigla) {
        super(String.format("Estado com sigla %s, não encontrado", sigla));
    }
}
