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
public class IbgeMunicipioResponse {

    @JsonAlias("id")
    private Long codigoIbge;

    private IbgeMicrorregiaoResponse microrregiao;
    private String nome;
}
