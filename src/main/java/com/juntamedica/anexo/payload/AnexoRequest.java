package com.juntamedica.anexo.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnexoRequest {

    private Long id;
    private String uuid;
    private Long classificacaoAnexoId;
    private String nome;
    private String mimeType;
    private String base64;
    private Boolean isNewFile;
    private Boolean deleted;
    private String extensao;
}
