package com.juntamedica.webservice.dadosautorizacao.service;

import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoRequest;
import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoResponse;

public interface DadosAutorizacaoClientService {

    DadosAutorizacaoResponse findByNumeroGuia(DadosAutorizacaoRequest dadosAutorizacaoRequest);
}
