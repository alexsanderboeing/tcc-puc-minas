package com.juntamedica.processo.payload;

import com.juntamedica.anexo.payload.AnexoRequest;
import lombok.Data;
import java.util.List;

@Data
public class ProcessoAnexoUploadRequest {

    private List<AnexoRequest> anexos;
}
