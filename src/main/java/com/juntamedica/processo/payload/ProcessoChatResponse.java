package com.juntamedica.processo.payload;

import com.juntamedica.fluxoprocesso.payload.FluxoProcessoResponse;
import com.juntamedica.statusetapa.payload.StatusEtapaResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessoChatResponse {

    private Long id;
    private FluxoProcessoResponse fluxoProcesso;
    private StatusEtapaResponse statusEtapa;
    private Long administrativoFescExtId;
    private Long auditorQualidadeExtId;
    private Long desempatadorId;
}
