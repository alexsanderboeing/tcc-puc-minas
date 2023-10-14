package com.juntamedica.processo.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessoDesempatadoresResponse {

    private Long id;
    private String miniCurriculo;
    private String nome;
}
