package com.juntamedica.anexo.payload;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class AnexoResponseGrid {

	private Long id;
	private String classificacaoAnexo;
	private String mimeType;
	private String uuid;
	private String nome;
	private String usuario;
	private Long usuarioId;
    private LocalDateTime createdAt;
	private String extensao;
}
