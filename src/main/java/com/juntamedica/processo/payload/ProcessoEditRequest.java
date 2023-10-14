package com.juntamedica.processo.payload;

import com.juntamedica.utils.validator.ValidLocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProcessoEditRequest {

    @NotNull(message = "Id do processo deve ser informado")
    private Long id;

    private Boolean modoRascunho;
    private LocalDateTime inicioProcesso;
    private LocalDateTime conclusaoProcesso;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataSolicitacao;

    @ValidLocalDateTime(message = "Data 'Período Prazo Inicial(ANS)' está inválida.")
    private LocalDateTime prazoInicialAns;

    @ValidLocalDateTime(message = "Data 'Período Prazo Final(ANS)' está inválida.")
    private LocalDateTime prazoFinalAns;

    private LocalDateTime dataParecer;
    private Boolean prioridade;
    private Boolean prazoGuiaLegivel;
    private Boolean dataReprovacaoOperadora;
    private Boolean procedimentoRealizado;
    private Boolean possuiLiminar;
    private Boolean possuiNip;
    private Boolean planoRegulamentado;

    @Size(max = 500, message = "Indicação clínica pode conter até 500 caracteres")
    private String indicacaoClinica;

    private String parecerDesempatador;
    private String parecerDesempatadorRascunho;
    private String resumoAoBeneficiario;
    private String parecerCaraterSolicitacao;
    private Boolean necessarioMatFabDistrib;
    private Boolean podeMatOutroFab;
    private LocalDateTime dataCancelamento;
    private String justificativaOperadora;
    private String justificativaCancelamento;
    private Long avaliacaoTrabalho;
    private Long avaliacaoDesempatador;
    private Boolean documentacaoCompleta;
    private Boolean parecerVisivelOperadora;

    @Pattern(regexp = "^(\\d*)$", message = "Número do protocolo pode conter apenas números")
    @Size(max = 20, message = "Número do protocolo pode conter até 20 caracteres")
    private String numeroProtocolo;
}
