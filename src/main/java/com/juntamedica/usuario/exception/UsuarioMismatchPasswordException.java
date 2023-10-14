package com.juntamedica.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioMismatchPasswordException extends RuntimeException {

    public UsuarioMismatchPasswordException() {
        super("Falha ao autenticar usuário");
    }
}
