package com.juntamedica.anexo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnexoBase64FileDownloadErrorException extends RuntimeException {

    public AnexoBase64FileDownloadErrorException() {
        super("Falha construir arquivo para download.");
    }

    public AnexoBase64FileDownloadErrorException(String erroMsg) {
        super(String.format("Falha construir arquivo para download. Erro: %s", erroMsg));
    }
}
