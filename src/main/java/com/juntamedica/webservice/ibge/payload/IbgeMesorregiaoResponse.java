package com.juntamedica.webservice.ibge.payload;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbgeMesorregiaoResponse {

    @JsonAlias("id")
    private Long codigIbge;

    @JsonAlias("UF")
    private IbgeEstadoResponse uf;

    private String nome;
}
