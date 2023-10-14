package com.juntamedica.processo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProcessoNotFoundException extends RuntimeException {

    public ProcessoNotFoundException() {
        super("Processo não encontrado.");
    }

    public ProcessoNotFoundException(Integer total) {
        super("Não há dados para gerar relatório");
    }

    public ProcessoNotFoundException(Long processoId) {
        super(String.format("Processo não encontrado. Código processo: %s", processoId));
    }
}
