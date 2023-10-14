package com.juntamedica.cfm.service;

import com.juntamedica.cfm.payload.CfmResponse;

public interface CfmService {

    CfmResponse findCfmByConselhoNumeroAndConselhoUf(Integer crm, String uf);
}
