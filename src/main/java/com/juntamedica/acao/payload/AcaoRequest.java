package com.juntamedica.acao.payload;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AcaoRequest {

    private Long id;

    @NotEmpty(message = "Nome não pode estar vazio.")
    private String nome;
    private String nomeBotao;
    private String corBotao;
    private Long orderEmTela;

    @NotNull(message = "ID Modelo de Tela não pode ser nulo.")
    private Long dmModeloTelaId;
}
