package com.juntamedica.usuario.payload;

import com.juntamedica.usuario.annotation.ValidPassword;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UsuarioRequest {

    private Long id;

    @NotNull(message = "Campo active não pode ser nulo")
    private Boolean active;

    @NotEmpty(message = " Campo name não pode ser nulo")
    private String name;

    @NotEmpty(message = " Campo username não pode ser nulo")
    private String username;

    @NotEmpty(message = " Campo email não pode ser nulo")
    private String email;

    @ValidPassword
    private String password;

    @NotNull(message = "Campo autenticação duplo fator não pode ser nulo")
    private Boolean autenticacaoDuploFator;
}
