package com.juntamedica.webservice.dadosautorizacao.service;

import com.juntamedica.webservice.dadosautorizacao.DadosAutorizacaoClient;
import com.juntamedica.webservice.dadosautorizacao.exception.DadosAutorizacaoForbiddenException;
import com.juntamedica.webservice.dadosautorizacao.exception.DadosAutorizacaoNotFoundException;
import com.juntamedica.webservice.dadosautorizacao.factory.DadosAutorizacaoFactory;
import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoConsultaRequest;
import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoRequest;
import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoResponse;
import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoTokenRequest;
import com.juntamedica.webservice.dadosautorizacao.payload.DadosAutorizacaoTokenResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class DadosAutorizacaoClientServiceImpl implements DadosAutorizacaoClientService {

    @Autowired
    private DadosAutorizacaoClient dadosAutorizacaoClient;

    @Autowired
    private DadosAutorizacaoFactory dadosAutorizacaoFactory;

    @Override
    public DadosAutorizacaoResponse findByNumeroGuia(DadosAutorizacaoRequest dadosAutorizacaoRequest) {
        if (this.isConsultaInvalid(dadosAutorizacaoRequest)) {
            return null;
        }

        String oAuthToken = this.getOAuthToken(dadosAutorizacaoRequest);
        URI url = URI.create(dadosAutorizacaoRequest.getUrlConsultaAutorizacao());
        DadosAutorizacaoConsultaRequest request =
                dadosAutorizacaoFactory.buildConsultaRequest(dadosAutorizacaoRequest.getNumeroGuia());

        try {
            DadosAutorizacaoResponse result = dadosAutorizacaoClient.consultar(url, oAuthToken, request);

            if (this.responseIsNull(result)) {
                throw new DadosAutorizacaoNotFoundException();
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DadosAutorizacaoNotFoundException();
        }
    }

    private String getOAuthToken(DadosAutorizacaoRequest dadosAutorizacaoRequest) {
        try {
            String authHeader = this.getAuthorizationHeader(dadosAutorizacaoRequest);

            URI urlOAuthToken = new URI(dadosAutorizacaoRequest.getUrlAuth());
            DadosAutorizacaoTokenRequest tokenRequest = new DadosAutorizacaoTokenRequest("client_credentials", "read");
            DadosAutorizacaoTokenResponse response =
                    dadosAutorizacaoClient.getTokenOAuth(urlOAuthToken, authHeader, tokenRequest);

            if (Objects.isNull(response.getOAuthAcessToken())) {
                throw new DadosAutorizacaoNotFoundException();
            }

            return response.getOAuthAcessToken();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DadosAutorizacaoForbiddenException();
        }
    }

    private String getAuthorizationHeader(DadosAutorizacaoRequest dadosAutorizacaoRequest) {
        String clientId = dadosAutorizacaoRequest.getClientId();
        String clientSecret = dadosAutorizacaoRequest.getClientSecret();
        String auth = String.format("%s:%s", clientId, clientSecret);

        return String.format("Basic %s", new String(Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8))));
    }

    private boolean responseIsNull(DadosAutorizacaoResponse response) {
        return Stream.of(
                        response.getProcedimentoJaRealizado(),
                        response.getCaraterAtendimento(),
                        response.getPlanoNaoRegulamentado(),
                        response.getPossuiNip(),
                        response.getPossuiLiminar(),
                        response.getIndicacaoClinica(),
                        response.getJustificativaOperadora(),
                        response.getItens(),
                        response.getBeneficiario(),
                        response.getProfissionalAssistente(),
                        response.getAuditor())
                .allMatch(Objects::isNull);
    }

    private boolean isConsultaInvalid(DadosAutorizacaoRequest dadosAutorizacaoRequest) {
        return Objects.isNull(dadosAutorizacaoRequest.getClientId())
                || Objects.isNull(dadosAutorizacaoRequest.getClientSecret())
                || Objects.isNull(dadosAutorizacaoRequest.getUrlConsultaAutorizacao())
                || Objects.isNull(dadosAutorizacaoRequest.getUrlAuth())
                || Objects.isNull(dadosAutorizacaoRequest.getNumeroGuia());
    }
}
