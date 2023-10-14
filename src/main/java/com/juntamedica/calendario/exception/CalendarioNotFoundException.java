package com.juntamedica.calendario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CalendarioNotFoundException extends RuntimeException {

    public CalendarioNotFoundException() {
        super("Calendário não foi encontrato.");
    }

    public CalendarioNotFoundException(Long calendarioId) {
        super(String.format("Calendário não foi encontrato. Código calendário: %o", calendarioId));
    }
}
