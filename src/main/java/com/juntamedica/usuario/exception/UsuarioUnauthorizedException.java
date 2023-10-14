package com.juntamedica.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UsuarioUnauthorizedException extends RuntimeException {

    public UsuarioUnauthorizedException() {
        super("Usuário não autorizado.");
    }
}
