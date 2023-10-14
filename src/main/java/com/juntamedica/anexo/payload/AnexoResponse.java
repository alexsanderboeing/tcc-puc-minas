package com.juntamedica.anexo.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnexoResponse {

    private Long id;
    private Long classificacaoAnexoId;
    private String uuid;
    private String nome;
    private String mimeType;
    private Long usuarioId;
    private Boolean isNewFile;
    private Boolean deleted;
    private String extensao;
}
