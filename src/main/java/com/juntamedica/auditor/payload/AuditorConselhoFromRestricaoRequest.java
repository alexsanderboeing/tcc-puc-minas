package com.juntamedica.auditor.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditorConselhoFromRestricaoRequest {

    private Long id;

    @NotNull(message = "Campo dmTipoConselhoId é obrigatório")
    private Long dmTipoConselhoId;

    @NotNull(message = "Campo numeroConselho é obrigatório")
    @NotEmpty(message = "Campo numeroConselho não pode estar vazio")
    private String numeroConselho;

    @NotNull(message = "Campo ufConselho é obrigatório")
    @NotEmpty(message = "Campo ufConselho não pode estar vazio")
    private String ufConselho;
}
