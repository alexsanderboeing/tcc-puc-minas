package com.juntamedica.acao.payload;

import com.juntamedica.dominio.payload.DominioResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcaoResponse {

    private Long id;
    private String nome;
    private String nomeBotao;
    private String corBotao;
    private Long orderEmTela;
    private DominioResponse dmModeloTela;
}
