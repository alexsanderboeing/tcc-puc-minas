package com.juntamedica.webservice.dadosautorizacao;

import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoConsultaRequest;
import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoResponse;
import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoTokenRequest;
import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import java.net.URI;

@FeignClient(name = "dadosautorizacao", url = "dadosautorizacao")
public interface DadosAutorizacaoClient {

    @PostMapping
    DadosAutorizacaoTokenResponse getTokenOAuth(URI baseUri,
                                                @RequestHeader(value = "Authorization") String authorizationHeader,
                                                @RequestBody DadosAutorizacaoTokenRequest request);

    @PostMapping
    DadosAutorizacaoResponse consultar(URI baseUri,
                                       @RequestHeader(value = "Authorization") String authorizationHeader,
                                       @RequestBody DadosAutorizacaoConsultaRequest request);
}
