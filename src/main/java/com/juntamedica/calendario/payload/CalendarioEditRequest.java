package com.juntamedica.calendario.payload;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class CalendarioEditRequest {

    @NotNull(message = "Calendário id não pode estar vazio.")
    private Long id;

    @NotEmpty(message = "Nome não pode estar vazio.")
    @Size(max = 70, message = "O nome pode conter até 70 caracteres.")
    private String nome;

    @NotNull(message = "Ativo não pode estar vazio.")
    private Boolean ativo;
}
