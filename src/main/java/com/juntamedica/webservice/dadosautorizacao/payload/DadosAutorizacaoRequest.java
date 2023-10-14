package com.juntamedica.webservice.dadosautorizacao.payload;

import lombok.Data;

@Data
public class DadosAutorizacaoRequest {

    private String urlConsultaAutorizacao;
    private String urlAuth;
    private String clientId;
    private String clientSecret;
    private String numeroGuia;
}
