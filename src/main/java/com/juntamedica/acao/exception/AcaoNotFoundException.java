package com.juntamedica.acao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AcaoNotFoundException extends RuntimeException {

    public AcaoNotFoundException() {
        super("Ação não encontrada.");
    }

    public AcaoNotFoundException(Long acaoId) {
        super(String.format("Ação não encontrada. Código da ação: %o", acaoId));
    }
}
