package com.juntamedica.webservice.ibge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IbgeMunicipioNotFoundException extends RuntimeException {

    public IbgeMunicipioNotFoundException() {
        super("Munícipios não encontrados");
    }

    public IbgeMunicipioNotFoundException(String termo, String by) {
        super(String.format("Munícipios, para %s %s, não encontrado", by, termo));
    }
}
