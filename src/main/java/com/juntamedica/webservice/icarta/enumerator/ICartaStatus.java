package com.juntamedica.webservice.icarta.enumerator;

import com.juntamedica.notificacao.exception.ICartaStatusNotFound;
import lombok.Getter;

@Getter
public enum ICartaStatus {

    SUCESSO("00", "Sucesso"),
    CREDENCIAL_INVALIDA("01", "Credenciais inválidas"),
    EMAIL_INVALIDO("02", "Algum e-mail inválido"),
    ERRO("99", "Erro");

    private String status;
    private String descricao;

    public ICartaStatus setValue(String status) {
        switch (status) {
            case "00":
                return SUCESSO;
            case "01":
                return CREDENCIAL_INVALIDA;
            case "02":
                return EMAIL_INVALIDO;
            case "99":
                return ERRO;
            default:
                throw new ICartaStatusNotFound(status);
        }
    }

    ICartaStatus(String status, String descricao) {
        this.status = status;
        this.descricao = descricao;
    }

    public static ICartaStatus from(String value) {
        for (ICartaStatus iCartaStatus : values()) {
            if (iCartaStatus.status.equalsIgnoreCase(value)) {
                return iCartaStatus;
            }
        }

        throw new ICartaStatusNotFound();
    }
}
