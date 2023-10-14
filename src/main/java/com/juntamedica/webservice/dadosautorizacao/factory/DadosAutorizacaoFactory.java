package com.juntamedica.webservice.dadosautorizacao.factory;

import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoConsultaRequest;
import org.springframework.stereotype.Component;

@Component
public class DadosAutorizacaoFactory {

    public DadosAutorizacaoConsultaRequest buildConsultaRequest(String numeroPedidoGuia) {
        return DadosAutorizacaoConsultaRequest.builder()
                .numeroGuia(numeroPedidoGuia)
                .build();
    }
}
