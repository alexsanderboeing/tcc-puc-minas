package com.juntamedica.usuario.payload;

import com.juntamedica.usuario.annotation.ValidPassword;
import lombok.Data;
import javax.validation.constraints.NotNull;
@Data
public class UsuarioValidatePasswordRequest {

    @NotNull
    @ValidPassword
    private String password;
}
