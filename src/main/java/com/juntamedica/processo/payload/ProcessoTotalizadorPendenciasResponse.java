package com.juntamedica.processo.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoTotalizadorPendenciasResponse {

    private Long minhasPendencias;
    private Long pendenciasArea;
    private Long finalizados;
    private Long total;

    public ProcessoTotalizadorPendenciasResponse(Long minhasPendencias, Integer pendenciasArea,
                                                 Long finalizados, Long total) {
        this.minhasPendencias = minhasPendencias;
        this.pendenciasArea = Long.valueOf(pendenciasArea);
        this.finalizados = finalizados;
        this.total = total;
    }
}
