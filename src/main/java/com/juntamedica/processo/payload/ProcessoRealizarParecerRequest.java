package com.juntamedica.processo.payload;

import com.juntamedica.processoitem.payload.ProcessoItemRequest;
import lombok.Data;
import java.util.List;

@Data
public class ProcessoRealizarParecerRequest {

    private Boolean podeMatOutroFab;
    private Boolean necessarioMatFabDistrib;
    private String itensExclusivos;
    private String parecerDesempatador;
    private String parecerCaraterSolicitacao;
    private List<ProcessoItemRequest> processoItemList;
}
