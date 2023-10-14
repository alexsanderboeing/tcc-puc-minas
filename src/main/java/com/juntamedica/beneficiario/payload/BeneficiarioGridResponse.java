package com.juntamedica.beneficiario.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(staticName = "of")
public class BeneficiarioGridResponse {

    private Long id;
    private String codigo;
    private String nome;
    private Long operadoraId;
    private String operadora;

    public BeneficiarioGridResponse(Long id, String codigo, String nome, Long operadoraId, String operadora) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.operadoraId = operadoraId;
        this.operadora = operadora;
    }
}
