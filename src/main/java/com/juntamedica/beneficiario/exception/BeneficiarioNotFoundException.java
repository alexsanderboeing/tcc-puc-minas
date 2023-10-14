package com.juntamedica.beneficiario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeneficiarioNotFoundException extends RuntimeException {

    public BeneficiarioNotFoundException() {
        super("Beneficiário não encontrado.");
    }

    public BeneficiarioNotFoundException(Long beneficiarioId) {
        super(String.format("Beneficiário não encontrado. Código beneficiário: %o", beneficiarioId));
    }
}
