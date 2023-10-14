package com.juntamedica.auditor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AuditorRestricaoDesempatadorException extends RuntimeException {

    public AuditorRestricaoDesempatadorException() {
        super("O auditor selecionado, possui uma restrição com o desempatador atual do processo");
    }
}
