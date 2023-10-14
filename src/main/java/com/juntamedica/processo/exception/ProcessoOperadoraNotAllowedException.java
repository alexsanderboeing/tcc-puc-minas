package com.juntamedica.processo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ProcessoOperadoraNotAllowedException extends RuntimeException {

    public ProcessoOperadoraNotAllowedException() {
        super("Sem permissão para visualizar dados do processo.");
    }

    public ProcessoOperadoraNotAllowedException(Long userOperadoraId) {
        super(String.format("Sem permissão para visualizar dados do processo. Código operadora: %s", userOperadoraId));
    }
}
