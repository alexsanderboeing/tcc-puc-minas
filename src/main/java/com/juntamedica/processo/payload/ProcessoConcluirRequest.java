package com.juntamedica.processo.payload;

import lombok.Data;

@Data
public class ProcessoConcluirRequest {

    private Long processoId;
    private Long statusEtapaDestinoId;
}
