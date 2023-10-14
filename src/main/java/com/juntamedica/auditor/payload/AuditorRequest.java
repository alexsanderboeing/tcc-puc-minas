package com.juntamedica.auditor.payload;

import com.juntamedica.auditorconselho.payload.AuditorConselhoRequest;
import com.juntamedica.auditorespecialidade.payload.AuditorEspecialidadeRequest;
import lombok.Data;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AuditorRequest {

    private Long id;
    private String nome;

    @Size(max = 500, message = "O campo observação de auditor pode conter até até 500 caracteres")
    private String observacao;

    private Long operadoraId;
    private Boolean preCadastro;
    private List<AuditorEspecialidadeRequest> especialidadeList;
    private List<AuditorConselhoRequest> conselhoList;
    private Boolean novoConselho;
}
