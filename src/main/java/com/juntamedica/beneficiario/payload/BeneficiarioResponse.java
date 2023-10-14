package com.juntamedica.beneficiario.payload;

import com.juntamedica.anexo.payload.AnexoResponse;
import com.juntamedica.contato.payload.ContatoResponse;
import com.juntamedica.endereco.payload.EnderecoResponse;
import com.juntamedica.operadora.payload.OperadoraComboResponse;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BeneficiarioResponse {

    private Long id;
    private String codigo;
    private String nome;
    private Long dmSexoBeneficiarioId;
    private String dmSexoBeneficiarioDescricao;
    private LocalDateTime dataNascimento;
    private String observacao;
    private Long operadoraId;
    private OperadoraComboResponse operadora;
    private EnderecoResponse endereco;
    private List<ContatoResponse> contatoList;
    private List<AnexoResponse> anexoList;
}
