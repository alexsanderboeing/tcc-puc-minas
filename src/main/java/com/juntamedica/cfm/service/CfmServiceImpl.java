package com.juntamedica.cfm.service;

import com.juntamedica.cfm.exception.CfmDadosMedicoErrorException;
import com.juntamedica.cfm.exception.CfmMedicoNotFoundException;
import com.juntamedica.cfm.factory.CfmFactory;
import com.juntamedica.cfm.payload.CfmResponse;
import com.juntamedica.webservice.cfm.CfmMensagemDecode;
import com.juntamedica.webservice.cfm.payload.CfmSOAPResponse;
import com.juntamedica.webservice.cfm.service.CfmClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class CfmServiceImpl implements CfmService {

    @Autowired
    private CfmClientService cfmClientService;

    @Autowired
    private CfmFactory cfmFactory;

    @Override
    public CfmResponse findCfmByConselhoNumeroAndConselhoUf(Integer crm, String uf) {
        CfmSOAPResponse response = cfmClientService.consultaInformacoes(crm, uf);

        return this.createFromXmlResponse(response);
    }

    private CfmResponse createFromXmlResponse(CfmSOAPResponse responseCfm) {
        if (Objects.isNull(responseCfm) || Objects.isNull(responseCfm.getDadosMedico())) {
            throw new CfmMedicoNotFoundException();
        }

        if (Objects.nonNull(responseCfm.getDadosMedico().getCodigoErro())) {
            try {
                Integer errorCode = Integer.parseInt(responseCfm.getDadosMedico().getCodigoErro());
                throw new CfmDadosMedicoErrorException(CfmMensagemDecode.decodeCodigoErro(errorCode));
            } catch (NumberFormatException formatException) {
                throw new CfmDadosMedicoErrorException(responseCfm.getDadosMedico().getCodigoErro());
            }
        }

        return cfmFactory.build(responseCfm);
    }
}
