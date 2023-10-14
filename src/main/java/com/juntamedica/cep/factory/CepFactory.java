package com.juntamedica.cep.factory;

import com.juntamedica.cep.Cep;
import com.juntamedica.cep.payload.CepResponse;
import com.juntamedica.webservice.viacep.payload.ViaCepResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CepFactory {

    public CepResponse buildResponse(Cep cep) {
        return CepResponse.builder()
                .id(cep.getId())
                .cep(cep.getCodigo())
                .logradouro(cep.getLogradouro())
                .complemento(cep.getComplemento())
                .bairro(cep.getBairro())
                .localidade(cep.getLocalidade())
                .uf(cep.getUf())
                .ibge(cep.getIbge())
                .gia(cep.getGia())
                .ddd(cep.getDdd())
                .siafi(cep.getSiafi())
                .build();
    }

    public Cep build(ViaCepResponse viaCepResponse) {
        return Cep.builder()
                .id(viaCepResponse.getId())
                .codigo(Long.valueOf(
                        StringUtils.replace(viaCepResponse.getCep(), "-", "")))
                .logradouro(viaCepResponse.getLogradouro())
                .complemento(viaCepResponse.getComplemento())
                .bairro(viaCepResponse.getBairro())
                .localidade(viaCepResponse.getLocalidade())
                .uf(viaCepResponse.getUf())
                .ibge(viaCepResponse.getIbge())
                .gia(viaCepResponse.getGia())
                .ddd(viaCepResponse.getDdd())
                .siafi(viaCepResponse.getSiafi())
                .build();
    }
}
