package com.juntamedica.anexo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnexoNotFoundException extends RuntimeException {

    public AnexoNotFoundException() {
        super("Anexo não encontrado.");
    }

    public AnexoNotFoundException(String uuid) {
        super(String.format("Anexo não encontrado. Uuid anexo: %s", uuid));
    }

    public AnexoNotFoundException(Long anexoId) {
        super(String.format("Anexo não encontrado. Código anexo: %o", anexoId));
    }
}
