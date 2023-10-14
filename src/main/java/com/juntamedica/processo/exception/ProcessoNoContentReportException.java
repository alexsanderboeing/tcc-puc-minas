package com.juntamedica.processo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ProcessoNoContentReportException extends RuntimeException {

    public ProcessoNoContentReportException() {
        super("Não há dados para geração do relatório.");
    }
}
