package com.juntamedica.usuario.payload;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UsuarioGridResponse {

    private Long id;
    private String name;
    private String username;
    private String email;
    private Boolean expiredPassword;
    private Boolean active;
    private LocalDateTime createdAt;
}
