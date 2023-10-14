package com.juntamedica.webservice.icarta.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ICartaResponse {

    private String status;
    private String descricao;
}
