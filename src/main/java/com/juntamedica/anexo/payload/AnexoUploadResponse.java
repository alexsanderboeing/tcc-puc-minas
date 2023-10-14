package com.juntamedica.anexo.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnexoUploadResponse {

    private Long id;
    private String uuid;
    private String nome;
}
