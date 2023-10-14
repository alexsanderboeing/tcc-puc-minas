package com.juntamedica.webservice.dadosautorizacao.payload;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class DadosAutorizacaoItensResponse {

    private String codigo;

    private String descricao;

    private String valor;

    private String quantidade;

    @JsonAlias("tipo_item")
    private String tipoItem;

    @JsonAlias("parecer_operadora")
    private String parecerOperadora;
}
