package com.juntamedica.processo.payload;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvIgnore;
import lombok.Data;

@Data
public class ProcessoRelatorioResponse {

    @CsvBindByName(column = "SLA Processo")
    @CsvBindByPosition(position = 0)
    private String slaProcesso;

    @CsvBindByName(column = "Nº Processo")
    @CsvBindByPosition(position = 1)
    private Long id;

    @CsvBindByName(column = "Beneficiário")
    @CsvBindByPosition(position = 2)
    private String nomeBeneficiario;

    @CsvBindByName(column = "Código Beneficiário")
    @CsvBindByPosition(position = 3)
    private String codigoBeneficiario;

    @CsvBindByName(column = "Etapa")
    @CsvBindByPosition(position = 4)
    private String etapaProcesso;

    @CsvBindByName(column = "Status")
    @CsvBindByPosition(position = 5)
    private String statusProcesso;

    @CsvBindByName(column = "SLA Status")
    @CsvBindByPosition(position = 6)
    private String slaStatusProcesso;

    @CsvBindByName(column = "Início")
    @CsvBindByPosition(position = 7)
    private String inicioProcesso;

    @CsvBindByName(column = "Nº Pedido da Guia")
    @CsvBindByPosition(position = 8)
    private String numeroPedidoGuia;

    @CsvBindByName(column = "Área de Atuação")
    @CsvBindByPosition(position = 9)
    private String areasAtuacao;

    @CsvBindByName(column = "Administrativo FESC")
    @CsvBindByPosition(position = 10)
    private String nomeAdministrativoFesc;

    @CsvBindByName(column = "Desempatador")
    @CsvBindByPosition(position = 11)
    private String nomeDesempatador;

    @CsvBindByName(column = "Especialidades Desempatador")
    @CsvBindByPosition(position = 12)
    private String especialidadesDesempatador;

    @CsvBindByName(column = "Profissional Assistente")
    @CsvBindByPosition(position = 13)
    private String nomeProfissionalAssistente;

    @CsvBindByName(column = "CRM/UF Profissional Assistente\"")
    @CsvBindByPosition(position = 14)
    private String cfmUfProfissionalAssistente;

    @CsvBindByName(column = "CRO/UF Profissional Assistente")
    @CsvBindByPosition(position = 15)
    private String croUfProfissionalAssistente;

    @CsvBindByName(column = "Especialidades Profissional Assistente")
    @CsvBindByPosition(position = 16)
    private String especialidadesProfissionalAssistente;

    @CsvBindByName(column = "Auditor Qualidade FESC")
    @CsvBindByPosition(position = 17)
    private String nomeAuditorQualidade;

    @CsvBindByName(column = "Administrativo da Operadora")
    @CsvBindByPosition(position = 18)
    private String nomeAdministrativoOperadora;

    @CsvBindByName(column = "Nome do Auditor da Operadora")
    @CsvBindByPosition(position = 19)
    private String nomeAuditorOperadora;

    @CsvBindByName(column = "CRM/UF do Auditor da Operadora")
    @CsvBindByPosition(position = 20)
    private String cfmUfAuditorOperadora;

    @CsvBindByName(column = "CRO/UF do Auditor da Operadora")
    @CsvBindByPosition(position = 21)
    private String croUfAuditorOperadora;

    @CsvBindByName(column = "Especialidades do Auditor da Operadora")
    @CsvBindByPosition(position = 22)
    private String especialidadesAuditorOperadora;

    @CsvBindByName(column = "Fluxo do Processo")
    @CsvBindByPosition(position = 23)
    private String nomeFluxoProcesso;

    @CsvBindByName(column = "Operadora Solicitante")
    @CsvBindByPosition(position = 24)
    private String nomeOperadoraSolicitante;

    @CsvBindByName(column = "Documentação Completa?")
    @CsvBindByPosition(position = 25)
    private String documentacaoCompleta;

    @CsvBindByName(column = "% evolução etapas")
    @CsvBindByPosition(position = 26)
    private String porcentagemEtapas;

    @CsvBindByName(column = "Data estimada de conclusão")
    @CsvBindByPosition(position = 27)
    private String dataEstimadaConclusao;

    @CsvBindByName(column = "Data Criação")
    @CsvBindByPosition(position = 28)
    private String dataCriacao;

    @CsvBindByName(column = "Data Conclusão")
    @CsvBindByPosition(position = 29)
    private String dataConclusao;

    @CsvBindByName(column = "Data Parecer")
    @CsvBindByPosition(position = 30)
    private String dataParecer;

    @CsvBindByName(column = "Prazo Inicial(ANS)")
    @CsvBindByPosition(position = 31)
    private String prazoInicialAns;

    @CsvBindByName(column = "Prazo Final(ANS)")
    @CsvBindByPosition(position = 32)
    private String prazoFinalAns;

    @CsvBindByName(column = "Pontos de Divergência")
    @CsvBindByPosition(position = 33)
    private String pontosDivergencia;

    @CsvBindByName(column = "Data do Cancelamento")
    @CsvBindByPosition(position = 34)
    private String dataCancelamento;

    @CsvBindByName(column = "Motivo do Cancelamento")
    @CsvBindByPosition(position = 35)
    private String motivoCancelamento;

    @CsvBindByName(column = "Data de reprovação operadora")
    @CsvBindByPosition(position = 36)
    private String dataReprovacao;

    @CsvBindByName(column = "Motivo de reprovação operadora")
    @CsvBindByPosition(position = 37)
    private String motivoReprovacao;

    @CsvBindByName(column = "Resumo parecer desempatador")
    @CsvBindByPosition(position = 38)
    private String resumoParecerDesempatador;

    @CsvBindByName(column = "Valor estimado de economia")
    @CsvBindByPosition(position = 39)
    private String valorEstimadoEconomia;

    @CsvBindByName(column = "Dias entre Criação e Inicio")
    @CsvBindByPosition(position = 40)
    private Long diasEntreCriacaoInicio;

    @CsvBindByName(column = "Cartas e notificações")
    @CsvBindByPosition(position = 41)
    private String cartas;

    @CsvBindByName(column = "Nº Protocolo")
    @CsvBindByPosition(position = 42)
    private String numeroProtocolo;

    @CsvBindByName(column = "Cobrança do Processo")
    @CsvBindByPosition(position = 43)
    private String cobrancaProcesso;

    @CsvBindByName(column = "Operadora de Origem")
    @CsvBindByPosition(position = 44)
    private String operadoraOrigem;

    @CsvBindByName(column = "Telegramas")
    @CsvBindByPosition(position = 45)
    private String telegramas;

    @CsvBindByName(column = "Data de Envio Processo Desempatador")
    @CsvBindByPosition(position = 46)
    private String dataEnvioProcessoDesempatador;

    @CsvIgnore
    private String slaProcessoTempo;

    @CsvIgnore
    private Boolean slaProcessoEmAlerta;
}
