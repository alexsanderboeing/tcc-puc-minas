package com.juntamedica.variavel.factory;

import com.juntamedica.variavel.Variavel;
import com.juntamedica.variavel.payload.VariavelRequest;
import com.juntamedica.variavel.payload.VariavelResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VariavelFactory {

    public Variavel build(VariavelRequest variavelRequest) {
        return Variavel.builder()
                .id(variavelRequest.getId())
                .chave(variavelRequest.getChave())
                .nome(variavelRequest.getNome())
                .descricao(variavelRequest.getDescricao())
                .build();
    }

    public VariavelResponse buildResponse(Variavel variavel) {
        return VariavelResponse.builder()
                .id(variavel.getId())
                .chave(variavel.getChave())
                .nome(variavel.getNome())
                .descricao(variavel.getDescricao())
                .build();
    }

    public List<VariavelResponse> buildResponseList(List<Variavel> variavelList) {
        return variavelList.stream().map(this::buildResponse).collect(Collectors.toList());
    }
}
