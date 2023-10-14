package com.juntamedica.auditor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuditorNotFoundException extends RuntimeException {

    public AuditorNotFoundException() {
        super("Auditor não encontrado.");
    }

    public AuditorNotFoundException(Long auditorId) {
        super(String.format("Auditor não encontrado. Código auditor: %o", auditorId));
    }
}
