package com.juntamedica.token.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenCreationRequest implements Serializable {

    @NotEmpty(message = "Campo usuarioLogin não pode ser nulo")
    private String usuarioLogin;

    @NotEmpty(message = "Campo password não pode ser nulo")
    private String password;
}
