package com.juntamedica.usuario.payload;

import com.juntamedica.usuario.annotation.ValidPassword;
import lombok.Data;
import javax.validation.constraints.NotNull;
@Data
public class UsuarioResetPasswordRequest {

    @NotNull
    @ValidPassword
    private String password;

    @NotNull
    @ValidPassword
    private String newPassword;

    @NotNull
    @ValidPassword
    private String confirmNewPassword;
}
