package com.juntamedica.anexo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnexoUploadErrorException extends RuntimeException {

    public AnexoUploadErrorException() {
        super("Falha ao fazer upload do anexo.");
    }

    public AnexoUploadErrorException(String erroMsg) {
        super(String.format("Falha ao fazer upload do anexo. Erro: %s", erroMsg));
    }
}
