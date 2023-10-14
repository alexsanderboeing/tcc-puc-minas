package com.juntamedica.webservice.dadosautorizacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DadosAutorizacaoForbiddenException extends RuntimeException {

    public DadosAutorizacaoForbiddenException() {
        super("Token inválido");
    }
}
