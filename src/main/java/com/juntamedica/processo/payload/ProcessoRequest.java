package com.juntamedica.processo.payload;

import com.juntamedica.anexo.payload.AnexoRequest;
import com.juntamedica.auditor.payload.AuditorRequest;
import com.juntamedica.beneficiario.payload.BeneficiarioRequest;
import com.juntamedica.consultor.payload.ConsultorRequest;
import com.juntamedica.fluxoprocesso.payload.FluxoProcessoRequest;
import com.juntamedica.processoareaatuacao.payload.ProcessoAreaAtuacaoRequest;
import com.juntamedica.processoitem.payload.ProcessoItemRequest;
import com.juntamedica.profissionalassistente.payload.ProfissionalAssistenteRequest;
import com.juntamedica.utils.validator.ValidLocalDateTime;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProcessoRequest {

    private Long id;
    private Boolean modoRascunho;
    private Boolean prioridade;
    private FluxoProcessoRequest fluxoProcesso;

    @Valid
    private BeneficiarioRequest beneficiario;

    @Valid
    private ProfissionalAssistenteRequest profissionalAssistente;

    @Valid
    private AuditorRequest auditor;

    @Valid
    private ConsultorRequest consultor;

    private Long desempatadorId;
    private Long administrativoFescExtId;
    private Long auditorQualidadeExtId;
    private Long administrativoOperadoraId;

    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Número pedido guia pode conter apenas números")
    @NotBlank(message = "Número pedido guia é obrigatório")
    @Size(min = 1, message = "Número pedido guia é obrigatório")
    @Size(max = 20, message = "Número pedido guia pode conter até 20 caracteres")
    private String numeroPedidoGuia;

    @Pattern(regexp = "^(\\d*)$", message = "Número do protocolo pode conter apenas números")
    @Size(max = 20, message = "Número do protocolo pode conter até 20 caracteres")
    private String numeroProtocolo;

    private Long operadoraContratanteId;

    @NotNull(message = "O Caráter de Atendimento é obrigatório.")
    private Long dmCaraterAtendimentoId;

    private LocalDateTime inicioProcesso;
    private Long contratoId;
    private LocalDateTime conclusaoProcesso;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataSolicitacao;

    @ValidLocalDateTime(message = "Data 'Período Prazo Inicial(ANS)' está inválida.")
    private LocalDateTime prazoInicialAns;

    @ValidLocalDateTime(message = "Data 'Período Prazo Final(ANS)' está inválida.")
    private LocalDateTime prazoFinalAns;

    private Long operadoraLayoutId;
    private LocalDateTime dataParecer;
    private Boolean procedimentoRealizado;
    private Boolean possuiLiminar;
    private Boolean possuiNip;
    private Boolean planoRegulamentado;
    private Boolean prazoGuiaLegivel;
    private List<ProcessoItemRequest> processoItemList;
    private List<AnexoRequest> anexoList;

    @Size(max = 500, message = "Indicação clínica pode conter até 500 caracteres")
    private String indicacaoClinica;
    private String parecerDesempatador;
    private String parecerDesempatadorRascunho;
    private String resumoAoBeneficiario;
    private String parecerCaraterSolicitacao;
    private Boolean necessarioMatFabDistrib;
    private Long statusEtapaId;
    private Boolean podeMatOutroFab;
    private LocalDateTime dataReprovacaoOperadora;
    private LocalDateTime dataCancelamento;
    private Long dmCancelamentoMotivadoId;
    private Long dmMotivoCancelProcessoId;
    private String justificativaOperadora;
    private String justificativaCancelamento;
    private Long avaliacaoTrabalho;
    private Long avaliacaoDesempatador;
    private Boolean documentacaoCompleta;
    private Long operadoraOrigemId;
    private Long operadoraSolicitanteId;
    private Long dmCobrarProcessoId;
    private List<ProcessoAreaAtuacaoRequest> areaAtuacaoList;
    private Boolean parecerVisivelOperadora;
}
