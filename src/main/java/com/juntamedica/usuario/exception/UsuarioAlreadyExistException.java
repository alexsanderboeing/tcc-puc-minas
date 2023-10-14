package com.juntamedica.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsuarioAlreadyExistException extends RuntimeException {

    public UsuarioAlreadyExistException(String email) {
        super(String.format("Não foi possível realizar essa operação pois o usuário %s já existe", email));
    }
}
