package com.juntamedica.cfm.factory;

import com.juntamedica.cfm.payload.CfmResponse;
import com.juntamedica.webservice.cfm.CfmMensagemDecode;
import com.juntamedica.webservice.cfm.payload.CfmSOAPResponse;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class CfmFactory {

    public CfmResponse build(CfmSOAPResponse response) {
        return CfmResponse.builder()
                .crm(response.getDadosMedico().getCrm())
                .dataAtualizacao(response.getDadosMedico().getDataAtualizacao())
                .especialidades(response.getDadosMedico().getEspecialidade())
                .nome(response.getDadosMedico().getNome())
                .situacao(Objects.nonNull(response.getDadosMedico())
                        ? CfmMensagemDecode.decodeSituacao(response.getDadosMedico().getSituacao())
                        : null)
                .tipoInscricao(Objects.nonNull(response.getDadosMedico())
                        ? CfmMensagemDecode.decodeTipoInscricao(response.getDadosMedico().getTipoInscricao())
                        : null)
                .uf(response.getDadosMedico().getUf())
                .build();
    }
}
