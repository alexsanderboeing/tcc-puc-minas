package com.juntamedica.processo.payload;

import com.juntamedica.processo.enumerator.Leitura;
import com.juntamedica.processo.enumerator.LeituraPapel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProcessoGridResponse {

    private Long id;
    private String slaProcesso;
    private String slaProcessoEmDias;
    private String slaProcessoFormatado;
    private Boolean slaProcessoEmAlerta;
    private Boolean modoRascunho;
    private Boolean prioridade;
    private String nomeBeneficiario;
    private String etapaProcesso;
    private Long statusEtapaId;
    private String statusEtapa;
    private String slaStatusProcesso;
    private String slaStatusProcessoFormatado;
    private Boolean slaStatusProcessoEmAlerta;
    private LocalDateTime dataInicioProcesso;
    private String numeroPedidoGuia;

    private Long administrativoFescExtId;
    private String nomeAdministrativoFesc;

    private String nomeDesempatador;
    private String nomeProfissionalAssistente;

    private Long auditorQualidadeExtId;
    private String nomeAuditorQualidadeFesc;

    private Long administrativoOperadoraId;
    private String nomeAdministrativoOperadora;

    private Long fluxoProcessoId;
    private String fluxoProcesso;
    private String operadora;
    private String documentacaoCompleta;
    private String percentualEvolucaoEtapas;
    private LocalDateTime dataEstimadaConclusao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataParecer;
    private LocalDateTime prazoInicialAns;
    private LocalDateTime prazoFinalAns;
    private LocalDateTime dataReprovacaoOperadora;
    private LocalDateTime conclusaoProcesso;
    private Long contratoId;
    private Long calendarioStatusEtapaId;
    private Boolean statusPreveSla;
    private String dominioContagemSlaStatus;
    private LocalDateTime dataCancelamento;
    private Long ordemProcessoEtapa;
    private Long operadoraSolicitanteId;
    private Leitura leitura;
    private Long quantidadeTotalNotificacoes;
    private Long quantidadeTotalNotificacoesLidas;
    private Long quantidadeTotalNotificacoesNaoLidas;
    private Long quantidadeTotalNotificacoesAdministrativoOperadora;
    private Long quantidadeTotalNotificacoesLidasAdministrativoOperadora;
    private Long quantidadeTotalNotificacoesNaoLidasAdministrativoOperadora;
    private Long quantidadeTotalNotificacoesAuditorOperadora;
    private Long quantidadeTotalNotificacoesLidasAuditorOperadora;
    private Long quantidadeTotalNotificacoesNaoLidasAuditorOperadora;
    private Long quantidadeTotalNotificacoesProfissionalAssistente;
    private Long quantidadeTotalNotificacoesLidasProfissionalAssistente;
    private Long quantidadeTotalNotificacoesNaoLidasProfissionalAssistente;
    private Long quantidadeTotalNotificacoesBeneficiario;
    private Long quantidadeTotalNotificacoesLidasBeneficiario;
    private Long quantidadeTotalNotificacoesNaoLidasBeneficiario;
    private Long quantidadeTotalNotificacoesAdministrativoFesc;
    private Long quantidadeTotalNotificacoesLidasAdministrativoFesc;
    private Long quantidadeTotalNotificacoesNaoLidasAdministrativoFesc;
    private Long quantidadeTotalNotificacoesAuditorQualidadeFesc;
    private Long quantidadeTotalNotificacoesLidasAuditorQualidadeFesc;
    private Long quantidadeTotalNotificacoesNaoLidasAuditorQualidadeFesc;
    private Long quantidadeTotalNotificacoesDesempatador;
    private Long quantidadeTotalNotificacoesLidasDesempatador;
    private Long quantidadeTotalNotificacoesNaoLidasDesempatador;
    private List<LeituraPapel> leituraPapelList;

    private Long quantidadeTotalTelegramas;
    private Long quantidadeTotalTelegramasLidas;
    private Long quantidadeTotalTelegramasNaoLidas;
    private Long quantidadeTotalTelegramasAdministrativoOperadora;
    private Long quantidadeTotalTelegramasLidosAdministrativoOperadora;
    private Long quantidadeTotalTelegramasNaoLidosAdministrativoOperadora;
    private Long quantidadeTotalTelegramasAuditorOperadora;
    private Long quantidadeTotalTelegramasLidosAuditorOperadora;
    private Long quantidadeTotalTelegramasNaoLidosAuditorOperadora;
    private Long quantidadeTotalTelegramasProfissionalAssistente;
    private Long quantidadeTotalTelegramasLidosProfissionalAssistente;
    private Long quantidadeTotalTelegramasNaoLidosProfissionalAssistente;
    private Long quantidadeTotalTelegramasBeneficiario;
    private Long quantidadeTotalTelegramasLidosBeneficiario;
    private Long quantidadeTotalTelegramasNaoLidosBeneficiario;
    private Long quantidadeTotalTelegramasAdministrativoFesc;
    private Long quantidadeTotalTelegramasLidosAdministrativoFesc;
    private Long quantidadeTotalTelegramasNaoLidosAdministrativoFesc;
    private Long quantidadeTotalTelegramasAuditorQualidadeFesc;
    private Long quantidadeTotalTelegramasLidosAuditorQualidadeFesc;
    private Long quantidadeTotalTelegramasNaoLidosAuditorQualidadeFesc;
    private Long quantidadeTotalTelegramasDesempatador;
    private Long quantidadeTotalTelegramasLidosDesempatador;
    private Long quantidadeTotalTelegramasNaoLidosDesempatador;

    public ProcessoGridResponse(Long id, Boolean modoRascunho, Boolean prioridade, String nomeBeneficiario,
                                String etapaProcesso, Long statusEtapaId, String statusEtapa, LocalDateTime dataInicioProcesso,
                                String numeroPedidoGuia, String nomeDesempatador, String nomeProfissionalAssistente,
                                Long fluxoProcessoId, String fluxoProcesso, String operadora, String documentacaoCompleta,
                                LocalDateTime dataCriacao, LocalDateTime dataParecer, LocalDateTime prazoInicialAns,
                                LocalDateTime prazoFinalAns, LocalDateTime dataReprovacaoOperadora,
                                LocalDateTime conclusaoProcesso, Long auditorQualidadeExtId, String nomeAuditorQualidadeFesc,
                                Long administrativoFescExtId, String nomeAdministrativoFesc,
                                Long administrativoOperadoraId, String nomeAdministrativoOperadora,
                                Long contratoId, Long calendarioStatusEtapaId, Boolean statusPreveSla,
                                String dominioContagemSlaStatus, LocalDateTime dataEstimadaConclusao,
                                LocalDateTime dataCancelamento, Long ordemProcessoEtapa, Long operadoraSolicitanteId,
                                Long quantidadeTotalNotificacoes, Long quantidadeTotalNotificacoesLidas,
                                Long quantidadeTotalNotificacoesNaoLidas,
                                Long quantidadeTotalNotificacoesAdministrativoOperadora,
                                Long quantidadeTotalNotificacoesLidasAdministrativoOperadora,
                                Long quantidadeTotalNotificacoesNaoLidasAdministrativoOperadora,
                                Long quantidadeTotalNotificacoesAuditorOperadora,
                                Long quantidadeTotalNotificacoesLidasAuditorOperadora,
                                Long quantidadeTotalNotificacoesNaoLidasAuditorOperadora,
                                Long quantidadeTotalNotificacoesProfissionalAssistente,
                                Long quantidadeTotalNotificacoesLidasProfissionalAssistente,
                                Long quantidadeTotalNotificacoesNaoLidasProfissionalAssistente,
                                Long quantidadeTotalNotificacoesBeneficiario,
                                Long quantidadeTotalNotificacoesLidasBeneficiario,
                                Long quantidadeTotalNotificacoesNaoLidasBeneficiario,
                                Long quantidadeTotalNotificacoesAdministrativoFesc,
                                Long quantidadeTotalNotificacoesLidasAdministrativoFesc,
                                Long quantidadeTotalNotificacoesNaoLidasAdministrativoFesc,
                                Long quantidadeTotalNotificacoesAuditorQualidadeFesc,
                                Long quantidadeTotalNotificacoesLidasAuditorQualidadeFesc,
                                Long quantidadeTotalNotificacoesNaoLidasAuditorQualidadeFesc,
                                Long quantidadeTotalNotificacoesDesempatador,
                                Long quantidadeTotalNotificacoesLidasDesempatador,
                                Long quantidadeTotalNotificacoesNaoLidasDesempatador,
                                Long quantidadeTotalTelegramas,
                                Long quantidadeTotalTelegramasLidas,
                                Long quantidadeTotalTelegramasNaoLidas,
                                Long quantidadeTotalTelegramasAdministrativoOperadora,
                                Long quantidadeTotalTelegramasLidosAdministrativoOperadora,
                                Long quantidadeTotalTelegramasNaoLidosAdministrativoOperadora,
                                Long quantidadeTotalTelegramasAuditorOperadora,
                                Long quantidadeTotalTelegramasLidosAuditorOperadora,
                                Long quantidadeTotalTelegramasNaoLidosAuditorOperadora,
                                Long quantidadeTotalTelegramasProfissionalAssistente,
                                Long quantidadeTotalTelegramasLidosProfissionalAssistente,
                                Long quantidadeTotalTelegramasNaoLidosProfissionalAssistente,
                                Long quantidadeTotalTelegramasBeneficiario,
                                Long quantidadeTotalTelegramasLidosBeneficiario,
                                Long quantidadeTotalTelegramasNaoLidosBeneficiario,
                                Long quantidadeTotalTelegramasAdministrativoFesc,
                                Long quantidadeTotalTelegramasLidosAdministrativoFesc,
                                Long quantidadeTotalTelegramasNaoLidosAdministrativoFesc,
                                Long quantidadeTotalTelegramasAuditorQualidadeFesc,
                                Long quantidadeTotalTelegramasLidosAuditorQualidadeFesc,
                                Long quantidadeTotalTelegramasNaoLidosAuditorQualidadeFesc,
                                Long quantidadeTotalTelegramasDesempatador,
                                Long quantidadeTotalTelegramasLidosDesempatador,
                                Long quantidadeTotalTelegramasNaoLidosDesempatador
                                ) {
        this.id = id;
        this.modoRascunho = modoRascunho;
        this.prioridade = prioridade;
        this.nomeBeneficiario = nomeBeneficiario;
        this.etapaProcesso = etapaProcesso;
        this.statusEtapaId = statusEtapaId;
        this.statusEtapa = statusEtapa;
        this.dataInicioProcesso = dataInicioProcesso;
        this.numeroPedidoGuia = numeroPedidoGuia;
        this.nomeDesempatador = nomeDesempatador;
        this.nomeProfissionalAssistente = nomeProfissionalAssistente;
        this.fluxoProcessoId = fluxoProcessoId;
        this.fluxoProcesso = fluxoProcesso;
        this.operadora = operadora;
        this.documentacaoCompleta = documentacaoCompleta;
        this.dataCriacao = dataCriacao;
        this.dataParecer = dataParecer;
        this.prazoInicialAns = prazoInicialAns;
        this.prazoFinalAns = prazoFinalAns;
        this.dataReprovacaoOperadora = dataReprovacaoOperadora;
        this.percentualEvolucaoEtapas = this.modoRascunho ? "0%" : this.percentualEvolucaoEtapas;
        this.slaProcesso = "0";
        this.slaStatusProcesso = "";
        this.conclusaoProcesso = conclusaoProcesso;
        this.auditorQualidadeExtId = auditorQualidadeExtId;
        this.nomeAuditorQualidadeFesc = nomeAuditorQualidadeFesc;
        this.administrativoFescExtId = administrativoFescExtId;
        this.nomeAdministrativoFesc = nomeAdministrativoFesc;
        this.administrativoOperadoraId = administrativoOperadoraId;
        this.nomeAdministrativoOperadora = nomeAdministrativoOperadora;
        this.contratoId = contratoId;
        this.calendarioStatusEtapaId = calendarioStatusEtapaId;
        this.statusPreveSla = statusPreveSla;
        this.dominioContagemSlaStatus = dominioContagemSlaStatus;
        this.dataEstimadaConclusao = dataEstimadaConclusao;
        this.dataCancelamento = dataCancelamento;
        this.ordemProcessoEtapa = ordemProcessoEtapa;
        this.operadoraSolicitanteId = operadoraSolicitanteId;
        this.quantidadeTotalNotificacoes = quantidadeTotalNotificacoes;
        this.quantidadeTotalNotificacoesLidas = quantidadeTotalNotificacoesLidas;
        this.quantidadeTotalNotificacoesNaoLidas = quantidadeTotalNotificacoesNaoLidas;
        this.quantidadeTotalNotificacoesAdministrativoOperadora = quantidadeTotalNotificacoesAdministrativoOperadora;
        this.quantidadeTotalNotificacoesLidasAdministrativoOperadora
                = quantidadeTotalNotificacoesLidasAdministrativoOperadora;
        this.quantidadeTotalNotificacoesNaoLidasAdministrativoOperadora
                = quantidadeTotalNotificacoesNaoLidasAdministrativoOperadora;
        this.quantidadeTotalNotificacoesAuditorOperadora = quantidadeTotalNotificacoesAuditorOperadora;
        this.quantidadeTotalNotificacoesLidasAuditorOperadora = quantidadeTotalNotificacoesLidasAuditorOperadora;
        this.quantidadeTotalNotificacoesNaoLidasAuditorOperadora = quantidadeTotalNotificacoesNaoLidasAuditorOperadora;
        this.quantidadeTotalNotificacoesProfissionalAssistente = quantidadeTotalNotificacoesProfissionalAssistente;
        this.quantidadeTotalNotificacoesLidasProfissionalAssistente
                = quantidadeTotalNotificacoesLidasProfissionalAssistente;
        this.quantidadeTotalNotificacoesNaoLidasProfissionalAssistente
                = quantidadeTotalNotificacoesNaoLidasProfissionalAssistente;
        this.quantidadeTotalNotificacoesBeneficiario = quantidadeTotalNotificacoesBeneficiario;
        this.quantidadeTotalNotificacoesLidasBeneficiario = quantidadeTotalNotificacoesLidasBeneficiario;
        this.quantidadeTotalNotificacoesNaoLidasBeneficiario = quantidadeTotalNotificacoesNaoLidasBeneficiario;
        this.quantidadeTotalNotificacoesAdministrativoFesc = quantidadeTotalNotificacoesAdministrativoFesc;
        this.quantidadeTotalNotificacoesLidasAdministrativoFesc = quantidadeTotalNotificacoesLidasAdministrativoFesc;
        this.quantidadeTotalNotificacoesNaoLidasAdministrativoFesc
                = quantidadeTotalNotificacoesNaoLidasAdministrativoFesc;
        this.quantidadeTotalNotificacoesAuditorQualidadeFesc = quantidadeTotalNotificacoesAuditorQualidadeFesc;
        this.quantidadeTotalNotificacoesLidasAuditorQualidadeFesc = quantidadeTotalNotificacoesLidasAuditorQualidadeFesc;
        this.quantidadeTotalNotificacoesNaoLidasAuditorQualidadeFesc
                = quantidadeTotalNotificacoesNaoLidasAuditorQualidadeFesc;
        this.quantidadeTotalNotificacoesDesempatador = quantidadeTotalNotificacoesDesempatador;
        this.quantidadeTotalNotificacoesLidasDesempatador = quantidadeTotalNotificacoesLidasDesempatador;
        this.quantidadeTotalNotificacoesNaoLidasDesempatador = quantidadeTotalNotificacoesNaoLidasDesempatador;
        this.quantidadeTotalTelegramas = quantidadeTotalTelegramas;
        this.quantidadeTotalTelegramasLidas = quantidadeTotalTelegramasLidas;
        this.quantidadeTotalTelegramasNaoLidas = quantidadeTotalTelegramasNaoLidas;
        this.quantidadeTotalTelegramasAdministrativoOperadora = quantidadeTotalTelegramasAdministrativoOperadora;
        this.quantidadeTotalTelegramasLidosAdministrativoOperadora = quantidadeTotalTelegramasLidosAdministrativoOperadora;
        this.quantidadeTotalTelegramasNaoLidosAdministrativoOperadora = quantidadeTotalTelegramasNaoLidosAdministrativoOperadora;
        this.quantidadeTotalTelegramasAuditorOperadora = quantidadeTotalTelegramasAuditorOperadora;
        this.quantidadeTotalTelegramasLidosAuditorOperadora = quantidadeTotalTelegramasLidosAuditorOperadora;
        this.quantidadeTotalTelegramasNaoLidosAuditorOperadora = quantidadeTotalTelegramasNaoLidosAuditorOperadora;
        this.quantidadeTotalTelegramasProfissionalAssistente = quantidadeTotalTelegramasProfissionalAssistente;
        this.quantidadeTotalTelegramasLidosProfissionalAssistente = quantidadeTotalTelegramasLidosProfissionalAssistente;
        this.quantidadeTotalTelegramasNaoLidosProfissionalAssistente = quantidadeTotalTelegramasNaoLidosProfissionalAssistente;
        this.quantidadeTotalTelegramasBeneficiario = quantidadeTotalTelegramasBeneficiario;
        this.quantidadeTotalTelegramasLidosBeneficiario = quantidadeTotalTelegramasLidosBeneficiario;
        this.quantidadeTotalTelegramasNaoLidosBeneficiario = quantidadeTotalTelegramasNaoLidosBeneficiario;
        this.quantidadeTotalTelegramasAdministrativoFesc = quantidadeTotalTelegramasAdministrativoFesc;
        this.quantidadeTotalTelegramasLidosAdministrativoFesc = quantidadeTotalTelegramasLidosAdministrativoFesc;
        this.quantidadeTotalTelegramasNaoLidosAdministrativoFesc = quantidadeTotalTelegramasNaoLidosAdministrativoFesc;
        this.quantidadeTotalTelegramasAuditorQualidadeFesc = quantidadeTotalTelegramasAuditorQualidadeFesc;
        this.quantidadeTotalTelegramasLidosAuditorQualidadeFesc = quantidadeTotalTelegramasLidosAuditorQualidadeFesc;
        this.quantidadeTotalTelegramasNaoLidosAuditorQualidadeFesc = quantidadeTotalTelegramasNaoLidosAuditorQualidadeFesc;
        this.quantidadeTotalTelegramasDesempatador = quantidadeTotalTelegramasDesempatador;
        this.quantidadeTotalTelegramasLidosDesempatador = quantidadeTotalTelegramasLidosDesempatador;
        this.quantidadeTotalTelegramasNaoLidosDesempatador = quantidadeTotalTelegramasNaoLidosDesempatador;
    }
}
