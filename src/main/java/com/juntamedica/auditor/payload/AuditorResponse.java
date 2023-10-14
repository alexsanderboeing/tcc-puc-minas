package com.juntamedica.auditor.payload;

import com.juntamedica.auditorconselho.payload.AuditorConselhoResponse;
import com.juntamedica.auditorespecialidade.payload.AuditorEspecialidadeResponse;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class AuditorResponse {

    private Long id;
    private String nome;
    private String observacao;
    private Long operadoraId;
    private String operadoraNomeFantasia;
    private Boolean preCadastro;
    private List<AuditorEspecialidadeResponse> especialidadeList;
    private List<AuditorConselhoResponse> conselhoList;
}
