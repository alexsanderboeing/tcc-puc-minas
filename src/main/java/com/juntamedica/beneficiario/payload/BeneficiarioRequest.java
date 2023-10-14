package com.juntamedica.beneficiario.payload;

import com.juntamedica.anexo.payload.AnexoRequest;
import com.juntamedica.contato.payload.ContatoRequest;
import com.juntamedica.endereco.payload.EnderecoRequest;
import com.juntamedica.utils.validator.ValidLocalDateTime;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BeneficiarioRequest {

    @Size(min = 1, max = 17, message = "Código do beneficiário teve conter entre 1 e 17 digitos")
    private String codigo;

    private Long id;
    private Boolean preCadastro;
    private String nome;
    private Long dmSexoBeneficiarioId;

    private LocalDateTime dataNascimento;

    @Size(max = 500, message = "O campo observação de beneficiario pode conter até até 500 caracteres")
    private String observacao;

    @Valid
    private EnderecoRequest endereco;
    private Long operadoraId;

    @Valid
    private List<ContatoRequest> contatoList;

    private List<AnexoRequest> anexoList;
    private String searchTerm;
    private Boolean novoCodigo;
}
