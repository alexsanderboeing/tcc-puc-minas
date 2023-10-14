package com.juntamedica.calendario.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalendarioResponse {

    private Long id;
    private String nome;
    private Boolean ativo;
}
