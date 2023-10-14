package com.juntamedica.usuario.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponse {

    private Long id;
    private Boolean active;
    private String email;
    private String name;
    private String username;
    private String status;
    private Boolean expiredPassword;
    private Boolean autenticacaoDuploFator;
}
