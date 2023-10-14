package com.juntamedica.processo.payload;

import lombok.Data;

@Data
public class ProcessoReprovarRequest {

    private Long statusEtapaDestinoId;
    private Long dmMotivoReprovarId;
    private Long dmReprovarMotivadoId;
    private String justificativa;
}
