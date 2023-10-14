package com.juntamedica.processo.payload;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class ProcessoAprovarParecerQualidadeRequest {

    private Long statusEtapaDestinoId;

    @NotNull(message = "Campo resumoBeneficiario deve ser preenchido")
    private String resumoBeneficiario;

    private String comentarioChat;

    @NotNull(message = "Acao ID não pode ser nulo.")
    private Long acaoId;
}
