package com.juntamedica.processo.payload;

import lombok.Data;

@Data
public class ProcessoCancelRequest {

    private Long dmMotivoCancelId;
    private Long dmCancelamentoMotivadoId;
    private Long dmCobrarProcessoId;
    private String justificativa;
    private Long statusEtapaDestinoId;
}
