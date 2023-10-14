package com.juntamedica.auditor.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditorFromRestricaoRequest {

    private Long id;

    @NotNull(message = "Campo nome é obrigatório")
    @Size(min = 1, max = 120, message = "Campo nome, deve conter entre 1 e 120 caracteres")
    private String nome;

    @NotNull(message = "Campo conselhoList é obrigatório")
    @NotEmpty(message = "Campo conselhoList, deve conter ao menos 1 conselho")
    @Valid
    private List<AuditorConselhoFromRestricaoRequest> conselhoList;
}
