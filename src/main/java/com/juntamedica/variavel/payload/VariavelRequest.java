package com.juntamedica.variavel.payload;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class VariavelRequest {

    @NotNull(message = "Campo id não pode estar vazio.")
    private Long id;

    @NotNull(message = "Campo chave não pode estar vazio.")
    private String chave;

    @NotNull(message = "Campo nome não pode estar vazio.")
    private String nome;

    @NotNull(message = "Campo descricao não pode estar vazio.")
    private String descricao;
}
