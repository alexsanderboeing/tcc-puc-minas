package com.juntamedica.webservice.cfm.factory;

import com.juntamedica.cfm.payload.CfmRequest;
import com.juntamedica.webservice.cfm.payload.CfmSOAPRequest;
import org.springframework.stereotype.Component;

@Component
public class CfmClientFactory {

    public CfmSOAPRequest build(CfmRequest request) {
        return CfmSOAPRequest.builder()
                .crm(request.getCrm())
                .uf(request.getUf())
                .chave(request.getChave().toUpperCase())
                .build();
    }

    public CfmRequest buildRequest(Integer crm, String uf, String chave) {
        return CfmRequest.builder()
                .crm(crm)
                .uf(uf)
                .chave(chave)
                .build();
    }
}
