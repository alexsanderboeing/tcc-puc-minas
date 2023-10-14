package com.juntamedica.webservice.htmltopdf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HtmlToPdfErrorException extends RuntimeException {

    public HtmlToPdfErrorException() {
        super("Falha ao converter um html para pdf.");
    }

    public HtmlToPdfErrorException(String fileName) {
        super(String.format("Falha ao converter um html para pdf. Nome do arquivo html: %s", fileName));
    }
}
