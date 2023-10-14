package com.juntamedica.webservice.icarta.enumerator;

import com.juntamedica.notificacao.exception.ICartaStatusNotFound;
import lombok.Getter;

@Getter
public enum ICartaStatusEmail {

    SUCESSO("00", "Sucesso"),
    JA_UTILIZADO("01", "ID do e-mail já utilizados"),
    ERRO("99", "Erro");

    private String status;
    private String descricao;

    public ICartaStatusEmail setValue(String status) {
        switch (status) {
            case "00":
                return SUCESSO;
            case "01":
                return JA_UTILIZADO;
            case "99":
                return ERRO;
            default:
                throw new ICartaStatusNotFound(status);
        }
    }

    ICartaStatusEmail(String status, String descricao) {
        this.status = status;
        this.descricao = descricao;
    }

    public static ICartaStatusEmail from(String value) {
        for (ICartaStatusEmail iCartaStatusEmail : values()) {
            if (iCartaStatusEmail.status.equalsIgnoreCase(value)) {
                return iCartaStatusEmail;
            }
        }

        throw new ICartaStatusNotFound();
    }
}
