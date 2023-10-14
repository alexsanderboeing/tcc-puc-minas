package com.juntamedica.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException() {
        super("Usuário não encontrado");
    }

    public UsuarioNotFoundException(String login) {
        super(String.format("Usuário, %s, não foi encontrado", login));
    }

    public UsuarioNotFoundException(Long userId) {
        super(String.format("Usuário, com id %d, não foi encontrado", userId));
    }
}
