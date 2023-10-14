package com.juntamedica.processo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoAlterarStatusRequest {

    private String comentario;

    @NotNull(message = "Acao ID não pode ser nulo.")
    private Long acaoId;
}
