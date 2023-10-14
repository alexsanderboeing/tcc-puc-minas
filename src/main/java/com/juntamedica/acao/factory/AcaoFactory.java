package com.juntamedica.acao.factory;

import com.juntamedica.acao.Acao;
import com.juntamedica.acao.payload.AcaoCreateRequest;
import com.juntamedica.acao.payload.AcaoRequest;
import com.juntamedica.acao.payload.AcaoResponse;
import com.juntamedica.dominio.Dominio;
import com.juntamedica.dominio.factory.DominioFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AcaoFactory {

    @Autowired
    private DominioFactory dominioFactory;

    public Acao build(AcaoCreateRequest acaoRequest) {
        return Acao.builder()
                .id(acaoRequest.getId())
                .nome(acaoRequest.getNome())
                .nomeBotao(acaoRequest.getNomeBotao())
                .corBotao(acaoRequest.getCorBotao())
                .ordemEmTela(acaoRequest.getOrderEmTela())
                .dmModeloTela(Dominio.builder().id(acaoRequest.getDmModeloTelaId()).build())
                .build();
    }

    public AcaoResponse buildResponse(Acao acao) {
        AcaoResponse acaoResponse = AcaoResponse.builder()
                .id(acao.getId())
                .nome(acao.getNome())
                .nomeBotao(acao.getNomeBotao())
                .corBotao(acao.getCorBotao())
                .orderEmTela(acao.getOrdemEmTela()).build();

        Dominio dmModeloTela = acao.getDmModeloTela();

        if (dmModeloTela != null) {
            acaoResponse.setDmModeloTela(dominioFactory.buildResponse(dmModeloTela));
        }

        return acaoResponse;
    }

    public List<AcaoResponse> buildResponseList(List<Acao> acaoList) {
        return acaoList.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    public Acao updateValues(Acao acao, AcaoRequest acaoRequest) {
        acao.setNome(acaoRequest.getNome());
        acao.setNomeBotao(acaoRequest.getNomeBotao());
        acao.setCorBotao(acaoRequest.getCorBotao());
        acao.setOrdemEmTela(acaoRequest.getOrderEmTela());
        acao.setDmModeloTela(Dominio.builder().id(acaoRequest.getDmModeloTelaId()).build());

        return acao;
    }
}
