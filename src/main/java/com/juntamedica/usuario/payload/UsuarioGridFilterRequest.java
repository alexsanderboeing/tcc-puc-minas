package com.juntamedica.usuario.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioGridFilterRequest {

    private String nome;
    private Boolean active;
    private String nomePerfil;
}
