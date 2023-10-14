package com.juntamedica.processo.payload;

import lombok.Data;

@Data
public class ProcessoAprovarParecerOperadoraRequest {

    private Long statusEtapaDestinoId;
    private Long avaliacaoTrabalho;
    private Long avaliacaoDesempatador;
}
