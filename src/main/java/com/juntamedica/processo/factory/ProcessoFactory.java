package com.juntamedica.processo.factory;

import com.juntamedica.anexo.Anexo;
import com.juntamedica.anexo.factory.AnexoFactory;
import com.juntamedica.auditor.Auditor;
import com.juntamedica.auditor.factory.AuditorFactory;
import com.juntamedica.beneficiario.Beneficiario;
import com.juntamedica.beneficiario.factory.BeneficiarioFactory;
import com.juntamedica.consultor.Consultor;
import com.juntamedica.consultor.factory.ConsultorFactory;
import com.juntamedica.contrato.Contrato;
import com.juntamedica.contrato.factory.ContratoFactory;
import com.juntamedica.desempatador.Desempatador;
import com.juntamedica.desempatador.factory.DesempatadorFactory;
import com.juntamedica.dominio.Dominio;
import com.juntamedica.fluxoprocesso.FluxoProcesso;
import com.juntamedica.fluxoprocesso.factory.FluxoProcessoFactory;
import com.juntamedica.operadora.Operadora;
import com.juntamedica.operadoralayout.OperadoraLayout;
import com.juntamedica.operadoralayout.factory.OperadoraLayoutFactory;
import com.juntamedica.processo.Processo;
import com.juntamedica.processo.exception.ProcessoNoSuchFieldException;
import com.juntamedica.processo.payload.ProcessoChatResponse;
import com.juntamedica.processo.payload.ProcessoDesempatadoresResponse;
import com.juntamedica.processo.payload.ProcessoEditRequest;
import com.juntamedica.processo.payload.ProcessoRequest;
import com.juntamedica.processo.payload.ProcessoResponse;
import com.juntamedica.processoareaatuacao.ProcessoAreaAtuacao;
import com.juntamedica.processoareaatuacao.factory.ProcessoAreaAtuacaoFactory;
import com.juntamedica.processodesempatadores.ProcessoDesempatadores;
import com.juntamedica.processoitem.ProcessoItem;
import com.juntamedica.processoitem.factory.ProcessoItemFactory;
import com.juntamedica.profissionalassistente.ProfissionalAssistente;
import com.juntamedica.profissionalassistente.factory.ProfissionalAssistenteFactory;
import com.juntamedica.statusetapa.StatusEtapa;
import com.juntamedica.statusetapa.factory.StatusEtapaFactory;
import com.juntamedica.usuario.Usuario;
import com.juntamedica.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProcessoFactory {

    @Autowired
    private ProcessoItemFactory processoItemFactory;

    @Autowired
    private AnexoFactory anexoFactory;

    @Autowired
    private BeneficiarioFactory beneficiarioFactory;

    @Autowired
    private ProfissionalAssistenteFactory profissionalAssistenteFactory;

    @Autowired
    private AuditorFactory auditorFactory;

    @Autowired
    private ConsultorFactory consultorFactory;

    @Autowired
    private DesempatadorFactory desempatadorFactory;

    @Autowired
    private ContratoFactory contratoFactory;

    @Autowired
    private StatusEtapaFactory statusEtapaFactory;

    @Autowired
    private FluxoProcessoFactory fluxoProcessoFactory;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProcessoAreaAtuacaoFactory processoAreaAtuacaoFactory;

    @Autowired
    private OperadoraLayoutFactory operadoraLayoutFactory;

    public Processo build(ProcessoRequest processoRequest) {
        Processo processo = Processo.builder()
                .beneficiario(
                        processoRequest.getBeneficiario() == null
                                ? null
                                : beneficiarioFactory.build(processoRequest.getBeneficiario()))
                .profissionalAssistente(
                        processoRequest.getProfissionalAssistente() == null
                                ? null
                                : profissionalAssistenteFactory.build(processoRequest.getProfissionalAssistente()))
                .auditor(
                        processoRequest.getAuditor() == null
                                ? null
                                : auditorFactory.build(processoRequest.getAuditor()))
                .consultor(
                        processoRequest.getConsultor() == null
                                ? null
                                : consultorFactory.build(processoRequest.getConsultor()))
                .administrativoFesc(
                        processoRequest.getAdministrativoFescExtId() != null
                                ? Usuario.builder().id(processoRequest.getAdministrativoFescExtId()).build()
                                : null)
                .auditorQualidade(
                        processoRequest.getAuditorQualidadeExtId() != null
                                ? Usuario.builder().id(processoRequest.getAuditorQualidadeExtId()).build()
                                : null)
                .administrativoOperadora(
                        processoRequest.getAdministrativoOperadoraId() != null
                                ? Usuario.builder().id(processoRequest.getAdministrativoOperadoraId()).build()
                                : null)
                .numeroPedidoGuia(processoRequest.getNumeroPedidoGuia())
                .prioridade(processoRequest.getPrioridade())
                .operadoraContratante(
                        processoRequest.getOperadoraContratanteId() == null
                                ? null
                                : Operadora.builder()
                                .id(processoRequest.getOperadoraContratanteId())
                                .build())
                .dmCaraterAtendimento(
                        processoRequest.getDmCaraterAtendimentoId() == null
                                ? null
                                : Dominio.builder()
                                .id(processoRequest.getDmCaraterAtendimentoId())
                                .build())
                .inicioProcesso(processoRequest.getInicioProcesso())
                .contrato(
                        processoRequest.getContratoId() == null
                                ? null
                                : Contrato.builder()
                                .id(processoRequest.getContratoId())
                                .build())
                .conclusaoProcesso(processoRequest.getConclusaoProcesso())
                .dataCriacao(processoRequest.getDataCriacao())
                .dataAlteracao(processoRequest.getDataAlteracao())
                .dataSolicitacao(processoRequest.getDataSolicitacao())
                .prazoInicialAns(processoRequest.getPrazoInicialAns())
                .prazoFinalAns(processoRequest.getPrazoFinalAns())
                .procedimentoRealizado(processoRequest.getProcedimentoRealizado())
                .possuiLiminar(processoRequest.getPossuiLiminar())
                .possuiNip(processoRequest.getPossuiNip())
                .planoRegulamentado(processoRequest.getPlanoRegulamentado())
                .prazoGuiaLegivel(processoRequest.getPrazoGuiaLegivel())
                .indicacaoClinica(processoRequest.getIndicacaoClinica())
                .parecerDesempatador(processoRequest.getParecerDesempatador())
                .parecerDesempatadorRascunho(processoRequest.getParecerDesempatadorRascunho())
                .resumoAoBeneficiario(processoRequest.getResumoAoBeneficiario())
                .parecerCaraterSolicitacao(processoRequest.getParecerCaraterSolicitacao())
                .necessarioMatFabDistrib(processoRequest.getNecessarioMatFabDistrib())
                .dataReprovacaoOperadora(processoRequest.getDataReprovacaoOperadora())
                .dataParecer(processoRequest.getDataParecer())
                .statusEtapa(
                        processoRequest.getStatusEtapaId() == null
                                ? null
                                : StatusEtapa.builder()
                                .id(processoRequest.getStatusEtapaId())
                                .build())
                .podeMatOutroFab(processoRequest.getPodeMatOutroFab())
                .dataCancelamento(processoRequest.getDataCancelamento())
                .dmCancelamentoMotivado(
                        processoRequest.getDmCancelamentoMotivadoId() == null
                                ? null
                                : Dominio.builder()
                                .id(processoRequest.getDmCancelamentoMotivadoId())
                                .build())
                .dmMotivoCancelProcesso(
                        processoRequest.getDmMotivoCancelProcessoId() == null
                                ? null
                                : Dominio.builder()
                                .id(processoRequest.getDmMotivoCancelProcessoId())
                                .build())
                .justificativaCancelamento(processoRequest.getJustificativaCancelamento())
                .justificativaOperadora(processoRequest.getJustificativaOperadora())
                .avaliacaoTrabalho(processoRequest.getAvaliacaoTrabalho())
                .avaliacaoDesempatador(processoRequest.getAvaliacaoDesempatador())
                .dmCobrarProcesso(processoRequest.getDmCobrarProcessoId() == null
                        ? null
                        : Dominio.builder()
                        .id(processoRequest.getDmCobrarProcessoId())
                        .build())
                .documentacaoCompleta(processoRequest.getDocumentacaoCompleta())
                .operadoraOrigem(
                        processoRequest.getOperadoraOrigemId() == null
                                ? null
                                : Operadora.builder()
                                .id(processoRequest.getOperadoraOrigemId())
                                .build())
                .operadoraSolicitante(
                        processoRequest.getOperadoraSolicitanteId() == null
                                ? null
                                : Operadora.builder()
                                .id(processoRequest.getOperadoraSolicitanteId())
                                .build())
                .numeroProtocolo(processoRequest.getNumeroProtocolo())
                .build();

        processo.setProcessoItemList(
                processoRequest.getProcessoItemList() == null
                        ? null
                        : processoItemFactory.buildList(processo, processoRequest.getProcessoItemList()));

        processo.setAnexoList(
                processoRequest.getAnexoList() == null
                        ? null
                        : anexoFactory.buildList(processoRequest.getAnexoList()));

        processo.setAreaAtuacaoList(
                processoRequest.getAreaAtuacaoList() == null
                        ? null
                        : processoAreaAtuacaoFactory.buildList(processo, processoRequest.getAreaAtuacaoList()));

        return processo;
    }

    public Processo updateValues(Processo processo, ProcessoEditRequest processoEditRequest) {
        for (Field field : processoEditRequest.getClass().getDeclaredFields()) {
            String fieldName = field.getName();
            field.setAccessible(true);

            if (ReflectionUtils.getField(field, processoEditRequest) != null) {
                try {
                    Field campoProcessoEntity = processo.getClass().getDeclaredField(fieldName);
                    Field campoProcessoRequest = processoEditRequest.getClass().getDeclaredField(fieldName);
                    campoProcessoEntity.setAccessible(true);
                    campoProcessoRequest.setAccessible(true);

                    campoProcessoEntity.set(processo, campoProcessoRequest.get(processoEditRequest));
                } catch (NoSuchFieldException | IllegalAccessException noSuchFieldException) {
                    throw new ProcessoNoSuchFieldException(fieldName);
                }
            }
        }

        return processo;
    }

    public ProcessoResponse buildResponse(Processo processo) {
        ProcessoResponse processoResponse = ProcessoResponse.builder()
                .id(processo.getId())
                .modoRascunho(processo.getModoRascunho())
                .numeroPedidoGuia(processo.getNumeroPedidoGuia())
                .prioridade(processo.getPrioridade())
                .parecerVisivelOperadora(processo.getParecerVisivelOperadora())
                .numeroProtocolo(processo.getNumeroProtocolo())
                .build();

        Dominio dmCaraterAtendimento = processo.getDmCaraterAtendimento();
        if (dmCaraterAtendimento != null) {
            processoResponse.setDmCaraterAtendimentoId(dmCaraterAtendimento.getId());
            processoResponse.setDmCaraterAtendimentoDescricao(dmCaraterAtendimento.getDescricao());
        }

        processoResponse.setInicioProcesso(processo.getInicioProcesso());

        Contrato contrato = processo.getContrato();
        if (contrato != null) {
            processoResponse.setContrato(contratoFactory.buildResponse(contrato));
        }

        processoResponse.setConclusaoProcesso(processo.getConclusaoProcesso());
        processoResponse.setDataCriacao(processo.getDataCriacao());
        processoResponse.setDataAlteracao(processo.getDataAlteracao());
        processoResponse.setDataSolicitacao(processo.getDataSolicitacao());
        processoResponse.setPrazoInicialAns(processo.getPrazoInicialAns());
        processoResponse.setPrazoFinalAns(processo.getPrazoFinalAns());
        processoResponse.setDataCancelamento(processo.getDataCancelamento());
        processoResponse.setProcedimentoRealizado(processo.getProcedimentoRealizado());
        processoResponse.setPossuiLiminar(processo.getPossuiLiminar());
        processoResponse.setPossuiNip(processo.getPossuiNip());
        processoResponse.setPlanoRegulamentado(processo.getPlanoRegulamentado());
        processoResponse.setPrazoGuiaLegivel(processo.getPrazoGuiaLegivel());
        processoResponse.setIndicacaoClinica(processo.getIndicacaoClinica());
        processoResponse.setParecerDesempatador(processo.getParecerDesempatador());
        processoResponse.setParecerDesempatadorRascunho(processo.getParecerDesempatadorRascunho());
        processoResponse.setResumoAoBeneficiario(processo.getResumoAoBeneficiario());
        processoResponse.setParecerCaraterSolicitacao(processo.getParecerCaraterSolicitacao());
        processoResponse.setNecessarioMatFabDistrib(processo.getNecessarioMatFabDistrib());
        processoResponse.setPodeMatOutroFab(processo.getPodeMatOutroFab());
        processoResponse.setDataParecer(processo.getDataParecer());
        processoResponse.setItensExclusivos(processo.getItensExclusivos());
        processoResponse.setResumoParecerDesempatador(processo.getResumoParecerDesempatador());
        processoResponse.setDataEstimadaConclusao(processo.getDataEstimadaConclusao());

        FluxoProcesso fluxoProcesso = processo.getFluxoProcesso();
        if (fluxoProcesso != null) {
            processoResponse.setFluxoProcesso(fluxoProcessoFactory.buildResponse(fluxoProcesso));
        }

        StatusEtapa statusEtapa = processo.getStatusEtapa();
        if (statusEtapa != null) {
            processoResponse.setStatusEtapa(statusEtapaFactory.buildResponse(processo.getStatusEtapa()));
        }

        Dominio dmCancelamentoMotivado = processo.getDmCancelamentoMotivado();
        if (dmCancelamentoMotivado != null) {
            processoResponse.setDmCancelamentoMotivadoId(dmCancelamentoMotivado.getId());
            processoResponse.setDmCancelamentoMotivadoDescricao(dmCancelamentoMotivado.getDescricao());
        }

        Dominio dmMotivoCancelProcesso = processo.getDmMotivoCancelProcesso();
        if (dmMotivoCancelProcesso != null) {
            processoResponse.setDmMotivoCancelProcessoId(dmMotivoCancelProcesso.getId());
            processoResponse.setDmMotivoCancelProcessoDescricao(dmMotivoCancelProcesso.getDescricao());
        }

        Dominio dmCobrarProcesso = processo.getDmCobrarProcesso();
        if (dmCobrarProcesso != null) {
            processoResponse.setDmCobrarProcessoId(dmCobrarProcesso.getId());
            processoResponse.setDmCobrarProcessoDescricao(dmCobrarProcesso.getDescricao());
        }

        processoResponse.setJustificativaCancelamento(processo.getJustificativaCancelamento());
        processoResponse.setJustificativaOperadora(processo.getJustificativaOperadora());
        processoResponse.setAvaliacaoTrabalho(processo.getAvaliacaoTrabalho());
        processoResponse.setAvaliacaoDesempatador(processo.getAvaliacaoDesempatador());
        processoResponse.setDocumentacaoCompleta(processo.getDocumentacaoCompleta());

        Operadora operadoraOrigem = processo.getOperadoraOrigem();
        if (operadoraOrigem != null) {
            processoResponse.setOperadoraOrigemId(operadoraOrigem.getId());
            processoResponse.setOperadoraOrigemDescricao(operadoraOrigem.getNomeFantasia());

            if (Objects.nonNull(operadoraOrigem.getLayouts()) && !operadoraOrigem.getLayouts().isEmpty()) {
                processoResponse.setOperadoraOrigemLayouts(
                        operadoraLayoutFactory.buildProcessoOperadoraOrigemResponseList(operadoraOrigem.getLayouts()));
            }
        }

        Operadora operadoraSolicitante = processo.getOperadoraSolicitante();
        if (operadoraSolicitante != null) {
            processoResponse.setOperadoraSolicitanteId(operadoraSolicitante.getId());
            processoResponse.setOperadoraSolicitanteDescricao(operadoraSolicitante.getNomeFantasia());
        }

        List<ProcessoItem> processoItemList = processo.getProcessoItemList();
        if (processoItemList != null) {
            processoResponse.setProcessoItemList(processoItemFactory.buildResponseList(processoItemList));
        }

        List<Anexo> processoAnexoList = processo.getAnexoList();
        if (processoAnexoList != null) {
            processoResponse.setAnexoList(anexoFactory.buildResponseList(processoAnexoList));
        }

        Beneficiario beneficiario = processo.getBeneficiario();
        if (beneficiario != null) {
            processoResponse.setBeneficiario(beneficiarioFactory.buildResponse(beneficiario, false));
        }

        ProfissionalAssistente profissionalAssistente = processo.getProfissionalAssistente();
        if (profissionalAssistente != null) {
            processoResponse.setProfissionalAssistente(
                    profissionalAssistenteFactory.buildResponse(profissionalAssistente));
        }

        Auditor auditor = processo.getAuditor();
        if (auditor != null) {
            processoResponse.setAuditor(auditorFactory.buildResponse(auditor));
        }

        Consultor consultor = processo.getConsultor();
        if (consultor != null) {
            processoResponse.setConsultor(consultorFactory.buildResponse(consultor));
        }

        Desempatador desempatador = processo.getDesempatador();
        if (desempatador != null) {
            processoResponse.setDesempatador(desempatadorFactory.buildResponse(desempatador, null));
        }

        Usuario administrativoFesc = processo.getAdministrativoFesc();
        if (administrativoFesc != null) {
            processoResponse.setAdministrativoFescExtId(administrativoFesc.getId());
            processoResponse.setAdministrativoFescExtDescricao(administrativoFesc.getName());
        }

        Usuario auditorQualidade = processo.getAuditorQualidade();
        if (auditorQualidade != null) {
            processoResponse.setAuditorQualidadeExtId(auditorQualidade.getId());
            processoResponse.setAuditorQualidadeExtDescricao(auditorQualidade.getName());
        }

        Usuario administrativoOperadora = processo.getAdministrativoOperadora();
        if (administrativoOperadora != null) {
            processoResponse.setAdministrativoOperadoraId(administrativoOperadora.getId());
            processoResponse.setAdministrativoOperadoraDescricao(administrativoOperadora.getName());
        }

        Operadora operadoraContratante = processo.getOperadoraContratante();
        if (operadoraContratante != null) {
            processoResponse.setOperadoraContratanteId(operadoraContratante.getId());
            processoResponse.setOperadoraContratanteDescricao(operadoraContratante.getNomeFantasia());
        }

        List<ProcessoAreaAtuacao> areaAtuacaoList = processo.getAreaAtuacaoList();
        if (areaAtuacaoList != null && !areaAtuacaoList.isEmpty()) {
            processoResponse.setAreaAtuacaoList(processoAreaAtuacaoFactory.buildResponseList(areaAtuacaoList));
        }

        OperadoraLayout operadoraLayout = processo.getOperadoraLayout();
        if (operadoraLayout != null) {
            processoResponse.setOperadoraLayout(operadoraLayoutFactory.buildResponse(operadoraLayout));
        }

        return processoResponse;
    }

    public ProcessoDesempatadoresResponse buildReponse(ProcessoDesempatadores processoDesempatadores) {
        return ProcessoDesempatadoresResponse.builder()
                .id(processoDesempatadores.getDesempatador().getId())
                .nome(processoDesempatadores.getDesempatador().getNome())
                .miniCurriculo(processoDesempatadores.getDesempatador().getMiniCurriculo())
                .build();
    }

    public ProcessoChatResponse buildResponseChat(Processo processo) {
        return ProcessoChatResponse.builder()
                .id(processo.getId())
                .fluxoProcesso(!Objects.isNull(processo.getFluxoProcesso())
                        ? fluxoProcessoFactory.buildResponse(processo.getFluxoProcesso())
                        : null)
                .statusEtapa(!Objects.isNull(processo.getStatusEtapa())
                        ? statusEtapaFactory.buildResponseFluxoProcesso(processo.getStatusEtapa())
                        : null)
                .administrativoFescExtId(processo.getAdministrativoFesc() != null
                        ? processo.getAdministrativoFesc().getId() : null)
                .auditorQualidadeExtId(processo.getAuditorQualidade() != null
                        ? processo.getAuditorQualidade().getId() : null)
                .desempatadorId(processo.getDesempatador() != null
                        ? processo.getDesempatador().getId() : null)
                .build();
    }

    public List<ProcessoDesempatadoresResponse> buildResponseListDesempatadores(
            List<ProcessoDesempatadores> processoDesempatadoresList) {
        return processoDesempatadoresList.stream().map(this::buildReponse).collect(Collectors.toList());
    }
}
