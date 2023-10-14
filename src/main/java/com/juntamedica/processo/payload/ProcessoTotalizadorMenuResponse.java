package com.juntamedica.processo.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoTotalizadorMenuResponse {
    private Long totalNotificacao;
    private Long totalMensagem;
    private Long totalAnexo;
}
