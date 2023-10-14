package com.juntamedica.acao.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AcaoCreateRequest {

    private Long id;

    @NotEmpty(message = "Nome não pode estar vazio.")
    private String nome;
    private String nomeBotao;
    private String corBotao;

    @JsonIgnore
    private Long orderEmTela;

    @NotNull(message = "ID Modelo de Tela não pode ser nulo.")
    private Long dmModeloTelaId;
}
