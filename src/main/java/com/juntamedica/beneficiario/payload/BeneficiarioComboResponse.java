package com.juntamedica.beneficiario.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(staticName = "of")
public class BeneficiarioComboResponse {

    private Long id;
    private String codigo;
    private String nome;

    public BeneficiarioComboResponse(Long id, String codigo, String nome) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
    }
}
