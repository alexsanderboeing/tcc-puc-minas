package com.juntamedica.anexo.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnexoDownloadResponse {

    private Long id;
    private String nome;
    private String mimeType;
    private String base64;
    private String extensao;
}
