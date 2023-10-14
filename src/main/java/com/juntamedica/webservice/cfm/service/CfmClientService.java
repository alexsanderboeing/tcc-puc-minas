package com.juntamedica.webservice.cfm.service;

import com.juntamedica.webservice.cfm.payload.CfmSOAPResponse;

public interface CfmClientService {

    CfmSOAPResponse consultaInformacoes(Integer crm, String uf);
}
