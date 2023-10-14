package com.juntamedica.webservice.cfm.service;

import com.juntamedica.cfm.exception.CfmChaveConsultaNotFoundException;
import com.juntamedica.cfm.exception.CfmConsultaErrorException;
import com.juntamedica.cfm.payload.CfmRequest;
import com.juntamedica.webservice.cfm.CfmClient;
import com.juntamedica.webservice.cfm.factory.CfmClientFactory;
import com.juntamedica.webservice.cfm.payload.CfmSOAPResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class CfmClientServiceImpl implements CfmClientService {

    @Value("${cfm.chave}")
    private String chaveCfm;

    @Autowired
    private CfmClient cfmClient;

    @Autowired
    private CfmClientFactory cfmClientFactory;

    @Override
    public CfmSOAPResponse consultaInformacoes(Integer crm, String uf) {
        this.validChave();

        try {
            CfmRequest request = cfmClientFactory.buildRequest(crm, uf.toUpperCase(), chaveCfm);

            return cfmClient.consultaInformacoes(cfmClientFactory.build(request));
        } catch (FeignException fe) {
            fe.printStackTrace();
            throw new CfmConsultaErrorException();
        }
    }

    private void validChave() {
        if (Objects.isNull(chaveCfm)) {
            throw new CfmChaveConsultaNotFoundException();
        }
    }
}
