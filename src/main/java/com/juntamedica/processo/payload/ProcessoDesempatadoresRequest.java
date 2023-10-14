package com.juntamedica.processo.payload;

import com.juntamedica.desempatador.payload.DesempatadorRequest;
import lombok.Data;
import java.util.ArrayList;

@Data
public class ProcessoDesempatadoresRequest {

    private ArrayList<DesempatadorRequest> desempatadorList;
    private Long statusEtapaDestinoId;
}
