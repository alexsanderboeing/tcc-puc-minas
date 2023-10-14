package com.juntamedica.processo.payload;

import lombok.Data;

@Data
public class ProcessoDesempatadorDefinirRequest {

    private Long processoId;
    private Long operadoraId;
    private Long desempatadorId;
    private Long statusEtapaDestinoId;
    private Boolean favoritar;
}
