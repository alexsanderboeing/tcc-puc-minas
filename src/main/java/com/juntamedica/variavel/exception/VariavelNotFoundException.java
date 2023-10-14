package com.juntamedica.variavel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VariavelNotFoundException extends RuntimeException {

    public VariavelNotFoundException() {
        super("Variável não encontrada");
    }

    public VariavelNotFoundException(String variavelTextId) {
        super(String.format("Variável não encontrada. Código da variável: %s", variavelTextId));
    }

    public VariavelNotFoundException(Long variavelId) {
        super(String.format("Variável não encontrada. Id da variável: %d", variavelId));
    }
}
