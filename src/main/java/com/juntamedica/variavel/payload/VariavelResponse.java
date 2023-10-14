package com.juntamedica.variavel.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariavelResponse {

    private Long id;
    private String chave;
    private String nome;
    private String descricao;
}
