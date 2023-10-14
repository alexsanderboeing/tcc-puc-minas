package com.juntamedica.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UsuarioNotLoggedException extends RuntimeException {

    public UsuarioNotLoggedException() {
        super("O usuário logado não foi identificado!");
    }
}
