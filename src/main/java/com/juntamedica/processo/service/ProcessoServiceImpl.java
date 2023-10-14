package com.juntamedica.processo.service;

import com.juntamedica.acao.service.AcaoService;
import com.juntamedica.anexo.Anexo;
import com.juntamedica.anexo.factory.AnexoFactory;
import com.juntamedica.anexo.payload.AnexoRequest;
import com.juntamedica.anexo.service.AnexoService;
import com.juntamedica.areaatuacao.AreaAtuacao;
import com.juntamedica.areaatuacao.exception.AreaAtuacaoException;
import com.juntamedica.areaatuacao.exception.AreaAtuacaoInativaException;
import com.juntamedica.areaatuacao.service.AreaAtuacaoService;
import com.juntamedica.auditor.Auditor;
import com.juntamedica.auditor.payload.AuditorRequest;
import com.juntamedica.auditor.service.AuditorService;
import com.juntamedica.auditorconselho.AuditorConselho;
import com.juntamedica.auditorconselho.payload.AuditorConselhoRequest;
import com.juntamedica.auditorespecialidade.AuditorEspecialidade;
import com.juntamedica.auditorespecialidade.payload.AuditorEspecialidadeRequest;
import com.juntamedica.beneficiario.Beneficiario;
import com.juntamedica.beneficiario.payload.BeneficiarioRequest;
import com.juntamedica.beneficiario.service.BeneficiarioService;
import com.juntamedica.calendariodiahora.CalendarioDiaHora;
import com.juntamedica.calendariodiahora.service.CalendarioDiaHoraService;
import com.juntamedica.calendarioferiado.CalendarioFeriado;
import com.juntamedica.calendarioferiado.service.CalendarioFeriadoService;
import com.juntamedica.chatmensagem.service.ChatMensagemService;
import com.juntamedica.consultor.payload.ConsultorRequest;
import com.juntamedica.consultor.service.ConsultorService;
import com.juntamedica.consultorconselho.payload.ConsultorConselhoRequest;
import com.juntamedica.consultorespecialidade.payload.ConsultorEspecialidadeRequest;
import com.juntamedica.contato.Contato;
import com.juntamedica.contato.payload.ContatoRequest;
import com.juntamedica.contrato.service.ContratoService;
import com.juntamedica.contratofluxoprocesso.ContratoFluxoProcesso;
import com.juntamedica.contratofluxoprocesso.exception.ContratoFluxoProcessoNotFoundException;
import com.juntamedica.contratofluxoprocesso.service.ContratoFluxoProcessoService;
import com.juntamedica.desempatador.Desempatador;
import com.juntamedica.desempatador.payload.DesempatadorRequest;
import com.juntamedica.desempatador.service.DesempatadorService;
import com.juntamedica.desempatadorespecialidade.DesempatadorEspecialidade;
import com.juntamedica.desempatadorfavorito.DesempatadorFavorito;
import com.juntamedica.desempatadorfavorito.service.DesempatadorFavoritoService;
import com.juntamedica.dominio.Dominio;
import com.juntamedica.dominio.payload.DominioResponse;
import com.juntamedica.dominio.service.DominioService;
import com.juntamedica.equipemembro.EquipeMembro;
import com.juntamedica.equipemembro.service.EquipeMembroService;
import com.juntamedica.fluxoprocesso.FluxoProcesso;
import com.juntamedica.infoedit.service.InfoEditService;
import com.juntamedica.itemtipo.payload.ItemTipoResponse;
import com.juntamedica.itemtipo.service.ItemTipoService;
import com.juntamedica.notificacao.Notificacao;
import com.juntamedica.notificacao.enumerator.NotificacaoTipo;
import com.juntamedica.notificacao.service.NotificacaoService;
import com.juntamedica.operadora.Operadora;
import com.juntamedica.operadora.service.OperadoraService;
import com.juntamedica.operadoralayout.OperadoraLayout;
import com.juntamedica.operadoralayout.service.OperadoraLayoutService;
import com.juntamedica.papel.Papel;
import com.juntamedica.papel.service.PapelService;
import com.juntamedica.papelperfil.PapelPerfil;
import com.juntamedica.papelperfil.service.PapelPerfilService;
import com.juntamedica.parametro.payload.ParametroResponse;
import com.juntamedica.parametro.service.ParametroService;
import com.juntamedica.permissao.service.PermissaoService;
import com.juntamedica.processo.Processo;
import com.juntamedica.processo.ProcessoRepository;
import com.juntamedica.processo.enumerator.Leitura;
import com.juntamedica.processo.enumerator.LeituraPapel;
import com.juntamedica.processo.enumerator.TipoFiltro;
import com.juntamedica.processo.exception.ProcessoInvalidOperadoraOrigemException;
import com.juntamedica.processo.exception.ProcessoMissingRequiredFieldException;
import com.juntamedica.processo.exception.ProcessoNoContentReportException;
import com.juntamedica.processo.exception.ProcessoNotFoundException;
import com.juntamedica.processo.exception.ProcessoOperadoraNotAllowedException;
import com.juntamedica.processo.factory.ProcessoFactory;
import com.juntamedica.processo.factory.ProcessoSortByEnum;
import com.juntamedica.processo.payload.ProcessoAlterarStatusRequest;
import com.juntamedica.processo.payload.ProcessoAnexoUploadRequest;
import com.juntamedica.processo.payload.ProcessoAprovarParecerOperadoraRequest;
import com.juntamedica.processo.payload.ProcessoAprovarParecerQualidadeRequest;
import com.juntamedica.processo.payload.ProcessoCancelRequest;
import com.juntamedica.processo.payload.ProcessoChatResponse;
import com.juntamedica.processo.payload.ProcessoDesempatadorDefinirRequest;
import com.juntamedica.processo.payload.ProcessoDesempatadoresRequest;
import com.juntamedica.processo.payload.ProcessoDesempatadoresResponse;
import com.juntamedica.processo.payload.ProcessoEditRequest;
import com.juntamedica.processo.payload.ProcessoFiltersRequest;
import com.juntamedica.processo.payload.ProcessoGridResponse;
import com.juntamedica.processo.payload.ProcessoRealizarParecerRequest;
import com.juntamedica.processo.payload.ProcessoRelatorioResponse;
import com.juntamedica.processo.payload.ProcessoReprovarRequest;
import com.juntamedica.processo.payload.ProcessoRequest;
import com.juntamedica.processo.payload.ProcessoResponse;
import com.juntamedica.processo.payload.ProcessoTotalizadorMenuResponse;
import com.juntamedica.processo.payload.ProcessoTotalizadorPendenciasResponse;
import com.juntamedica.processoareaatuacao.ProcessoAreaAtuacao;
import com.juntamedica.processoareaatuacao.factory.ProcessoAreaAtuacaoFactory;
import com.juntamedica.processoareaatuacao.payload.ProcessoAreaAtuacaoRequest;
import com.juntamedica.processoareaatuacao.service.ProcessoAreaAtuacaoService;
import com.juntamedica.processodesempatadores.ProcessoDesempatadores;
import com.juntamedica.processodesempatadores.service.ProcessoDesempatadoresService;
import com.juntamedica.processoetapa.ProcessoEtapa;
import com.juntamedica.processohistorico.ProcessoHistorico;
import com.juntamedica.processohistorico.service.ProcessoHistoricoService;
import com.juntamedica.processohistoriconotificacao.ProcessoHistoricoNotificacao;
import com.juntamedica.processohistoriconotificacao.service.ProcessoHistoricoNotificacaoService;
import com.juntamedica.processoitem.ProcessoItem;
import com.juntamedica.processoitem.factory.ProcessoItemFactory;
import com.juntamedica.processoitem.payload.ProcessoItemRequest;
import com.juntamedica.processoitem.service.ProcessoItemService;
import com.juntamedica.profissionalassistente.ProfissionalAssistente;
import com.juntamedica.profissionalassistente.payload.ProfissionalAssistenteRequest;
import com.juntamedica.profissionalassistente.service.ProfissionalAssistenteService;
import com.juntamedica.profissionalassistenteconselho.ProfissionalAssistenteConselho;
import com.juntamedica.profissionalassistenteconselho.payload.ProfissionalAssistenteConselhoRequest;
import com.juntamedica.profissionalassistenteespecialidade.ProfissionalAssistenteEspecialidade;
import com.juntamedica.profissionalassistenteespecialidade.payload.ProfissionalAssistenteEspecialidadeRequest;
import com.juntamedica.statusetapa.StatusEtapa;
import com.juntamedica.statusetapa.service.StatusEtapaService;
import com.juntamedica.statusetapaacao.StatusEtapaAcao;
import com.juntamedica.statusetapaacao.service.StatusEtapaAcaoService;
import com.juntamedica.statusetapaacaoperfil.service.StatusEtapaAcaoPerfilService;
import com.juntamedica.statusetapainfoedit.service.StatusEtapaInfoEditService;
import com.juntamedica.usuario.Usuario;
import com.juntamedica.usuario.exception.UsuarioNotLoggedException;
import com.juntamedica.usuario.service.UsuarioService;
import com.juntamedica.utils.CalculadorSlaUtil;
import com.juntamedica.utils.CsvUtils;
import com.juntamedica.utils.SystemUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProcessoServiceImpl implements ProcessoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ProcessoFactory processoFactory;

    @Autowired
    private ProcessoItemFactory processoItemFactory;

    @Autowired
    private ProcessoItemService processoItemService;

    @Autowired
    private BeneficiarioService beneficiarioService;

    @Autowired
    private ProfissionalAssistenteService profissionalAssistenteService;

    @Autowired
    private AuditorService auditorService;

    @Autowired
    private ConsultorService consultorService;

    @Autowired
    private DesempatadorService desempatadorService;

    @Autowired
    private OperadoraService operadoraService;

    @Autowired
    private DominioService dominioService;

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private StatusEtapaService statusEtapaService;

    @Autowired
    private AnexoService anexoService;

    @Autowired
    private AnexoFactory anexoFactory;

    @Autowired
    private ContratoFluxoProcessoService contratoFluxoProcessoService;

    @Autowired
    private StatusEtapaAcaoService statusEtapaAcaoService;

    @Autowired
    private ProcessoDesempatadoresService processoDesempatadoresService;

    @Autowired
    private DesempatadorFavoritoService desesempatadorFavoritoService;

    @Autowired
    private ChatMensagemService chatMensagemService;

    @Autowired
    private ProcessoHistoricoService processoHistoricoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PapelPerfilService papelPerfilService;

    @Autowired
    private ItemTipoService itemTipoService;

    @Autowired
    private CalendarioDiaHoraService calendarioDiaHoraService;

    @Autowired
    private CalendarioFeriadoService calendarioFeriadoService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private EquipeMembroService equipeMembroService;

    @Autowired
    private StatusEtapaAcaoPerfilService statusEtapaAcaoPerfilService;

    @Autowired
    private ProcessoHistoricoNotificacaoService processoHistoricoNotificacaoService;

    @Autowired
    private PapelService papelService;
    
    @Autowired
    private AcaoService acaoService;

    @Autowired
    private ProcessoAreaAtuacaoService processoAreaAtuacaoService;

    @Autowired
    private ProcessoAreaAtuacaoFactory processoAreaAtuacaoFactory;

    @Autowired
    private AreaAtuacaoService areaAtuacaoService;

    @Autowired
    private InfoEditService infoEditService;

    @Autowired
    private StatusEtapaInfoEditService statusEtapaInfoEditService;

    @Autowired
    private OperadoraLayoutService operadoraLayoutService;

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private NotificacaoService notificacaoService;

    @Override
    @Transactional
    public void alterarStatusEtapa(Long processoId,
                                   Long statusEtapaDestinoId,
                                   ProcessoAlterarStatusRequest processoAlterarStatusRequest) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException(processoId));

        verificaPerfilLogadoPermiteAlterarStatus(processo.getStatusEtapa().getId(),
                processoAlterarStatusRequest.getAcaoId());

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        processo.setStatusEtapa(
                StatusEtapa.builder().id(statusEtapaDestinoId).build());

        processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, statusEtapaDestinoId);

        if (!Objects.isNull(processoAlterarStatusRequest)
                && !Objects.isNull(processoAlterarStatusRequest.getComentario())) {
            chatMensagemService.registrarAcao(
                    processo, statusEtapaDestinoId, processoAlterarStatusRequest.getComentario());
        }

        if (statusEtapaIdAtual != null) {
            StatusEtapaAcao statusEtapaAcao = statusEtapaAcaoService.findEntityByStatusEtapaAndAcaoId(
                    statusEtapaIdAtual, processoAlterarStatusRequest.getAcaoId());

            if (statusEtapaAcao != null) {
                processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndStatusEtapaAcao(
                        processo, statusEtapaIdAtual, statusEtapaAcao.getId());
            }
        }
    }

    @Override
    @Transactional
    public void enviarCarta(Long processoId, Long statusEtapaDestinoId, Long anexoId, List<Long> notificacaoIdList,
                            Long modeloCartaId) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException(processoId));

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Gerar e enviar cartas");
        validateRequiredFields(processo);

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        processo.setStatusEtapa(
                StatusEtapa.builder().id(statusEtapaDestinoId).build());

        processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, statusEtapaDestinoId);

        if (statusEtapaIdAtual != null) {
            processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndModeloCarta(notificacaoIdList,
                    processo, statusEtapaIdAtual, anexoId, modeloCartaId);
        }
    }

    @Override
    @Transactional
    public void aprovarParecerAdministrativo(Long processoId, Long statusEtapaDestinoId) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException());

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Aprovar Parecer(Administrativo)");

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        processo.setStatusEtapa(
                StatusEtapa.builder().id(statusEtapaDestinoId).build());

        processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, statusEtapaDestinoId);

        if (statusEtapaIdAtual != null) {
            StatusEtapaAcao statusEtapaAcao = statusEtapaAcaoService.findByStatusEtapaAndNomeBotao(
                    statusEtapaIdAtual, "Aprovar Parecer(Administrativo)");

            if (statusEtapaAcao != null) {
                processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndStatusEtapaAcao(
                        processo, statusEtapaIdAtual, statusEtapaAcao.getId());
            }
        }
    }

    @Override
    @Transactional
    public void aprovarParecerOperadora(Long processoId,
                                        ProcessoAprovarParecerOperadoraRequest processoAprovarParecerOperadoraRequest) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException());

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Aprovar Processo(Operadora)");
        validateRequiredFields(processo);

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        processo.setStatusEtapa(
                StatusEtapa.builder().id(processoAprovarParecerOperadoraRequest.getStatusEtapaDestinoId()).build());

        processo.setAvaliacaoTrabalho(processoAprovarParecerOperadoraRequest.getAvaliacaoTrabalho());
        processo.setAvaliacaoDesempatador(processoAprovarParecerOperadoraRequest.getAvaliacaoDesempatador());

        processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, processoAprovarParecerOperadoraRequest.getStatusEtapaDestinoId());

        if (statusEtapaIdAtual != null) {
            StatusEtapaAcao statusEtapaAcao = statusEtapaAcaoService.findByStatusEtapaAndNomeBotao(
                    statusEtapaIdAtual, "Aprovar Processo(Operadora)");

            if (statusEtapaAcao != null) {
                processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndStatusEtapaAcao(
                        processo, statusEtapaIdAtual, statusEtapaAcao.getId());
            }
        }
    }

    @Override
    @Transactional
    public void aprovarParecerQualidade(Long processoId,
                                        ProcessoAprovarParecerQualidadeRequest processoAprovarParecerQualidadeRequest) {
        if (SystemUtil.getCurrentUsuarioLogado() == null) {
            throw new UsuarioNotLoggedException();
        }

        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(ProcessoNotFoundException::new);

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Revisar Parecer(Qualidade)");
        validateRequiredFields(processo);

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        processo.setStatusEtapa(
                StatusEtapa.builder().id(processoAprovarParecerQualidadeRequest.getStatusEtapaDestinoId()).build());
        processo.setAuditorQualidade(Usuario.builder().id(SystemUtil.getCurrentUsuarioLogado().getId()).build());
        processo.setResumoAoBeneficiario(processoAprovarParecerQualidadeRequest.getResumoBeneficiario());

        processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, processoAprovarParecerQualidadeRequest.getStatusEtapaDestinoId());

        chatMensagemService.registrarAcao(
                processo,
                processoAprovarParecerQualidadeRequest.getStatusEtapaDestinoId(),
                processoAprovarParecerQualidadeRequest.getComentarioChat());

        if (statusEtapaIdAtual != null) {
            StatusEtapaAcao statusEtapaAcao = statusEtapaAcaoService.findEntityByStatusEtapaAndAcaoId(statusEtapaIdAtual,
                    processoAprovarParecerQualidadeRequest.getAcaoId());

            if (statusEtapaAcao != null) {
                processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndStatusEtapaAcao(
                        processo, statusEtapaIdAtual, statusEtapaAcao.getId());
            }
        }
    }

    @Override
    @Transactional
    public void cancelarProcesso(Long processoId, ProcessoCancelRequest processoCancelRequest) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException());

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Cancelar Solicitação");
        validateRequiredFields(processo);

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        processo.setJustificativaCancelamento(processoCancelRequest.getJustificativa());
        processo.setDataCancelamento(LocalDateTime.now());

        processo.setDmMotivoCancelProcesso(
                processoCancelRequest.getDmMotivoCancelId() != null
                        ? Dominio.builder().id(processoCancelRequest.getDmMotivoCancelId()).build()
                        : null);
        processo.setDmCancelamentoMotivado(
                processoCancelRequest.getDmCancelamentoMotivadoId() != null
                        ? Dominio.builder().id(processoCancelRequest.getDmCancelamentoMotivadoId()).build()
                        : null);
        processo.setDmCobrarProcesso(
                processoCancelRequest.getDmCobrarProcessoId() != null
                        ? Dominio.builder().id(processoCancelRequest.getDmCobrarProcessoId()).build()
                        : null);

        processo.setStatusEtapa(
                StatusEtapa.builder().id(processoCancelRequest.getStatusEtapaDestinoId()).build());

        processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, processoCancelRequest.getStatusEtapaDestinoId());

        if (statusEtapaIdAtual != null) {
            StatusEtapaAcao statusEtapaAcao = statusEtapaAcaoService.findByStatusEtapaAndNomeBotao(
                    statusEtapaIdAtual, "Cancelar Solicitação");

            if (statusEtapaAcao != null) {
                processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndStatusEtapaAcao(
                        processo, statusEtapaIdAtual, statusEtapaAcao.getId());
            }
        }
    }

    @Override
    @Transactional
    public void concluirProcesso(Long processoId, Long statusEtapaDestinoId) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException());

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Concluir processo");
        validateRequiredFields(processo);

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        processo.setStatusEtapa(
                StatusEtapa.builder().id(statusEtapaDestinoId).build());
        processo.setDataParecer(LocalDateTime.now());
        processo.setConclusaoProcesso(LocalDateTime.now());

        processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, statusEtapaDestinoId);

        if (statusEtapaIdAtual != null) {
            StatusEtapaAcao statusEtapaAcao = statusEtapaAcaoService.findByStatusEtapaAndNomeBotao(
                    statusEtapaIdAtual, "Concluir processo");

            if (statusEtapaAcao != null) {
                processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndStatusEtapaAcao(
                        processo, statusEtapaIdAtual, statusEtapaAcao.getId());
            }
        }
    }

    @Override
    @Transactional
    public void definirDesempatadorProcesso(Long processoId,
                                            ProcessoDesempatadorDefinirRequest processoDesempatadorDefinirRequest) {
        Processo processo = processoRepository
                .findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException());

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Definir Desempatador");
        validateRequiredFields(processo);

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        Desempatador desempatador = desempatadorService
                .findEntity(processoDesempatadorDefinirRequest.getDesempatadorId());

        if (desempatador.getConselhoList() == null || desempatador.getConselhoList().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O Desempatador escolhido não possui dados de conselho CRM/CRO informados.");
        }

        processo.setDesempatador(desempatador);

        processo.setStatusEtapa(
                StatusEtapa.builder().id(processoDesempatadorDefinirRequest.getStatusEtapaDestinoId()).build());

        if (processoDesempatadorDefinirRequest.getFavoritar()) {
            DesempatadorFavorito novoDesempatadorFavorito = DesempatadorFavorito.builder()
                    .desempatador(desempatador)
                    .operadora(
                        Operadora.builder()
                                .id(processoDesempatadorDefinirRequest.getOperadoraId())
                                .build())
                    .build();

            desesempatadorFavoritoService.save(novoDesempatadorFavorito);
        }

        processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, processoDesempatadorDefinirRequest.getStatusEtapaDestinoId());

        if (statusEtapaIdAtual != null) {
            StatusEtapaAcao statusEtapaAcao = statusEtapaAcaoService.findByStatusEtapaAndNomeBotao(
                    statusEtapaIdAtual, "Definir Desempatador");

            if (statusEtapaAcao != null) {
                processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndStatusEtapaAcao(
                        processo, statusEtapaIdAtual, statusEtapaAcao.getId());
            }
        }
    }

    @Override
    public List<ProcessoDesempatadoresResponse> findDesempatadoresByProcessoId(Long processoId) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException());

        List<ProcessoDesempatadores> processoDesempatadoresList
                = processoDesempatadoresService.findByProcessoId(processo.getId());

        return processoFactory.buildResponseListDesempatadores(processoDesempatadoresList);
    }

    @Override
    public ProcessoTotalizadorMenuResponse findTotalizadorMenu(Long processoId, Long userPapelId) {
        Long userId = Objects.requireNonNull(SystemUtil.getCurrentUsuarioLogado()).getId();
        Papel papelUsuarioLogado = papelService.findEntity(userPapelId);
        boolean usuarioLogadoDesempatador = papelUsuarioLogado.getNome().equalsIgnoreCase("Desempatador");

        ProcessoTotalizadorMenuResponse processoTotalizadorMenuResponse =
                processoRepository.findTotalizadorMenu(processoId, userPapelId, usuarioLogadoDesempatador ? userId : null, userId);
        return processoTotalizadorMenuResponse == null
                ? new ProcessoTotalizadorMenuResponse(0l,0l,0l)
                : processoTotalizadorMenuResponse;
    }

    @Override
    @Transactional
    public void iniciarProcesso(Long processoId, Long statusEtapaDestinoId) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException());

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Iniciar Processo");
        validateRequiredFields(processo);

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        processo.setStatusEtapa(
                StatusEtapa.builder().id(statusEtapaDestinoId).build());

        processo = processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, statusEtapaDestinoId);

        if (statusEtapaIdAtual != null) {
            StatusEtapaAcao statusEtapaAcao = statusEtapaAcaoService.findByStatusEtapaAndNomeBotao(
                    statusEtapaIdAtual, "Iniciar Processo");

            if (statusEtapaAcao != null) {
                processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndStatusEtapaAcao(
                        processo, statusEtapaIdAtual, statusEtapaAcao.getId());
            }
        }
    }

    @Override
    @Transactional
    public void realizarParecer(Long processoId, Long statusEtapaDestinoId,
                                ProcessoRealizarParecerRequest processoRealizarParecerRequest) {
        Processo processo = processoRepository.findById(processoId).orElseThrow(ProcessoNotFoundException::new);

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Realizar Parecer");
        validateRequiredFields(processo);

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        processo.setStatusEtapa(
                StatusEtapa.builder().id(statusEtapaDestinoId).build());

        if (processoRealizarParecerRequest.getProcessoItemList() != null) {
            List<ProcessoItem> processoItemList =
                    processoItemService.saveAllEntity(processo, processoRealizarParecerRequest.getProcessoItemList());

            processo.mergeProcessoItemList(processoItemList);
        } else {
            processo.mergeProcessoItemList(Collections.emptyList());
        }

        DominioResponse desempatadorFavoravel =
                dominioService.findByNomeAndValor("dm_parecer_desempatador", "F");
        DominioResponse desempatadorDesfavoravel =
                dominioService.findByNomeAndValor("dm_parecer_desempatador", "D");
        DominioResponse operadoraDesfavoravel =
                dominioService.findByNomeAndValor("dm_parecer_operadora", "D");
        DominioResponse operadoraFavoravel =
                dominioService.findByNomeAndValor("dm_parecer_operadora", "F");

        int countDesempatadorFavoravel = 0;
        int countFavoravelOperadora = 0;
        String resumoParecerDesempatador = "";

        for (ProcessoItem processoItem : processo.getProcessoItemList()) {
            if (Objects.equals(processoItem.getDmParecerDesempatador().getId(), desempatadorFavoravel.getId())) {
                countDesempatadorFavoravel++;
            }
            if ((Objects.equals(processoItem.getDmParecerOperadora().getId(), operadoraDesfavoravel.getId()) &&
                    Objects.equals(processoItem.getDmParecerDesempatador().getId(), desempatadorDesfavoravel.getId())) ||
                    (Objects.equals(processoItem.getDmParecerOperadora().getId(), operadoraFavoravel.getId()) &&
                            Objects.equals(processoItem.getDmParecerDesempatador().getId(), desempatadorFavoravel.getId()))) {
                resumoParecerDesempatador = "Favorável à operadora";
                countFavoravelOperadora++;
            }
        }

        if (processo.getProcessoItemList() != null) {
            if (countFavoravelOperadora != processo.getProcessoItemList().size()) {
                resumoParecerDesempatador = "Favorável com ajustes";
            }


            if (countDesempatadorFavoravel == processo.getProcessoItemList().size()) {
                resumoParecerDesempatador = "Favorável ao profissional assistente";
            }
        }

        processo.setResumoParecerDesempatador(resumoParecerDesempatador);
        processo.setItensExclusivos(processoRealizarParecerRequest.getItensExclusivos());
        processo.setPodeMatOutroFab(processoRealizarParecerRequest.getPodeMatOutroFab());
        processo.setNecessarioMatFabDistrib(processoRealizarParecerRequest.getNecessarioMatFabDistrib());
        processo.setParecerDesempatador(processoRealizarParecerRequest.getParecerDesempatador());
        processo.setParecerCaraterSolicitacao(processoRealizarParecerRequest.getParecerCaraterSolicitacao());

        processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, statusEtapaDestinoId);

        if (statusEtapaIdAtual != null) {
            StatusEtapaAcao statusEtapaAcao = statusEtapaAcaoService.findByStatusEtapaAndNomeBotao(
                    statusEtapaIdAtual, "Realizar Parecer");

            if (statusEtapaAcao != null) {
                processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndStatusEtapaAcao(
                        processo, statusEtapaIdAtual, statusEtapaAcao.getId());
            }
        }
    }

    @Override
    public void realizarParecerRascunho(Long processoId, ProcessoRealizarParecerRequest processoRealizarParecerRequest) {
        Processo processo = processoRepository.findById(processoId).orElseThrow(ProcessoNotFoundException::new);

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Realizar Parecer");

        if (processoRealizarParecerRequest.getProcessoItemList() != null) {
            List<ProcessoItem> processoItemList =
                    processoItemService.saveAllEntity(processo, processoRealizarParecerRequest.getProcessoItemList());

            processo.mergeProcessoItemList(processoItemList);
        } else {
            processo.mergeProcessoItemList(Collections.emptyList());
        }

        processo.setItensExclusivos(processoRealizarParecerRequest.getItensExclusivos());
        processo.setPodeMatOutroFab(processoRealizarParecerRequest.getPodeMatOutroFab());
        processo.setNecessarioMatFabDistrib(processoRealizarParecerRequest.getNecessarioMatFabDistrib());
        processo.setParecerDesempatadorRascunho(processoRealizarParecerRequest.getParecerDesempatador());
        processo.setParecerCaraterSolicitacao(processoRealizarParecerRequest.getParecerCaraterSolicitacao());

        processoRepository.save(processo);
    }

    @Override
    @Transactional
    public void reprovarProcessoOperadora(Long processoId, ProcessoReprovarRequest processoReprovarRequest) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException());

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Reprovar Processo(Operadora)");

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        processo.setJustificativaOperadora(processoReprovarRequest.getJustificativa());
        processo.setDataReprovacaoOperadora(LocalDateTime.now());

        processo.setDmMotivoReprovarProcesso(Dominio.builder()
                .id(processoReprovarRequest.getDmMotivoReprovarId()).build());
        processo.setDmReprovarMotivado(Dominio.builder()
                .id(processoReprovarRequest.getDmReprovarMotivadoId()).build());

        processo.setStatusEtapa(
                StatusEtapa.builder().id(processoReprovarRequest.getStatusEtapaDestinoId()).build());

        processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, processoReprovarRequest.getStatusEtapaDestinoId());

        if (statusEtapaIdAtual != null) {
            StatusEtapaAcao statusEtapaAcao = statusEtapaAcaoService.findByStatusEtapaAndNomeBotao(
                    statusEtapaIdAtual, "Reprovar Processo(Operadora)");

            if (statusEtapaAcao != null) {
                processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndStatusEtapaAcao(
                        processo, statusEtapaIdAtual, statusEtapaAcao.getId());
            }
        }
    }

    @Override
    @Transactional
    public ProcessoResponse save(ProcessoRequest processoRequest) throws IOException {
        permissaoService.hasPermission("menu_processos.exibe_botao_novo_processo:view");

        if (!processoRequest.getModoRascunho()) {
            List<String> validations = this.validateProcesso(processoRequest);

            if (!validations.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validations.stream().findFirst().get());
            }
        }

        if (processoRequest.getModoRascunho()) {
            if (processoRequest.getPrazoInicialAns() != null && processoRequest.getPrazoFinalAns() != null
                    && processoRequest.getPrazoInicialAns().isAfter(processoRequest.getPrazoFinalAns())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "O Prazo Inicial ANS não deve ser maior que o Prazo Final ANS");
            }

            if (processoRequest.getOperadoraOrigemId() == null) {
                throw new ProcessoInvalidOperadoraOrigemException();
            }
        }

        if (SystemUtil.getCurrentUsuarioLogado() != null
                && SystemUtil.getCurrentUsuarioLogado().getOperadora() == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário logado não possui operadora vinculada.");
        }

        Processo processo = this.constructProcesso(processoRequest);

        ProcessoResponse processoResponse = processoFactory.buildResponse(processo);

        if (!processo.getModoRascunho()) {
            processoHistoricoService.saveProcessoHistorico(processo.getId(), processo.getStatusEtapa().getId());

            this.atualizaSlaProcesso(processoResponse);
            this.atualizaSlaStatusProcesso(processoResponse);
        }

        return processoResponse;
    }

    @Transactional
    private Processo constructProcesso(ProcessoRequest processoRequest) {
        if (SystemUtil.getCurrentUsuarioLogado() == null) {
            throw new UsuarioNotLoggedException();
        }

        Processo processo;

        if (processoRequest.getId() != null) {
            processo = processoRepository.findById(processoRequest.getId()).orElseThrow(ProcessoNotFoundException::new);
        } else {
            processo = new Processo();
            processo.setDataCriacao(LocalDateTime.now());
            processo.setDocumentacaoCompleta(false);
            processo.setPrazoGuiaLegivel(false);
            processo.setNecessarioMatFabDistrib(null);
            processo.setPodeMatOutroFab(null);
            processo.setAdministrativoOperadora(
                    Usuario.builder().id(SystemUtil.getCurrentUsuarioLogado().getId()).build());

            Operadora operadora = SystemUtil.getCurrentUsuarioLogado().getOperadora();

            processo.setOperadoraSolicitante(operadora);
            processo.setOperadoraContratante(operadora);
        }

        processo.setOperadoraOrigem(Operadora.builder().id(processoRequest.getOperadoraOrigemId()).build());
        processo.setModoRascunho(processoRequest.getModoRascunho());
        processo.setPrioridade(
                processoRequest.getPrioridade() != null ? processoRequest.getPrioridade() : false);
        processo.setPrazoInicialAns(processoRequest.getPrazoInicialAns());
        processo.setPrazoFinalAns(processoRequest.getPrazoFinalAns());
        processo.setNumeroPedidoGuia(processoRequest.getNumeroPedidoGuia());
        processo.setProcedimentoRealizado(
                processoRequest.getProcedimentoRealizado() != null ? processoRequest.getProcedimentoRealizado() : false);
        processo.setPossuiNip(
                processoRequest.getPossuiNip() != null ? processoRequest.getPossuiNip() : false);
        processo.setPossuiLiminar(
                processoRequest.getPossuiLiminar() != null ? processoRequest.getPossuiLiminar() : false);
        processo.setPlanoRegulamentado(
                processoRequest.getPlanoRegulamentado() != null ? processoRequest.getPlanoRegulamentado() : false);
        processo.setIndicacaoClinica(processoRequest.getIndicacaoClinica());
        processo.setJustificativaOperadora(processoRequest.getJustificativaOperadora());
        processo.setJustificativaCancelamento(processoRequest.getJustificativaCancelamento());
        processo.setNumeroProtocolo(processoRequest.getNumeroProtocolo());

        ContratoFluxoProcesso contratoFluxoProcesso = null;
        if (processo.getFluxoProcesso() == null
                || processo.getFluxoProcesso().getId().compareTo(processoRequest.getFluxoProcesso().getId()) != 0) {
            contratoFluxoProcesso = contratoFluxoProcessoService.findEntityById(
                    processoRequest.getFluxoProcesso().getContratoFluxoProcessoId());

            if (Objects.nonNull(contratoFluxoProcesso)) {
                processo.setFluxoProcesso(contratoFluxoProcesso.getFluxoProcesso());
                processo.setContrato(contratoFluxoProcesso.getContrato());
            }
        }

        if (!processoRequest.getModoRascunho()) {
            StatusEtapa statusEtapa = statusEtapaService.findFirstForProcess(processo.getFluxoProcesso().getId());
            if (statusEtapa != null) {
                processo.setStatusEtapa(statusEtapa);

                if (contratoFluxoProcesso == null) {
                    contratoFluxoProcesso = contratoFluxoProcessoService.findContratoByFluxoProcessoIdAndContrato(
                            processoRequest.getFluxoProcesso().getId(), processo.getContrato().getId())
                            .orElseThrow(ContratoFluxoProcessoNotFoundException::new);
                }

                List<CalendarioDiaHora> horarios = contratoFluxoProcesso != null && contratoFluxoProcesso.getCalendario() != null
                        ? contratoFluxoProcesso.getCalendario().getCalendarioDiaHoraList()
                        : Collections.emptyList();
                List<CalendarioFeriado> feriados = contratoFluxoProcesso != null && contratoFluxoProcesso.getCalendario() != null
                        ? contratoFluxoProcesso.getCalendario().getCalendarioFeriadoList()
                        : Collections.emptyList();

                LocalDateTime dataInicioProcesso = this.getProximoDiaUtil(horarios, feriados);
                processo.setInicioProcesso(dataInicioProcesso);

                if (contratoFluxoProcesso.getPreveSla()) {
                    CalculadorSlaUtil calculadorSlaUtil = new CalculadorSlaUtil();
                    processo.setDataEstimadaConclusao(calculadorSlaUtil.calculaDataPrevista(processo.getInicioProcesso(),
                            contratoFluxoProcesso.getTempoEmHoras(), horarios, feriados));
                }

                Long administrativoFescExtId = buscaProximoAdministrativoFescExtId(processo);
                processo.setAdministrativoFesc(
                        administrativoFescExtId != null
                                ? Usuario.builder().id(administrativoFescExtId).build()
                                : null);

                Long auditorQualidadeExtId = buscaProximoAuditorQualidadeExtId(processo);
                processo.setAuditorQualidade(
                        auditorQualidadeExtId != null
                                ? Usuario.builder().id(auditorQualidadeExtId).build()
                                : null);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Status Inicial não encontrado para este fluxo de processo");
            }
        }

        if (processoRequest.getDmCaraterAtendimentoId() != null) {
            processo.setDmCaraterAtendimento(dominioService.findEntity(processoRequest.getDmCaraterAtendimentoId()));
        }

        if (processoRequest.getDmMotivoCancelProcessoId() != null) {
            processo.setDmMotivoCancelProcesso(dominioService.findEntity(processoRequest.getDmMotivoCancelProcessoId()));
        }

        if (processoRequest.getDmCancelamentoMotivadoId() != null) {
            processo.setDmCancelamentoMotivado(dominioService.findEntity(processoRequest.getDmCancelamentoMotivadoId()));
        }

        if (processoRequest.getOperadoraLayoutId() != null) {
            processo.setOperadoraLayout(OperadoraLayout.builder().id(processoRequest.getOperadoraLayoutId()).build());
        } else {
            processo.setOperadoraLayout(null);
        }

        processo = this.saveProcessoChildRegistrations(processo, processoRequest);

        return processoRepository.save(processo);
    }

    @Transactional
    private Processo saveProcessoChildRegistrations(Processo processo, ProcessoRequest processoRequest) {
        if (processo.getProcessoItemList() == null || processo.getProcessoItemList().isEmpty()) {
            if (processoRequest.getProcessoItemList() != null && !processoRequest.getProcessoItemList().isEmpty()) {
                processo.mergeProcessoItemList(
                        processoItemFactory.buildList(processo, processoRequest.getProcessoItemList()));
            }
        } else {
            if (processoRequest.getProcessoItemList() != null && !processoRequest.getProcessoItemList().isEmpty()) {
                List<ProcessoItem> processoItemList =
                        processoItemService.saveAllEntity(processo, processoRequest.getProcessoItemList());

                processo.mergeProcessoItemList(processoItemList);
            } else {
                processo.mergeProcessoItemList(Collections.emptyList());
            }
        }

        BeneficiarioRequest beneficiario = processoRequest.getBeneficiario();
        if (processo.getBeneficiario() == null) {
            if (beneficiario != null) {
                if (beneficiario.getCodigo() != null && beneficiario.getNome() != null) {
                    processoRequest.getBeneficiario().setOperadoraId(processo.getOperadoraSolicitante().getId());

                    if (beneficiario.getAnexoList() != null && !beneficiario.getAnexoList().isEmpty()) {
                        beneficiario.setAnexoList(null);
                    }

                    processo.setBeneficiario(
                            beneficiarioService.saveEntity(processoRequest.getBeneficiario()));
                }
            }
        } else {
            if (beneficiario != null) {
                processo.setBeneficiario(beneficiarioService.editEntity(beneficiario));
            }
        }

        ProfissionalAssistenteRequest profissionalAssistente = processoRequest.getProfissionalAssistente();
        if (processo.getProfissionalAssistente() == null) {
            if (profissionalAssistente != null) {
                if (profissionalAssistente.getConselhoList() != null
                        && profissionalAssistente.getConselhoList().size() > 0
                        && profissionalAssistente.getNome() != null) {
                    processoRequest.getProfissionalAssistente().setOperadoraId(
                            processo.getOperadoraSolicitante().getId());

                    if (profissionalAssistente.getAnexoList() != null && !profissionalAssistente.getAnexoList().isEmpty()) {
                        profissionalAssistente.setAnexoList(null);
                    }

                    processo.setProfissionalAssistente(
                            profissionalAssistenteService.saveEntity(processoRequest.getProfissionalAssistente()));
                }
            }
        } else {
            if (profissionalAssistente != null) {
                if (profissionalAssistente.getOperadoraId() == null) {
                    profissionalAssistente.setOperadoraId(processo.getOperadoraSolicitante().getId());
                }
                processo.setProfissionalAssistente(profissionalAssistenteService.editEntity(profissionalAssistente));
            }
        }

        AuditorRequest auditor = processoRequest.getAuditor();
        if (processo.getAuditor() == null) {
            if (auditor != null) {
                if (auditor.getConselhoList() != null
                        && auditor.getConselhoList().size() > 0 && auditor.getNome() != null) {
                    processoRequest.getAuditor().setOperadoraId(processo.getOperadoraSolicitante().getId());

                    processo.setAuditor(
                            auditorService.saveEntity(auditor));
                }
            }
        } else {
            if (auditor != null) {
                if (auditor.getOperadoraId() == null) {
                    auditor.setOperadoraId(processo.getOperadoraSolicitante().getId());
                }
                processo.setAuditor(auditorService.editEntity(auditor));
            }
        }

        ConsultorRequest consultor = processoRequest.getConsultor();
        if (processo.getConsultor() == null) {
            if (consultor != null) {
                if (consultor.getConselhoList() != null
                        && consultor.getConselhoList().size() > 0 && consultor.getNome() != null) {
                    processoRequest.getConsultor().setOperadoraId(processo.getOperadoraSolicitante().getId());

                    processo.setConsultor(
                            consultorService.saveEntity(consultor));
                }
            }
        } else {
            if (consultor != null) {
                processo.setConsultor(consultorService.editEntity(consultor));
            }
        }

        List<AnexoRequest> anexoList = processoRequest.getAnexoList();
        if (processo.getAnexoList() == null || processo.getAnexoList().isEmpty()) {
            if (anexoList != null && !anexoList.isEmpty()) {
                List<Anexo> newAnexoList = anexoService.saveAllEntities(processoRequest.getAnexoList());
                processo.mergeProcessoAnexoList(newAnexoList);
            }
        } else {
            if (anexoList != null && !anexoList.isEmpty()) {
                this.mergeAnexosList(processo, processoRequest.getAnexoList());
            } else {
                processo.setAnexoList(Collections.emptyList());
            }
        }

        if (processo.getAreaAtuacaoList() == null || processo.getAreaAtuacaoList().isEmpty()) {
            if (processoRequest.getAreaAtuacaoList() != null && !processoRequest.getAreaAtuacaoList().isEmpty()) {
                processo.mergeProcessoAreaAtuacaoList(
                        processoAreaAtuacaoFactory.buildList(processo, processoRequest.getAreaAtuacaoList()));
            }
        } else {
            if (processoRequest.getAreaAtuacaoList() != null && !processoRequest.getAreaAtuacaoList().isEmpty()) {
                List<ProcessoAreaAtuacao> processoAreaAtuacaoList =
                        processoAreaAtuacaoService.saveAllEntity(processo, processoRequest.getAreaAtuacaoList());
                processo.mergeProcessoAreaAtuacaoList(processoAreaAtuacaoList);
            } else {
                processo.mergeProcessoAreaAtuacaoList(Collections.emptyList());
            }
        }

        return processo;
    }

    @Override
    public ProcessoResponse edit(ProcessoEditRequest processoEditRequest) {
        Processo processo = processoRepository.findById(processoEditRequest.getId())
                .orElseThrow(() -> new ProcessoNotFoundException(processoEditRequest.getId()));

        List<String> validations = this.validateProcessoEdit(processoEditRequest);

        if (!validations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validations.stream().findFirst().get());
        }

        processo = processoFactory.updateValues(processo, processoEditRequest);
        processo = processoRepository.save(processo);

        ProcessoResponse processoResponse = processoFactory.buildResponse(processo);

        this.atualizaSlaProcesso(processoResponse);
        this.atualizaSlaStatusProcesso(processoResponse);

        return processoResponse;
    }

    @Override
    public void saveProcessoDesempatadores(Long processoId, ProcessoDesempatadoresRequest processoDesempatadoresRequest) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException(processoId));

        verificaPerfilLogadoPermiteAcessar(processo.getStatusEtapa().getId(), "Escolher Desempatadores");
        validateRequiredFields(processo);

        Long statusEtapaIdAtual = processo.getStatusEtapa() != null
                ? Long.valueOf(processo.getStatusEtapa().getId()) : null;

        if (processoDesempatadoresRequest.getDesempatadorList() != null
            && !processoDesempatadoresRequest.getDesempatadorList().isEmpty()) {

            ParametroResponse parametro = parametroService.findByNome("nro_desempatadores_opcoes");

            if (parametro != null
                    && (parametro.getValorNumber() != processoDesempatadoresRequest.getDesempatadorList().size())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Escolha " + parametro.getValorNumber() + " desempatadores para prosseguir!");
            }
        }

        processoDesempatadoresService.deleteByProcessoId(processo.getId());

        for (DesempatadorRequest desempatadorRequest : processoDesempatadoresRequest.getDesempatadorList()) {
            ProcessoDesempatadores processoDesempatadores = new ProcessoDesempatadores();
            processoDesempatadores.setProcesso(Processo.builder().id(processo.getId()).build());
            processoDesempatadores.setDesempatador(Desempatador.builder().id(desempatadorRequest.getId()).build());

            processoDesempatadoresService.save(processoDesempatadores);
        }

        processo.setStatusEtapa(
                StatusEtapa.builder().id(processoDesempatadoresRequest.getStatusEtapaDestinoId()).build());

        processoRepository.save(processo);
        processoHistoricoService.saveProcessoHistorico(processoId, processoDesempatadoresRequest.getStatusEtapaDestinoId());

        if (statusEtapaIdAtual != null) {
            StatusEtapaAcao statusEtapaAcao = statusEtapaAcaoService.findByStatusEtapaAndNomeBotao(
                    statusEtapaIdAtual, "Escolher Desempatadores");

            if (statusEtapaAcao != null) {
                processoHistoricoNotificacaoService.createNotificacaoAndSendByProcessoAndStatusEtapaAcao(
                        processo, statusEtapaIdAtual, statusEtapaAcao.getId());
            }
        }
    }

    @Override
    public void updateField(Long processoId, Long foreignKey, String fieldName) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException(processoId));

        this.updateProcessoForeignField(processo, foreignKey, fieldName);

        processoRepository.save(processo);
    }

    @Override
    public void delete(Long processoId) {
        processoRepository.deleteById(processoId);
    }

    @Override
    public ProcessoResponse findById(Long processoId) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException(processoId));

        ProcessoResponse processoResponse = processoFactory.buildResponse(processo);

        this.atualizaSlaProcesso(processoResponse);
        this.atualizaSlaStatusProcesso(processoResponse);

        return processoResponse;
    }

    @Override
    public Processo findEntityById(Long processoId) {
        return processoRepository.findById(processoId).orElseThrow(() -> new ProcessoNotFoundException(processoId));
    }

    @Override
    public Integer findByFluxoProcessoId(Long fluxoProcessoId) {
        return processoRepository.findByFluxoProcessoId(fluxoProcessoId);
    }

    @Override
    public ProcessoTotalizadorPendenciasResponse findTotalizadorPendencias(
            ProcessoFiltersRequest processoFiltersRequest) {
        String hql = this.createProcessoHql(null, false, true, true);
        Query query = entityManager.createQuery(hql);

        return (ProcessoTotalizadorPendenciasResponse) query.getSingleResult();
    }

    private List<ProcessoGridResponse> leituraNotificacaoProcesso(List<ProcessoGridResponse> processoGridResponseList) {
        for (ProcessoGridResponse processoGridResponse:  processoGridResponseList) {
            Leitura leitura = Leitura.LIDO_PARCIALMENTE;

            Long quantidadeTotalNotificacoes = processoGridResponse.getQuantidadeTotalNotificacoes()
                    + processoGridResponse.getQuantidadeTotalTelegramas();
            Long quantidadeTotalNotificacoesLidas = processoGridResponse.getQuantidadeTotalNotificacoesLidas()
                    + processoGridResponse.getQuantidadeTotalTelegramasLidas();
            Long quantidadeTotalNotificacoesNaoLidas = processoGridResponse.getQuantidadeTotalNotificacoesNaoLidas()
                    + processoGridResponse.getQuantidadeTotalTelegramasNaoLidas();

            if (quantidadeTotalNotificacoes == 0L) {
                leitura = Leitura.VAZIO;
            } else if (quantidadeTotalNotificacoes == quantidadeTotalNotificacoesNaoLidas) {
                leitura = Leitura.NAO_LIDO;
            } else if (quantidadeTotalNotificacoes == quantidadeTotalNotificacoesLidas) {
                leitura = Leitura.LIDO;
            }

                processoGridResponse.setLeitura(leitura);
        }

        return processoGridResponseList;
    }

    private List<ProcessoGridResponse> leituraPapelNotificacaoProcesso(List<ProcessoGridResponse> processoGridResponseList) {
        for (ProcessoGridResponse processoGridResponse:  processoGridResponseList) {
            List<LeituraPapel> leituraPapelList = new ArrayList<>();
            LeituraPapel leituraPapel = LeituraPapel.LIDO_PARCIALMENTE_ADMINISTRATIVO_OPERADORA;

            Long quantidadeTotalNotificacoesAdministrativoOperadora =
                    processoGridResponse.getQuantidadeTotalNotificacoesAdministrativoOperadora()
                            + processoGridResponse.getQuantidadeTotalTelegramasAdministrativoOperadora();
            Long quantidadeTotalNotificacoesLidasAdministrativoOperadora =
                    processoGridResponse.getQuantidadeTotalNotificacoesLidasAdministrativoOperadora()
                            + processoGridResponse.getQuantidadeTotalTelegramasLidosAdministrativoOperadora();
            Long quantidadeTotalNotificacoesNaoLidasAdministrativoOperadora =
                    processoGridResponse.getQuantidadeTotalNotificacoesNaoLidasAdministrativoOperadora()
                            + processoGridResponse.getQuantidadeTotalTelegramasNaoLidosAdministrativoOperadora();

            if (quantidadeTotalNotificacoesAdministrativoOperadora == 0L) {
                leituraPapel = null;
            } else if (quantidadeTotalNotificacoesAdministrativoOperadora
                    == quantidadeTotalNotificacoesNaoLidasAdministrativoOperadora) {
                leituraPapel = LeituraPapel.NAO_LIDO_ADMINISTRATIVO_OPERADORA;
            } else if (quantidadeTotalNotificacoesAdministrativoOperadora
                    == quantidadeTotalNotificacoesLidasAdministrativoOperadora) {
                leituraPapel = LeituraPapel.LIDO_ADMINISTRATIVO_OPERADORA;
            }

            if (leituraPapel != null) leituraPapelList.add(leituraPapel);

            leituraPapel = LeituraPapel.LIDO_PARCIALMENTE_LIDO_AUDITOR_OPERADORA;

            Long quantidadeTotalNotificacoesAuditorOperadora = processoGridResponse.getQuantidadeTotalNotificacoesAuditorOperadora()
                    + processoGridResponse.getQuantidadeTotalTelegramasAuditorOperadora();
            Long quantidadeTotalNotificacoesNaoLidasAuditorOperadora = processoGridResponse.getQuantidadeTotalNotificacoesNaoLidasAuditorOperadora()
                    + processoGridResponse.getQuantidadeTotalTelegramasNaoLidosAuditorOperadora();
            Long quantidadeTotalNotificacoesLidasAuditorOperadora = processoGridResponse.getQuantidadeTotalNotificacoesLidasAuditorOperadora()
                    + processoGridResponse.getQuantidadeTotalTelegramasLidosAuditorOperadora();

            if (quantidadeTotalNotificacoesAuditorOperadora == 0L) {
                leituraPapel = null;
            } else if (quantidadeTotalNotificacoesAuditorOperadora == quantidadeTotalNotificacoesNaoLidasAuditorOperadora) {
                leituraPapel = LeituraPapel.NAO_LIDO_LIDO_AUDITOR_OPERADORA;
            } else if (quantidadeTotalNotificacoesAuditorOperadora == quantidadeTotalNotificacoesLidasAuditorOperadora) {
                leituraPapel = LeituraPapel.LIDO_AUDITOR_OPERADORA;
            }

            if (leituraPapel != null) leituraPapelList.add(leituraPapel);

            leituraPapel = LeituraPapel.LIDO_PARCIALMENTE_PROFISSIONAL_ASSISTENTE;

            Long quantidadeTotalNotificacoesProfissionalAssistente = processoGridResponse.getQuantidadeTotalNotificacoesProfissionalAssistente()
                    + processoGridResponse.getQuantidadeTotalTelegramasProfissionalAssistente();
            Long quantidadeTotalNotificacoesNaoLidasProfissionalAssistente =
                    processoGridResponse.getQuantidadeTotalNotificacoesNaoLidasProfissionalAssistente()
                            + processoGridResponse.getQuantidadeTotalTelegramasNaoLidosProfissionalAssistente();
            Long quantidadeTotalNotificacoesLidasProfissionalAssistente =
                    processoGridResponse.getQuantidadeTotalNotificacoesLidasProfissionalAssistente()
                            + processoGridResponse.getQuantidadeTotalTelegramasLidosProfissionalAssistente();

            if (quantidadeTotalNotificacoesProfissionalAssistente == 0L) {
                leituraPapel = null;
            } else if (quantidadeTotalNotificacoesProfissionalAssistente == quantidadeTotalNotificacoesNaoLidasProfissionalAssistente) {
                leituraPapel = LeituraPapel.NAO_LIDO_PROFISSIONAL_ASSISTENTE;
            } else if (quantidadeTotalNotificacoesProfissionalAssistente == quantidadeTotalNotificacoesLidasProfissionalAssistente) {
                leituraPapel = LeituraPapel.LIDO_PROFISSIONAL_ASSISTENTE;
            }

            if (leituraPapel != null) leituraPapelList.add(leituraPapel);

            leituraPapel = LeituraPapel.LIDO_PARCIALMENTE_BENEFICIARIO;

            Long quantidadeTotalNotificacoesBeneficiario = processoGridResponse.getQuantidadeTotalNotificacoesBeneficiario()
                    + processoGridResponse.getQuantidadeTotalTelegramasBeneficiario();
            Long quantidadeTotalNotificacoesNaoLidasBeneficiario =
                    processoGridResponse.getQuantidadeTotalNotificacoesNaoLidasBeneficiario()
                            + processoGridResponse.getQuantidadeTotalTelegramasNaoLidosBeneficiario();
            Long quantidadeTotalNotificacoesLidasBeneficiario =
                    processoGridResponse.getQuantidadeTotalNotificacoesLidasBeneficiario()
                            + processoGridResponse.getQuantidadeTotalTelegramasLidosBeneficiario();

            if (quantidadeTotalNotificacoesBeneficiario == 0L) {
                leituraPapel = null;
            } else if (quantidadeTotalNotificacoesBeneficiario == quantidadeTotalNotificacoesNaoLidasBeneficiario) {
                leituraPapel = LeituraPapel.NAO_LIDO_BENEFICIARIO;
            } else if (quantidadeTotalNotificacoesBeneficiario == quantidadeTotalNotificacoesLidasBeneficiario) {
                leituraPapel = LeituraPapel.LIDO_BENEFICIARIO;
            }

            if (leituraPapel != null) leituraPapelList.add(leituraPapel);

            leituraPapel = LeituraPapel.LIDO_PARCIALMENTE_ADMINISTRATIVO_FESC;

            Long quantidadeTotalTelegramasAdministrativoFesc = processoGridResponse.getQuantidadeTotalNotificacoesAdministrativoFesc()
                    + processoGridResponse.getQuantidadeTotalTelegramasAdministrativoFesc();
            Long quantidadeTotalNotificacoesNaoLidasAdministrativoFesc =
                    processoGridResponse.getQuantidadeTotalNotificacoesNaoLidasAdministrativoFesc()
                            + processoGridResponse.getQuantidadeTotalTelegramasNaoLidosAdministrativoFesc();
            Long quantidadeTotalNotificacoesLidasAdministrativoFesc =
                    processoGridResponse.getQuantidadeTotalNotificacoesLidasAdministrativoFesc()
                            + processoGridResponse.getQuantidadeTotalTelegramasLidosAdministrativoFesc();

            if (quantidadeTotalTelegramasAdministrativoFesc == 0L) {
                leituraPapel = null;
            } else if (quantidadeTotalTelegramasAdministrativoFesc == quantidadeTotalNotificacoesNaoLidasAdministrativoFesc) {
                leituraPapel = LeituraPapel.NAO_LIDO_ADMINISTRATIVO_FESC;
            } else if (quantidadeTotalTelegramasAdministrativoFesc == quantidadeTotalNotificacoesLidasAdministrativoFesc) {
                leituraPapel = LeituraPapel.LIDO_ADMINISTRATIVO_FESC;
            }

            if (leituraPapel != null) leituraPapelList.add(leituraPapel);

            leituraPapel = LeituraPapel.LIDO_PARCIALMENTE_AUDITOR_QUALIDADE_FESC;

            Long quantidadeTotalNotificacoesAuditorQualidadeFesc =
                    processoGridResponse.getQuantidadeTotalNotificacoesAuditorQualidadeFesc()
                            + processoGridResponse.getQuantidadeTotalTelegramasAuditorQualidadeFesc();
            Long quantidadeTotalNotificacoesNaoLidasAuditorQualidadeFesc =
                    processoGridResponse.getQuantidadeTotalNotificacoesNaoLidasAuditorQualidadeFesc()
                            + processoGridResponse.getQuantidadeTotalTelegramasNaoLidosAuditorQualidadeFesc();
            Long quantidadeTotalNotificacoesLidasAuditorQualidadeFesc =
                    processoGridResponse.getQuantidadeTotalNotificacoesLidasAuditorQualidadeFesc()
                            + processoGridResponse.getQuantidadeTotalTelegramasLidosAuditorQualidadeFesc();

            if (quantidadeTotalNotificacoesAuditorQualidadeFesc == 0L) {
                leituraPapel = null;
            } else if (quantidadeTotalNotificacoesAuditorQualidadeFesc == quantidadeTotalNotificacoesNaoLidasAuditorQualidadeFesc) {
                leituraPapel = LeituraPapel.NAO_LIDO_AUDITOR_QUALIDADE_FESC;
            } else if (quantidadeTotalNotificacoesAuditorQualidadeFesc == quantidadeTotalNotificacoesLidasAuditorQualidadeFesc) {
                leituraPapel = LeituraPapel.LIDO_AUDITOR_QUALIDADE_FESC;
            }

            if (leituraPapel != null) leituraPapelList.add(leituraPapel);

            leituraPapel = LeituraPapel.LIDO_PARCIALMENTE_DESEMPATADOR;

            Long quantidadeTotalNotificacoesDesempatador = processoGridResponse.getQuantidadeTotalNotificacoesDesempatador()
                    + processoGridResponse.getQuantidadeTotalTelegramasDesempatador();
            Long quantidadeTotalNotificacoesNaoLidasDesempatador =
                    processoGridResponse.getQuantidadeTotalNotificacoesNaoLidasDesempatador()
                            + processoGridResponse.getQuantidadeTotalTelegramasNaoLidosDesempatador();
            Long quantidadeTotalNotificacoesLidasDesempatador =
                    processoGridResponse.getQuantidadeTotalNotificacoesLidasDesempatador()
                            + processoGridResponse.getQuantidadeTotalTelegramasLidosDesempatador();

            if (quantidadeTotalNotificacoesDesempatador == 0L) {
                leituraPapel = null;
            } else if (quantidadeTotalNotificacoesDesempatador == quantidadeTotalNotificacoesNaoLidasDesempatador) {
                leituraPapel = LeituraPapel.NAO_LIDO_DESEMPATADOR;
            } else if (quantidadeTotalNotificacoesDesempatador == quantidadeTotalNotificacoesLidasDesempatador) {
                leituraPapel = LeituraPapel.LIDO_DESEMPATADOR;
            }

            if (leituraPapel != null) leituraPapelList.add(leituraPapel);

            processoGridResponse.setLeituraPapelList(leituraPapelList);
        }

        return processoGridResponseList;
    }

    @Override
    public Page<ProcessoGridResponse> findAllGrid(ProcessoFiltersRequest processoFiltersRequest, Pageable pageable) {
        String hql = this.createProcessoHql(processoFiltersRequest, false, true, false);

        if (pageable != null && pageable.getSort() != null) {
            String sortBy = StringUtils.substringBefore(pageable.getSort().toString(), ":");
            hql = QueryUtils.applySorting(hql, this.formatGridSort(pageable.getSort(), sortBy), null);
        }

        Query query = entityManager.createQuery(hql);

        if (pageable != null && pageable.isPaged()) {
            if (processoFiltersRequest.getDmContagemSla() != null
                    && !processoFiltersRequest.getDmContagemSla().equals("")) {
                List<ProcessoGridResponse> processos = query.getResultList();
                adicionaTempoRestanteSla(processos);

                ParametroResponse parametro = parametroService.findByNome("indicacao_tempo_diario_hrs_trabalho");
                processos.forEach(p -> p.setSlaProcessoEmDias(CalculadorSlaUtil.toDias(p.getSlaProcessoFormatado(), parametro, true)));
                processos = leituraNotificacaoProcesso(processos);
                processos = leituraPapelNotificacaoProcesso(processos);

                return filtraProcessosPorSla(pageable, processos, processoFiltersRequest.getDmContagemSla());
            }

            String countHql = this.createProcessoHql(processoFiltersRequest, true, true, false);

            Long totalProcessos = (Long) entityManager.createQuery(countHql).getSingleResult();

            int initialIndex = pageable.getPageNumber() * pageable.getPageSize();

            query.setFirstResult(initialIndex);
            query.setMaxResults(pageable.getPageSize());

            List<ProcessoGridResponse> resultList = query.getResultList();

            adicionaTempoRestanteSla(resultList);

            ParametroResponse parametro = parametroService.findByNome("indicacao_tempo_diario_hrs_trabalho");
            resultList.forEach(p -> p.setSlaProcessoEmDias(CalculadorSlaUtil.toDias(p.getSlaProcessoFormatado(), parametro, true)));
            resultList = leituraNotificacaoProcesso(resultList);
            resultList = leituraPapelNotificacaoProcesso(resultList);

            return new PageImpl<>(resultList, pageable, totalProcessos);
        }
        List<ProcessoGridResponse> resultList = query.getResultList();
        resultList = leituraNotificacaoProcesso(resultList);
        resultList = leituraPapelNotificacaoProcesso(resultList);

        return new PageImpl<>(resultList);
    }

    private String createProcessoHql(ProcessoFiltersRequest processoFiltersRequest, Boolean isCount,
                                     Boolean isGrid, Boolean isTotalizador) {
        String hql = "select ";

        Papel papel;

        try {
            PapelPerfil papelPerfil = papelPerfilService.findByPerfilIdEntity(
                    Objects.requireNonNull(SystemUtil.getCurrentUsuarioLogado()).getDefaultUsuarioPerfil().getPerfil().getId());
            papel = papelPerfil.getPapel();
        } catch (Exception e) {
            papel = null;
        }

        Notificacao notificacao = notificacaoService.findByNotificacaoTipo(NotificacaoTipo.TELEGRAMA);

        if (isCount) {
            hql += "count(p.id)";
        } else if (isTotalizador) {
            String caseMinhasPendencias = "sum(0)";
            String casePendenciasArea = "";
            String caseFinalizados = "sum(CASE WHEN p.conclusaoProcesso is not null THEN 1 ELSE 0 END)";
            String caseTotal = "count(1)";

            if (papel != null) {
                String nomePapel = papel.getNome().toLowerCase();
                if (nomePapel.compareTo("administrativo operadora") == 0) {
                    caseMinhasPendencias = "sum(CASE WHEN ao.id = "
                            + SystemUtil.getCurrentUsuarioLogado().getId() + " and (p.modoRascunho = true OR exists (" +
                            "select 1 from StatusEtapaAcao sea join StatusEtapaAcaoPerfil seap on sea.id = seap.statusEtapaAcao.id" +
                            " where sea.consideraPendencia = true" +
                            " and sea.statusEtapa.id = se.id and seap.perfilId = " +
                            SystemUtil.getCurrentUsuarioLogado().getDefaultUsuarioPerfil().getPerfil().getId() + ")) and p.conclusaoProcesso is null" +
                            " and p.dataCancelamento is null THEN 1 ELSE 0 END)";

                    casePendenciasArea = "ao.id != " + SystemUtil.getCurrentUsuarioLogado().getId();
                } else if (nomePapel.compareTo("administrativo fesc") == 0) {
                    caseMinhasPendencias = "sum(CASE WHEN af.id = "
                            + SystemUtil.getCurrentUsuarioLogado().getId() + " and (p.modoRascunho = true OR exists (" +
                            " select 1 from StatusEtapaAcao sea join StatusEtapaAcaoPerfil seap " +
                            "   on sea.id = seap.statusEtapaAcao.id" +
                            " where sea.consideraPendencia = true and" +
                            " sea.statusEtapa.id = se.id and seap.perfilId = " +
                            SystemUtil.getCurrentUsuarioLogado().getDefaultUsuarioPerfil().getPerfil().getId() + " )) and p.conclusaoProcesso is null" +
                            " and p.dataCancelamento is null THEN 1 ELSE 0 END)";

                    casePendenciasArea = "af.id != " + SystemUtil.getCurrentUsuarioLogado().getId();
                } else if (nomePapel.compareTo("auditor qualidade fesc") == 0) {
                    caseMinhasPendencias = "sum(CASE WHEN aq.id = "
                            + SystemUtil.getCurrentUsuarioLogado().getId() + " and exists (select 1 from StatusEtapaAcao sea" +
                            " join StatusEtapaAcaoPerfil seap on sea.id = seap.statusEtapaAcao.id" +
                            " where sea.consideraPendencia = true" +
                            " and sea.statusEtapa.id = se.id and seap.perfilId = " +
                            SystemUtil.getCurrentUsuarioLogado().getDefaultUsuarioPerfil().getPerfil().getId() + " ) and p.conclusaoProcesso is null" +
                            " and p.dataCancelamento is null THEN 1 ELSE 0 END)";

                    casePendenciasArea = "aq.id != " + SystemUtil.getCurrentUsuarioLogado().getId();
                } else if (nomePapel.compareTo("desempatador") == 0) {
                    caseMinhasPendencias = "sum(CASE WHEN d.usuarioId = " +
                            SystemUtil.getCurrentUsuarioLogado().getId() + " and exists (select 1 from StatusEtapaAcao sea" +
                            " join StatusEtapaAcaoPerfil seap on sea.id = seap.statusEtapaAcao.id" +
                            " where sea.consideraPendencia = true" +
                            " and sea.statusEtapa.id = se.id and seap.perfilId = " +
                            SystemUtil.getCurrentUsuarioLogado().getDefaultUsuarioPerfil().getPerfil().getId() + ") and p.conclusaoProcesso is null" +
                            " and p.dataCancelamento is null THEN 1 ELSE 0 END)";

                    caseFinalizados = "sum(CASE WHEN d.usuarioId = " + SystemUtil.getCurrentUsuarioLogado().getId() +
                            " and p.conclusaoProcesso is not null THEN 1 ELSE 0 END)";

                    caseTotal = "sum(CASE WHEN d.usuarioId = "
                            + SystemUtil.getCurrentUsuarioLogado().getId() + " THEN 1 ELSE 0 END)";

                    casePendenciasArea = "sum(0)";
                }

                if (nomePapel.compareTo("desempatador") != 0) {
                    String where = casePendenciasArea.isEmpty() ? " exists " : casePendenciasArea + " AND exists ";
                    casePendenciasArea = "sum(CASE WHEN " + where +
                            "(select 1 from StatusEtapaAcao sea join StatusEtapaAcaoPerfil seap " +
                            " on sea.id = seap.statusEtapaAcao.id" +
                            " where sea.consideraPendencia = true and sea.statusEtapa.id = se.id and seap.perfilId = " +
                            SystemUtil.getCurrentUsuarioLogado().getDefaultUsuarioPerfil().getPerfil().getId() + ")" +
                            " THEN 1 ELSE 0 END)";
                }
            }

            hql += "new com.juntamedica.processo.payload.ProcessoTotalizadorPendenciasResponse("
                    + caseMinhasPendencias + ", " + casePendenciasArea + ", " + caseFinalizados
                    + ", " + caseTotal + ")";
        } else {
            if (isGrid) {
                hql += "new com.juntamedica.processo.payload.ProcessoGridResponse(p.id, p.modoRascunho, p.prioridade,"
                        + "b.nome, pe.nome, se.id, se.nome, p.inicioProcesso, p.numeroPedidoGuia, d.nome, pa.nome, "
                        + "fp.id, fp.nomeApresentacao, os.nomeFantasia, "
                        + "CASE WHEN p.documentacaoCompleta IS NULL OR p.documentacaoCompleta = 0 THEN 'Não' "
                        + "ELSE 'Sim' END as documentacaoCompleta, p.dataCriacao, p.dataParecer, "
                        + "p.prazoInicialAns, p.prazoFinalAns, p.dataReprovacaoOperadora, p.conclusaoProcesso, "
                        + "aq.id, aq.name, af.id, af.name, ao.id, ao.name, co.id, cse.id, "
                        + "se.preveSla, csla.valor, p.dataEstimadaConclusao, p.dataCancelamento, pe.ordem, "
                        + "p.operadoraSolicitante.id, "
                        + "(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.dataCancelamento is null) as qtd_total, "
                        + "(select count(1) from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.dataCancelamento is null) as qtd_lidos, "
                        + "(select count(1) from "
                        + "ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.dataCancelamento is null) as qtd_nao_lidos, "

                        + "(select count(1) from "
                        + "ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 1 "
                        + "and u.id = p.administrativoOperadora.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_total_adm_operadora,"
                        + "(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 1 "
                        + "and u.id = p.administrativoOperadora.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null)"
                        +" as qtd_lidos_adm_operadora, "
                        +"(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 1 "
                        + "and u.id = p.administrativoOperadora.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_nao_lidos_admn_operadora, "

                        + "(select count(1) from "
                        + "ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 2 "
                        + "and u.id = p.auditor.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_total_aud_operadora, "
                        + "(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 2 "
                        + "and u.id = p.auditor.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null)"
                        +" as qtd_lidos_aud_operadora, "
                        +"(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 2 "
                        + "and u.id = p.auditor.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_nao_lidos_aud_operadora, "

                        + "(select count(1) from "
                        + "ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Contato c "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 3 "
                        + "and c.profissionalAssistente.id = p.profissionalAssistente.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_total_prof_assistente, "
                        + "(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Contato c "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 3 "
                        + "and c.profissionalAssistente.id = p.profissionalAssistente.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null)"
                        +" as qtd_lidos_prof_assistente, "
                        +"(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Contato c "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 3 "
                        + "and c.profissionalAssistente.id = p.profissionalAssistente.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_nao_lidos_prof_assistente, "

                        + "(select count(1) from "
                        + "ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Contato c "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 4 "
                        + "and c.beneficiario.id = p.beneficiario.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_total_beneficiario, "
                        + "(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Contato c "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 4 "
                        + "and c.beneficiario.id = p.beneficiario.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null)"
                        + " as qtd_lidos_beneficiario, "
                        + "(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Contato c "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 4 "
                        + "and c.beneficiario.id = p.beneficiario.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_nao_lidos_beneficiario, "

                        + "(select count(1) from "
                        + "ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 5 "
                        + "and u.id = p.administrativoFesc.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_total_adm_fesc, "
                        + "(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 5 "
                        + "and u.id = p.administrativoFesc.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null)"
                        +" as qtd_lidos_adm_fesc, "
                        +"(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 5 "
                        + "and u.id = p.administrativoFesc.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_nao_lidos_adm_fesc, "

                        + "(select count(1) from "
                        + "ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 6 "
                        + "and u.id = p.auditorQualidade.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_total_aud_qualidade_fesc, "
                        + "(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 6 "
                        + "and u.id = p.auditorQualidade.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null)"
                        +" as qtd_lidos_aud_qualidade_fesc, "
                        +"(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Usuario u "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 6 "
                        + "and u.id = p.auditorQualidade.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_nao_lidos_aud_qualidade_fesc, "

                        + "(select count(1) from "
                        + "ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Contato c "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 7 "
                        + "and c.desempatador.id = p.desempatador.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_total_desempatador, "
                        + "(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Contato c "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 7 "
                        + "and c.desempatador.id = p.desempatador.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null)"
                        + " as qtd_lidos_desempatador, "
                        + "(select count(1) "
                        + "from ModeloCarta mc, "
                        + "ProcessoAnexo pa, "
                        + "ProcessoHistoricoNotificacao phn, "
                        + "ProcessoEtapa pe, "
                        + "StatusEtapa se, "
                        + "Contato c "
                        + "where mc.processoEtapa.id = pe.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and pa.modeloCarta.id = mc.id "
                        + "and pa.processo.id = p.id "
                        + "and phn.processo.id = p.id "
                        + "and phn.modeloCarta.id = mc.id "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.papel.id = 7 "
                        + "and c.desempatador.id = p.desempatador.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) "
                        + "as qtd_nao_lidos_desempatador, "

                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn "
                        + "where phn.processo.id = p.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn "
                        + "where phn.processo.id = p.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_lidos, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn "
                        + "where phn.processo.id = p.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_nao_lidos, "

                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and u.id = p.administrativoOperadora.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 1 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_administrativo_operadora, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and u.id = p.administrativoOperadora.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 1 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_lidos_administrativo_operadora, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and u.id = p.administrativoOperadora.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 1 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_nao_lidos_administrativo_operadora, "

                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and u.id = p.auditor.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 2 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_auditor_operadora, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and u.id = p.auditor.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 2 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_lidos_auditor_operadora, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and u.id = p.auditor.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 2 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_nao_lidos_auditor_operadora, "

                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Contato c "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 3 "
                        + "and c.profissionalAssistente.id = p.profissionalAssistente.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_prof_assistente, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Contato c "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 3 "
                        + "and c.profissionalAssistente.id = p.profissionalAssistente.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_lidos_prof_assistente, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Contato c "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 3 "
                        + "and c.profissionalAssistente.id = p.profissionalAssistente.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_nao_lidos_prof_assistente, "

                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Contato c "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 4 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and c.beneficiario.id = p.beneficiario.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_beneficiario, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Contato c "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 4 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and c.beneficiario.id = p.beneficiario.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_lidos_beneficiario, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Contato c "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 4 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and c.beneficiario.id = p.beneficiario.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_nao_lidos_beneficiario, "

                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 5 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and u.id = p.administrativoFesc.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_adm_fesc, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 5 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and u.id = p.administrativoFesc.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_lidos_adm_fesc, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 5 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and u.id = p.administrativoFesc.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_nao_lidos_adm_fesc, "

                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 6 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and u.id = p.auditorQualidade.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_auditor_qualidade_fesc, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 6 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and u.id = p.auditorQualidade.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_lidos_auditor_qualidade_fesc, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Usuario u "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 6 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and u.id = p.auditorQualidade.id "
                        + "and u.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_nao_lidos_auditor_qualidade_fesc, "

                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Contato c "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 7 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and c.desempatador.id = p.desempatador.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_desempatador, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Contato c "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 7 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is not null "
                        + "and c.desempatador.id = p.desempatador.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_lidos_desempatador, "
                        + "(select count(1) "
                        + "from ProcessoHistoricoNotificacao phn, StatusEtapa se, ProcessoEtapa pe, Contato c "
                        + "where phn.processo.id = p.id "
                        + "and p.statusEtapa.id = se.id "
                        + "and se.processoEtapa.id = pe.id "
                        + "and phn.notificacao.id = " + notificacao.getId() + " "
                        + "and phn.papel.id = 7 "
                        + "and phn.comprovanteObrigatorio = 1 "
                        + "and phn.comprovanteDataRecebimento is null "
                        + "and c.desempatador.id = p.desempatador.id "
                        + "and c.email = phn.emailDestinatario "
                        + "and phn.dataCancelamento is null) as qtd_total_telegramas_nao_lidos_desempatador "
                        + ")";

            } else {
                hql += "p";
            }
        }

        hql += " from Processo p";
        hql += " left join p.beneficiario b";
        hql += " left join p.statusEtapa se";
        hql += " left join se.dmContagemSla csla";
        hql += " left join se.processoEtapa pe";
        hql += " left join se.calendario cse";
        hql += " left join p.desempatador d";
        hql += " left join p.profissionalAssistente pa";
        hql += " left join p.fluxoProcesso fp";
        hql += " left join p.operadoraSolicitante os";
        hql += " left join p.contrato co";
        hql += " left join p.auditorQualidade aq";
        hql += " left join p.administrativoFesc af";
        hql += " left join p.administrativoOperadora ao";

        if (!isGrid && !isTotalizador) {
            hql += " left join p.auditor a";
        }

        String where = " (p.modoRascunho = false or ao.id = "
                + SystemUtil.getCurrentUsuarioLogado().getId() + ") ";

        if (SystemUtil.getCurrentUsuarioLogado().getOperadora() != null) {
            where += where.length() > 0 ? " and " : " ";
            where += "os.id = " + SystemUtil.getCurrentUsuarioLogado().getOperadora().getId();
        }

        if (processoFiltersRequest != null) {
            if (!isTotalizador && processoFiltersRequest.getFiltroProcesso() != null) {
                if (TipoFiltro.from(processoFiltersRequest.getFiltroProcesso()).equals(TipoFiltro.MINHAS_PENDENCIAS)) {
                    if (papel != null) {
                        if (papel.getNome().toLowerCase().compareTo("administrativo operadora") == 0) {
                            where += where.length() > 0 ? " and " : " ";
                            where += "ao.id = " + SystemUtil.getCurrentUsuarioLogado().getId();
                        } else if (papel.getNome().toLowerCase().compareTo("administrativo fesc") == 0) {
                            where += where.length() > 0 ? " and " : " ";
                            where += "af.id = " + SystemUtil.getCurrentUsuarioLogado().getId();
                        } else if (papel.getNome().toLowerCase().compareTo("auditor qualidade fesc") == 0) {
                            where += where.length() > 0 ? " and " : " ";
                            where += "aq.id = " + SystemUtil.getCurrentUsuarioLogado().getId();
                        } else if (papel.getNome().toLowerCase().compareTo("desempatador") == 0) {
                            where += where.length() > 0 ? " and " : " ";
                            where += "d.usuarioId = " + SystemUtil.getCurrentUsuarioLogado().getId();
                        }
                    }

                    where += where.length() > 0 ? " and " : " ";
                    where += "(p.modoRascunho = true OR exists (select 1 from StatusEtapaAcao sea join StatusEtapaAcaoPerfil seap "
                            + " on sea.id = seap.statusEtapaAcao.id"
                            + " where sea.consideraPendencia = true and sea.statusEtapa.id = se.id and seap.perfilId = "
                            +  SystemUtil.getCurrentUsuarioLogado().getDefaultPerfil().getPerfil().getId() + "))";
                    where += " and p.conclusaoProcesso is null and p.dataCancelamento is null";
                } else if (TipoFiltro.from(processoFiltersRequest.getFiltroProcesso())
                        .equals(TipoFiltro.PENDENCIAS_AREA)) {
                    if (papel != null) {
                        if (papel.getNome().toLowerCase().compareTo("administrativo operadora") == 0) {
                            where += where.length() > 0 ? " and " : " ";
                            where += "ao.id != " + SystemUtil.getCurrentUsuarioLogado().getId();
                        } else if (papel.getNome().toLowerCase().compareTo("administrativo fesc") == 0) {
                            where += where.length() > 0 ? " and " : " ";
                            where += "af.id != " + SystemUtil.getCurrentUsuarioLogado().getId();
                        } else if (papel.getNome().toLowerCase().compareTo("auditor qualidade fesc") == 0) {
                            where += where.length() > 0 ? " and " : " ";
                            where += "aq.id != " + SystemUtil.getCurrentUsuarioLogado().getId();
                        } else if (papel.getNome().toLowerCase().compareTo("desempatador") == 0) {
                            where += where.length() > 0 ? " and " : " ";
                            where += "d.usuarioId != " + SystemUtil.getCurrentUsuarioLogado().getId();
                        }
                    }

                    where += where.length() > 0 ? " and " : "";
                    where += " exists (select 1 from StatusEtapaAcao sea join StatusEtapaAcaoPerfil seap "
                           + " on sea.id = seap.statusEtapaAcao.id"
                           + " where sea.consideraPendencia = true and sea.statusEtapa.id = se.id and seap.perfilId = "
                           +  SystemUtil.getCurrentUsuarioLogado().getDefaultPerfil().getPerfil().getId() + ")";
                    where += " and p.conclusaoProcesso is null and p.dataCancelamento is null";
                } else if (TipoFiltro.from(processoFiltersRequest.getFiltroProcesso()).equals(TipoFiltro.FINALIZADOS)) {
                    if (papel != null) {
                        if (papel.getNome().toLowerCase().compareTo("desempatador") == 0) {
                            where += where.length() > 0 ? " and " : " ";
                            where += "d.usuarioId = " + SystemUtil.getCurrentUsuarioLogado().getId();
                        }
                    }

                    where += where.length() > 0 ? " and " : " ";
                    where += "(p.conclusaoProcesso is not null)";
                } else if (TipoFiltro.from(processoFiltersRequest.getFiltroProcesso()).equals(TipoFiltro.TODOS)) {
                    if (papel != null) {
                        if (papel.getNome().toLowerCase().compareTo("desempatador") == 0) {
                            where += where.length() > 0 ? " and " : " ";
                            where += "d.usuarioId = " + SystemUtil.getCurrentUsuarioLogado().getId();
                        }
                    }
                }
            }

            if (processoFiltersRequest.getNumeroProcesso() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "p.id = " + processoFiltersRequest.getNumeroProcesso();
            }

            if (processoFiltersRequest.getNumeroPedidoGuia() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "p.numeroPedidoGuia = " + processoFiltersRequest.getNumeroPedidoGuia();
            }

            if (processoFiltersRequest.getCodigoBeneficiario() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "b.codigo = " + String.format("'%s'", processoFiltersRequest.getCodigoBeneficiario());
            }

            if (processoFiltersRequest.getPeriodoPrazoFinalInicio() != null
                    && processoFiltersRequest.getPeriodoPrazoFinalFim() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "p.prazoFinalAns between '"
                        + DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        .format(processoFiltersRequest.getPeriodoPrazoFinalInicio().toLocalDate())
                        + "' and '"
                        + DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        .format(processoFiltersRequest.getPeriodoPrazoFinalFim().toLocalDate()) + "'";
            }

            if (processoFiltersRequest.getPeriodoCriacaoInicio() != null
                    && processoFiltersRequest.getPeriodoCriacaoFim() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "p.dataCriacao between to_date('"
                        + DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        .format(processoFiltersRequest.getPeriodoCriacaoInicio().toLocalDate())
                        + " " +
                        DateTimeFormatter.ofPattern("HH:mm:ss")
                                .format(processoFiltersRequest.getPeriodoCriacaoInicio().toLocalTime())
                        + "', 'dd/MM/yyyy hh24:mi:ss') and to_date('"
                        + DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        .format(processoFiltersRequest.getPeriodoCriacaoFim().toLocalDate())
                        + " "
                        + DateTimeFormatter.ofPattern("HH:mm:ss")
                        .format(processoFiltersRequest.getPeriodoCriacaoFim().toLocalTime())
                        + "', 'dd/MM/yyyy hh24:mi:ss')";
            }

            if (processoFiltersRequest.getPeriodoConclusaoInicio() != null
                    && processoFiltersRequest.getPeriodoConclusaoFim() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "p.dataEstimadaConclusao between to_date('"
                        + DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        .format(processoFiltersRequest.getPeriodoConclusaoInicio().toLocalDate())
                        + " "
                        + DateTimeFormatter.ofPattern("HH:mm:ss")
                        .format(processoFiltersRequest.getPeriodoConclusaoInicio().toLocalTime())
                        + "', 'dd/MM/yyyy hh24:mi:ss') and to_date('"
                        + DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        .format(processoFiltersRequest.getPeriodoConclusaoFim().toLocalDate())
                        + " "
                        + DateTimeFormatter.ofPattern("HH:mm:ss")
                        .format(processoFiltersRequest.getPeriodoConclusaoFim().toLocalTime())
                        + "', 'dd/MM/yyyy hh24:mi:ss')";
            }

            if (processoFiltersRequest.getStatusProcessoId() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "se.id = " + processoFiltersRequest.getStatusProcessoId();
            }

            if (processoFiltersRequest.getEtapaProcesso() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "pe.nome like '%" + processoFiltersRequest.getEtapaProcesso()+"%'";
            }

            if (processoFiltersRequest.getProfissionalAssistenteNome() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "upper(pa.nome) like " + String.format("'%s'", processoFiltersRequest.getProfissionalAssistenteNome());
            }

            if (processoFiltersRequest.getDesempatadorId() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "d.id = " + processoFiltersRequest.getDesempatadorId();
            }

            if (processoFiltersRequest.getAdministrativoOperadoraId() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "ao.id = " + processoFiltersRequest.getAdministrativoOperadoraId();
            }

            if (processoFiltersRequest.getAdministrativoFescExtId() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "af.id = " + processoFiltersRequest.getAdministrativoFescExtId();
            }

            if (processoFiltersRequest.getOperadoraSolicitanteId() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "os.id = " + processoFiltersRequest.getOperadoraSolicitanteId();
            }

            if (processoFiltersRequest.getShowRascunho() != null && !processoFiltersRequest.getShowRascunho()) {
                where += where.length() > 0 ? " and " : " ";
                where += "p.modoRascunho = " + false;
            }
        }

        where = where.length() > 0 ? " where " + where : "";
        hql += where;

        return hql;
    }

    private Sort formatGridSort(Sort currentSort, String sortBy) {
        ProcessoSortByEnum processoSortByEnum = ProcessoSortByEnum.get(sortBy);
        String sortDesc = StringUtils.substringAfter(currentSort.toString(), ":").trim();
        Sort.Direction direction = sortDesc.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;

        if (Objects.isNull(processoSortByEnum)) {
            Sort.Order currentOrder = new Sort.Order(direction, "p.".concat(sortBy));
            return Sort.by(currentOrder);
        }

        Sort.Order order = new Sort.Order(direction, processoSortByEnum.getColunaTabela());
        if (processoSortByEnum.getTipoColuna().compareTo("String") == 0) {
            order = order.ignoreCase();
        }

        return Sort.by(order);
    }

    private List<String> validateBeneficiario(BeneficiarioRequest beneficiarioRequest) {
        List<String> listValidations = new ArrayList<>();

        if (beneficiarioRequest.getCodigo() == null) {
            listValidations.add("Código do Beneficiário deve ser informado.");
        }

        if (beneficiarioRequest.getNome() == null) {
            listValidations.add("Nome do Beneficiário deve ser informado.");
        }

        if (beneficiarioRequest.getDmSexoBeneficiarioId() == null) {
            listValidations.add("Sexo do Beneficiário deve ser informado.");
        }

        if (beneficiarioRequest.getContatoList() == null || beneficiarioRequest.getContatoList().size() < 2) {
            listValidations.add("Email/Telefone do Beneficiário deve ser informado.");
        } else {
            Integer telefoneCelular = 0;
            Integer telefoneFixo = 0;
            Integer email = 0;
            DominioResponse dominioCelular = dominioService.findByNomeAndValor("dm_tipo_contato", "C");
            DominioResponse dominioFixo = dominioService.findByNomeAndValor("dm_tipo_contato", "M");
            DominioResponse dominioEmail = dominioService.findByNomeAndValor("dm_tipo_contato", "E");
            for (ContatoRequest contatoRequest: beneficiarioRequest.getContatoList()) {
                if (contatoRequest.getDmTipoContatoId() == dominioCelular.getId()) telefoneCelular++;
                if (contatoRequest.getDmTipoContatoId() == dominioFixo.getId()) telefoneFixo++;
                if (contatoRequest.getDmTipoContatoId() == dominioEmail.getId()) email++;
            }

            if (telefoneCelular + telefoneFixo == 0) {
                listValidations.add("telefone fixo ou telefone celular do Beneficiário deve ser informado.");
            }
            if (email == 0) {
                listValidations.add("Email do Beneficiário deve ser informado.");
            }
        }

        if (beneficiarioRequest.getDataNascimento() == null) {
            listValidations.add("Data de nascimento do Beneficiário deve ser informada.");
        } else {
            if (LocalDateTime.now().isBefore(beneficiarioRequest.getDataNascimento())) {
                listValidations.add("Data de nascimento deve ser menor que a data de hoje!");
            } else if (LocalDateTime.now().compareTo(beneficiarioRequest.getDataNascimento()) >= 120) {
                listValidations.add("O beneficiário deve ter menos de 120 anos!");
            }
        }

        if (beneficiarioRequest.getEndereco() == null) {
            listValidations.add("Endereço do Beneficiário deve ser informado.");
        }

        if (beneficiarioRequest.getEndereco().getCep() == null) {
            listValidations.add("Cep do Beneficiário deve ser informado.");
        }

        if (beneficiarioRequest.getEndereco().getLogradouro() == null ||
                beneficiarioRequest.getEndereco().getLogradouro().length() == 0) {
            listValidations.add("Logradouro do Beneficiário deve ser informado.");
        }

        if (beneficiarioRequest.getEndereco().getNumero() == null ||
                beneficiarioRequest.getEndereco().getNumero().length() == 0) {
            listValidations.add("Número do Beneficiário deve ser informado.");
        }

        if (beneficiarioRequest.getEndereco().getBairro() == null ||
                beneficiarioRequest.getEndereco().getBairro().length() == 0) {
            listValidations.add("Bairro do Beneficiário deve ser informado.");
        }

        if (beneficiarioRequest.getEndereco().getLocalidade() == null ||
                beneficiarioRequest.getEndereco().getLocalidade().length() == 0) {
            listValidations.add("Cidade do Beneficiário deve ser informado.");
        }

        if (beneficiarioRequest.getEndereco().getUf() == null ||
                beneficiarioRequest.getEndereco().getUf().length() == 0) {
            listValidations.add("Estado do Beneficiário deve ser informado.");
        }

        return listValidations;
    }

    private List<String> validateProfissionalAssistente(ProfissionalAssistenteRequest profissionalAssistenteRequest) {
        List<String> listValidations = new ArrayList<>();

        if (profissionalAssistenteRequest.getNome() == null) {
            listValidations.add("Nome do Profissional Assistente deve ser informado.");
        }

        if (profissionalAssistenteRequest.getConselhoList() == null) {
            listValidations.add("Conselho do Profissional Assistente deve ser informado.");
        } else {
            if (profissionalAssistenteRequest.getConselhoList().isEmpty()) {
                listValidations.add("Conselho do Profissional Assistente deve ser informado.");
            } else {
                for (ProfissionalAssistenteConselhoRequest conselho : profissionalAssistenteRequest.getConselhoList()) {
                    if (conselho.getDmTipoConselhoId() == null) {
                        listValidations.add("Tipo do conselho do profissional assistente é obrigatório.");
                    } else if (conselho.getNumeroConselho() == null) {
                        listValidations.add("Número do conselho do profissional assistente é obrigatório.");
                    } else if (conselho.getUfConselho() == null) {
                        listValidations.add("UF do conselho do profissional assistente é obrigatório.");
                    }
                }
            }
        }

        if (profissionalAssistenteRequest.getEspecialidadeList() == null) {
            listValidations.add("Especialidade do profissional assistente é obrigatório.");
        } else {
            if (profissionalAssistenteRequest.getEspecialidadeList().isEmpty()) {
                listValidations.add("Especialidade do profissional assistente é obrigatório.");
            } else {
                for (ProfissionalAssistenteEspecialidadeRequest especialidade : profissionalAssistenteRequest.getEspecialidadeList()) {
                    if (especialidade.getEspecialidadeId() == null) {
                        listValidations.add("Código da especialidade do profissional assistente é obrigatório.");
                    }
                }
            }
        }
        if (profissionalAssistenteRequest.getContatoList() == null || profissionalAssistenteRequest.getContatoList().size() < 2) {
            listValidations.add("Email/Telefone do profissional assistente deve ser informado.");
        } else {
            Integer telefoneCelular = 0;
            Integer telefoneComercial = 0;
            Integer email = 0;

            DominioResponse dominioCelular = dominioService.findByNomeAndValor("dm_tipo_contato", "C");
            DominioResponse dominioComercial = dominioService.findByNomeAndValor("dm_tipo_contato", "M");
            DominioResponse dominioEmail = dominioService.findByNomeAndValor("dm_tipo_contato", "E");

            for (ContatoRequest contatoRequest: profissionalAssistenteRequest.getContatoList()) {
                if (contatoRequest.getDmTipoContatoId() == dominioCelular.getId()) telefoneCelular++;
                if (contatoRequest.getDmTipoContatoId() == dominioComercial.getId()) telefoneComercial++;
                if (contatoRequest.getDmTipoContatoId() == dominioEmail.getId()) email++;
            }

            if (telefoneCelular + telefoneComercial == 0) {
                listValidations.add("Telefone celular ou Telefone comercial do profissional assistente deve ser informado.");
            }
            if (email == 0) {
                listValidations.add("Email do profissional assistente deve ser informado.");
            }
        }

        if (profissionalAssistenteRequest.getEndereco() == null) {
            listValidations.add("Endereço do profissional assistente deve ser informado.");
        }

        if (profissionalAssistenteRequest.getEndereco().getCep() == null) {
            listValidations.add("Cep do profissional assistente deve ser informado.");
        }

        if (profissionalAssistenteRequest.getEndereco().getLogradouro() == null ||
                profissionalAssistenteRequest.getEndereco().getLogradouro().length() == 0) {
            listValidations.add("Logradouro do profissional assistente deve ser informado.");
        }

        if (profissionalAssistenteRequest.getEndereco().getNumero() == null ||
                profissionalAssistenteRequest.getEndereco().getNumero().length() == 0) {
            listValidations.add("Número do profissional assistente deve ser informado.");
        }

        if (profissionalAssistenteRequest.getEndereco().getBairro() == null ||
                profissionalAssistenteRequest.getEndereco().getBairro().length() == 0) {
            listValidations.add("Bairro do profissional assistente deve ser informado.");
        }

        if (profissionalAssistenteRequest.getEndereco().getLocalidade() == null ||
                profissionalAssistenteRequest.getEndereco().getLocalidade().length() == 0) {
            listValidations.add("Cidade do profissional assistente deve ser informado.");
        }

        if (profissionalAssistenteRequest.getEndereco().getUf() == null ||
                profissionalAssistenteRequest.getEndereco().getUf().length() == 0) {
            listValidations.add("Estado do profissional assistente deve ser informado.");
        }

        return listValidations;
    }

    private List<String> validateAuditor(AuditorRequest auditorRequest) {
        List<String> listValidations = new ArrayList<>();

        if (auditorRequest.getNome() == null) {
            listValidations.add("Nome do Auditor deve ser informado.");
        }

        if (auditorRequest.getConselhoList() == null) {
            listValidations.add("Conselho do Auditor deve ser informado.");
        } else {
            if (auditorRequest.getConselhoList().isEmpty()) {
                listValidations.add("Conselho do Auditor deve ser informado.");
            } else {
                for (AuditorConselhoRequest conselho : auditorRequest.getConselhoList()) {
                    if (conselho.getDmTipoConselhoId() == null) {
                        listValidations.add("Tipo do conselho do auditor é obrigatório.");
                    } else if (conselho.getNumeroConselho() == null) {
                        listValidations.add("Número do conselho do auditor é obrigatório.");
                    } else if (conselho.getUfConselho() == null) {
                        listValidations.add("UF do conselho do auditor é obrigatório.");
                    }
                }
            }
        }

        if (auditorRequest.getEspecialidadeList() == null) {
            listValidations.add("Especialidade do auditor é obrigatório.");
        } else {
            if (auditorRequest.getEspecialidadeList().isEmpty()) {
                listValidations.add("Especialidade do auditor é obrigatório.");
            } else {
                for (AuditorEspecialidadeRequest especialidadeRequest : auditorRequest.getEspecialidadeList()) {
                    if (especialidadeRequest.getEspecialidadeId() == null) {
                        listValidations.add("Código da especialidade do auditor é obrigatório.");
                    }
                }
            }
        }

        return listValidations;
    }

    private List<String> validateConsultor(ConsultorRequest consultorRequest) {
        if ((consultorRequest.getConselhoList() == null || consultorRequest.getConselhoList().size() == 0)
                && consultorRequest.getNome() == null
                && (consultorRequest.getEspecialidadeList() == null || consultorRequest.getEspecialidadeList().size() == 0)) {
            return Collections.emptyList();
        }

        List<String> listValidations = new ArrayList<>();

        if (consultorRequest.getNome() == null) {
            listValidations.add("Nome do Consultor deve ser informado.");
        }

        if (consultorRequest.getConselhoList() == null) {
            listValidations.add("Conselho do Consultor deve ser informado.");
        } else {
            if (consultorRequest.getConselhoList().isEmpty()) {
                listValidations.add("Conselho do Consultor deve ser informado.");
            } else {
                for (ConsultorConselhoRequest conselho : consultorRequest.getConselhoList()) {
                    if (conselho.getDmTipoConselhoId() == null) {
                        listValidations.add("Tipo do conselho do consultor é obrigatório.");
                    } else if (conselho.getNumeroConselho() == null) {
                        listValidations.add("Número do conselho do consultor é obrigatório.");
                    } else if (conselho.getUfConselho() == null) {
                        listValidations.add("UF do conselho do consultor é obrigatório.");
                    }
                }
            }
        }

        if (consultorRequest.getEspecialidadeList() == null) {
            listValidations.add("Especialidade do consultor é obrigatório.");
        } else {
            if (consultorRequest.getEspecialidadeList().isEmpty()) {
                listValidations.add("Especialidade do consultor é obrigatório.");
            } else {
                for (ConsultorEspecialidadeRequest especialidade : consultorRequest.getEspecialidadeList()) {
                    if (especialidade.getEspecialidadeId() == null) {
                        listValidations.add("Código da especialidade do consultor é obrigatório.");
                    }
                }
            }
        }

        return listValidations;
    }

    private List<String> validateProcesso(ProcessoRequest processoRequest) {
        List<String> listValidations = new ArrayList<>();

        if (processoRequest.getNumeroPedidoGuia() == null) {
            listValidations.add("O Nº Pedido/Guia é obrigatório.");
        } else if (processoRequest.getDmCaraterAtendimentoId() == null) {
            listValidations.add("O Caráter de Atendimento é obrigatório.");
        } else if (processoRequest.getPrazoInicialAns() == null) {
            listValidations.add("O Prazo Inicial ANS é obrigatório.");
        } else if (processoRequest.getPrazoInicialAns() == null) {
            listValidations.add("O Prazo Final ANS é obrigatório.");
        } else if (processoRequest.getPrazoInicialAns() != null && processoRequest.getPrazoFinalAns() != null
                && processoRequest.getPrazoInicialAns().isAfter(processoRequest.getPrazoFinalAns())) {
            listValidations.add("O Prazo Inicial ANS não deve ser maior que o Prazo Final ANS");
        } else if (processoRequest.getIndicacaoClinica() == null || processoRequest.getIndicacaoClinica().length() == 0) {
            listValidations.add("Indicação clinica é obrigatório.");
        } else if (processoRequest.getProcessoItemList().size() == 0) {
            listValidations.add("Item é obrigatório");
        } else if (processoRequest.getProcessoItemList() != null) {
            boolean isNecessaryJustification = false;

            DominioResponse dmParecerOperadoraDesfavoravel =
                    dominioService.findByNomeAndValor("dm_parecer_operadora", "D");

            for (ProcessoItemRequest processoItemRequest : processoRequest.getProcessoItemList()) {
                if (processoItemRequest.getDescricao() == null) {
                    listValidations.add("A descrição do item é obrigatória.");
                    break;
                } else if (processoItemRequest.getItemTipoId() == null) {
                    listValidations.add("O tipo do item é obrigatório.");
                    break;
                } else if (processoItemRequest.getValorUnitario() == null) {
                    listValidations.add("O valor do item é obrigatório.");
                    break;
                }else if (processoItemRequest.getQuantidade() == null) {
                    listValidations.add("A quantidade do item é obrigatório.");
                    break;
                }
                else if (processoItemRequest.getDmParecerOperadoraId() == null) {
                    listValidations.add("O parecer da operadora é obrigatório.");
                    break;
                } else {
                    if (processoItemRequest.getDmParecerOperadoraId()
                            .compareTo(dmParecerOperadoraDesfavoravel.getId()) == 0) {
                        isNecessaryJustification = true;
                    }
                }
            }
            if (!isNecessaryJustification) {
                listValidations.add("Ao menos um item deve ser desfavorável.");
            }
        }
        if (processoRequest.getJustificativaOperadora() == null || processoRequest.getJustificativaOperadora().length() == 0) {
            listValidations.add("A justificativa da operadora é obrigatório.");
        }

        if (processoRequest.getBeneficiario() == null) {
            listValidations.add("O Beneficiário deve ser informado no processo.");
        } else {
            listValidations.addAll(validateBeneficiario(processoRequest.getBeneficiario()));
        }

        if (processoRequest.getProfissionalAssistente() == null) {
            listValidations.add("O Profissional Assistente deve ser informado no processo.");
        } else {
            listValidations.addAll(validateProfissionalAssistente(processoRequest.getProfissionalAssistente()));
        }

        if (processoRequest.getAuditor() == null) {
            listValidations.add("O Auditor deve ser informado no processo.");
        } else {
            listValidations.addAll(validateAuditor(processoRequest.getAuditor()));
        }

        if (processoRequest.getConsultor() != null) {
            listValidations.addAll(validateConsultor(processoRequest.getConsultor()));
        }

        if (processoRequest.getOperadoraOrigemId() == null) {
            listValidations.add("A operadora de origem não foi informada no processo.");
        }

        if (processoRequest.getAreaAtuacaoList() == null
                || processoRequest.getAreaAtuacaoList().isEmpty()) {
            listValidations.add("A Área de Atuação é obrigatória.");
        } else {
            this.validateAreaAtuacao(processoRequest.getAreaAtuacaoList());
        }

        return listValidations;
    }

    private List<Processo> findWithFilters(ProcessoFiltersRequest processoFiltersRequest) {
        String hql = this.createProcessoHql(processoFiltersRequest, false, false, false);
        Query query = entityManager.createQuery(hql);

        return query.getResultList();
    }

    private void updateProcessoForeignField(Processo processo, Long foreignKey, String fieldName) {
        switch (fieldName) {
            case "beneficiario":
                processo.setBeneficiario(beneficiarioService.findEntity(foreignKey));
                break;
            case "profissionalAssistente":
                processo.setProfissionalAssistente(profissionalAssistenteService.findEntity(foreignKey));
                break;
            case "auditor":
                processo.setAuditor(auditorService.findEntity(foreignKey));
                break;
            case "consultor":
                processo.setConsultor(consultorService.findEntity(foreignKey));
                break;
            case "desempatador":
                processo.setDesempatador(desempatadorService.findEntity(foreignKey));
                break;
            case "contrato":
                processo.setContrato(contratoService.findEntity(foreignKey));
                break;
            case "statusEtapa":
                processo.setStatusEtapa(statusEtapaService.findEntity(foreignKey));
                break;
            case "dmCancelamentoMotivado":
            case "dmMotivoCancelProcesso":
            case "dmCaraterAtendimento":
            case "dmCobrarProcesso":
                if (fieldName.equals("dmCancelamentoMotivado")) {
                    processo.setDmCancelamentoMotivado(dominioService.findEntity(foreignKey));
                } else if (fieldName.equals("dmMotivoCancelProcesso")) {
                    processo.setDmMotivoCancelProcesso(dominioService.findEntity(foreignKey));
                } else if (fieldName.equals("dmCaraterAtendimento")) {
                    processo.setDmCaraterAtendimento(dominioService.findEntity(foreignKey));
                } else {
                    processo.setDmCobrarProcesso(dominioService.findEntity(foreignKey));
                }
                break;
            case "operadoraContratante":
            case "operadoraOrigem":
            case "operadoraSolicitante":
                if (fieldName.equals("operadoraContratante")) {
                    processo.setOperadoraContratante(operadoraService.findEntity(foreignKey));
                } else if (fieldName.equals("operadoraOrigem")) {
                    processo.setOperadoraOrigem(operadoraService.findEntity(foreignKey));
                } else {
                    processo.setOperadoraSolicitante(operadoraService.findEntity(foreignKey));
                }
                break;
            case "auditorQualidade":
                processo.setAuditorQualidade(usuarioService.findEntityById(foreignKey));
                break;
            case "administrativoFesc":
                processo.setAdministrativoFesc(usuarioService.findEntityById(foreignKey));
                break;
            case "operadoraLayout":
                processo.setOperadoraLayout(operadoraLayoutService.findEntityById(foreignKey));
                break;
        }
    }

    private void mergeAnexosList(Processo processo, List<AnexoRequest> anexoRequestList) {
        List<AnexoRequest> deletados = anexoRequestList.stream()
                .filter(AnexoRequest::getDeleted).collect(Collectors.toList());
        List<AnexoRequest> existentes = anexoRequestList.stream()
                .filter(anexoRequest -> !anexoRequest.getIsNewFile() && !anexoRequest.getDeleted())
                .collect(Collectors.toList());
        List<AnexoRequest> novos = anexoRequestList.stream()
                .filter(AnexoRequest::getIsNewFile)
                .collect(Collectors.toList());

        if (!deletados.isEmpty()) {
            anexoService.deleteStorage(anexoFactory.buildList(deletados));
        }

        List<Anexo> merged = new ArrayList<>(anexoFactory.buildList(existentes));

        if (novos != null && !novos.isEmpty()) {
            novos = anexoService.uploadAnexosToStorage(novos);
            merged.addAll(anexoFactory.buildList(novos));
        }

        processo.mergeProcessoAnexoList(merged);
    }

    @Override
    public InputStreamResource loadToCsvExport(ProcessoFiltersRequest processoFiltersRequest) throws Exception {
        List<Processo> processoList = this.findWithFilters(processoFiltersRequest);

        if (processoList == null || processoList.size() == 0) {
            throw new ProcessoNoContentReportException();
        }

        List<ProcessoRelatorioResponse> processoRelatorioResponseList = new ArrayList<>();

        List<ItemTipoResponse> tiposItemList = itemTipoService.findAllItemTipo();
        LinkedHashMap<Long, String> hashTipoItem = new LinkedHashMap<>();

        for (ItemTipoResponse tipoItem : tiposItemList) {
            if (!hashTipoItem.containsKey(tipoItem.getCodigo())) {
                hashTipoItem.put(tipoItem.getCodigo(), tipoItem.getDescricao());
            }
        }

        PageRequest pageRequest = PageRequest.of(0, 999, Sort.unsorted());

        for (Processo processo : processoList) {
            ProcessoRelatorioResponse processoRelItem = new ProcessoRelatorioResponse();
            processoRelItem.setId(processo.getId());

            Beneficiario beneficiario = processo.getBeneficiario();
            if (beneficiario != null) {
                processoRelItem.setNomeBeneficiario(beneficiario.getNome());
                processoRelItem.setCodigoBeneficiario(beneficiario.getCodigo());
            }

            StatusEtapa statusEtapa = processo.getStatusEtapa();
            if (statusEtapa != null) {
                Optional<ContratoFluxoProcesso> contratoFluxoProcesso = null;
                if (!processo.getModoRascunho()) {
                    contratoFluxoProcesso =
                        contratoFluxoProcessoService.findContratoByFluxoProcessoIdAndContrato(
                                processo.getFluxoProcesso().getId(), processo.getContrato().getId()
                        );
                        if (contratoFluxoProcesso.isPresent()) {
                            atualizaSlaProcessoParaExportacao(processoRelItem, processo, contratoFluxoProcesso.get());
                            processoRelItem.setSlaStatusProcesso(buscaSlaStatusProcessoParaExportacao(processo, contratoFluxoProcesso.get()));
                            processoRelItem.setDataEstimadaConclusao(calculaDataPrevistaConclusao(processo, contratoFluxoProcesso.get()));
                        }
                }

                processoRelItem.setStatusProcesso(statusEtapa.getNome());

                ProcessoEtapa processoEtapa = statusEtapa.getProcessoEtapa();
                if (processoEtapa != null) {
                    processoRelItem.setEtapaProcesso(processoEtapa.getNome());

                    FluxoProcesso fluxoProcesso = processoEtapa.getFluxoProcesso();
                    if (fluxoProcesso != null) {
                        processoRelItem.setNomeFluxoProcesso(fluxoProcesso.getNomeApresentacao());

                        String porcentagemEtapas = null;
                        if (contratoFluxoProcesso != null && contratoFluxoProcesso.isPresent()) {
                            porcentagemEtapas = calculaPercentualEvolucaoEtapas(processo.getConclusaoProcesso(), contratoFluxoProcesso.get(), processoEtapa.getOrdem());
                        }
                        processoRelItem.setPorcentagemEtapas(porcentagemEtapas);
                    }
                }
            }

            processoRelItem.setInicioProcesso(processo.getInicioProcesso() != null
                    ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(processo.getInicioProcesso()) : null);
            processoRelItem.setNumeroPedidoGuia(processo.getNumeroPedidoGuia());

            processoRelItem.setNomeAdministrativoFesc(processo.getAdministrativoFesc() != null
                    ? processo.getAdministrativoFesc().getName() : null);

            Desempatador desempatador = processo.getDesempatador();
            if (desempatador != null) {
                processoRelItem.setNomeDesempatador(desempatador.getNome());
                processoRelItem.setEspecialidadesDesempatador("");

                List<DesempatadorEspecialidade> desempatadorEspecialidadeList = desempatador.getEspecialidadeList();
                if (desempatadorEspecialidadeList != null) {
                    for (DesempatadorEspecialidade desempatadorEspecialidade : desempatadorEspecialidadeList) {
                        if (processoRelItem.getEspecialidadesDesempatador().length() > 0) {
                            processoRelItem.setEspecialidadesDesempatador(processoRelItem.getEspecialidadesDesempatador() + " / ");
                        }

                        String conselhoEspecialidade = desempatadorEspecialidade.getEspecialidade().getDmTipoConselho() != null
                                ? desempatadorEspecialidade.getEspecialidade().getDmTipoConselho().getDescricao() : "";

                        processoRelItem.setEspecialidadesDesempatador(processoRelItem.getEspecialidadesDesempatador()
                                + desempatadorEspecialidade.getEspecialidade().getNome() + " (" + conselhoEspecialidade + ")");
                    }
                }
            }

            ProfissionalAssistente profissionalAssistente = processo.getProfissionalAssistente();
            if (profissionalAssistente != null) {
                processoRelItem.setNomeProfissionalAssistente(profissionalAssistente.getNome());

                List<ProfissionalAssistenteConselho> profissionalAssistenteConselhoList =
                        profissionalAssistente.getConselhoList();
                if (profissionalAssistenteConselhoList != null) {
                    for (ProfissionalAssistenteConselho profissionalAssistenteConselho : profissionalAssistenteConselhoList) {
                        if (profissionalAssistenteConselho.getDmTipoConselho().getValor().compareTo("CRM") == 0) {
                            processoRelItem.setCfmUfProfissionalAssistente(
                                    (profissionalAssistenteConselho.getNumeroConselho() != null
                                            ? profissionalAssistenteConselho.getNumeroConselho() + "/" : "")
                                            + (profissionalAssistenteConselho.getUfConselho() != null
                                            ? profissionalAssistenteConselho.getUfConselho() : "")
                            );
                        } else if (profissionalAssistenteConselho.getDmTipoConselho().getValor().compareTo("CRO") == 0) {
                            processoRelItem.setCroUfProfissionalAssistente(
                                    (profissionalAssistenteConselho.getNumeroConselho() != null
                                            ? profissionalAssistenteConselho.getNumeroConselho() + "/" : "")
                                            + (profissionalAssistenteConselho.getUfConselho() != null
                                            ? profissionalAssistenteConselho.getUfConselho() : "")
                            );
                        }
                    }
                }

                processoRelItem.setEspecialidadesProfissionalAssistente("");
                List<ProfissionalAssistenteEspecialidade> profissionalAssistenteEspecialidadeList =
                        profissionalAssistente.getEspecialidadeList();

                if (profissionalAssistenteEspecialidadeList != null) {
                    for (ProfissionalAssistenteEspecialidade profissionalAssistenteEspecialidade : profissionalAssistenteEspecialidadeList) {
                        if (processoRelItem.getEspecialidadesProfissionalAssistente() != null &&
                                processoRelItem.getEspecialidadesProfissionalAssistente().length() > 0) {
                            processoRelItem.setEspecialidadesProfissionalAssistente(
                                    processoRelItem.getEspecialidadesProfissionalAssistente() + " / ");
                        }

                        String conselhoEspecialidade = profissionalAssistenteEspecialidade.getEspecialidade().getDmTipoConselho() != null
                                ? profissionalAssistenteEspecialidade.getEspecialidade().getDmTipoConselho().getDescricao() : "";

                        processoRelItem.setEspecialidadesProfissionalAssistente(
                                processoRelItem.getEspecialidadesProfissionalAssistente() +
                                        profissionalAssistenteEspecialidade.getEspecialidade().getNome()
                                        + " (" + conselhoEspecialidade + ")");
                    }
                }
            }

            processoRelItem.setNomeAuditorQualidade(processo.getAuditorQualidade() != null
                    ? processo.getAuditorQualidade().getName() : null);
            processoRelItem.setNomeAdministrativoOperadora(processo.getAdministrativoOperadora() != null
                    ? processo.getAdministrativoOperadora().getName() : null);

            Auditor auditor = processo.getAuditor();
            if (auditor != null) {
                processoRelItem.setNomeAuditorOperadora(auditor.getNome());
                processoRelItem.setEspecialidadesAuditorOperadora("");

                List<AuditorEspecialidade> auditorEspecialidadeList = auditor.getEspecialidadeList();

                if (auditorEspecialidadeList != null) {
                    for (AuditorEspecialidade auditorEspecialidade : auditorEspecialidadeList) {
                        if (processoRelItem.getEspecialidadesAuditorOperadora() != null &&
                                processoRelItem.getEspecialidadesAuditorOperadora().length() > 0) {
                            processoRelItem.setEspecialidadesAuditorOperadora(
                                    processoRelItem.getEspecialidadesAuditorOperadora() + " / ");
                        }

                        String conselhoEspecialidade = auditorEspecialidade.getEspecialidade().getDmTipoConselho() != null
                                ? auditorEspecialidade.getEspecialidade().getDmTipoConselho().getDescricao() : "";

                        processoRelItem.setEspecialidadesAuditorOperadora(
                                processoRelItem.getEspecialidadesAuditorOperadora() +
                                        auditorEspecialidade.getEspecialidade().getNome() + " (" + conselhoEspecialidade + ")");
                    }
                }

                List<AuditorConselho> auditorConselhoList = auditor.getConselhoList();
                if (auditorConselhoList != null) {
                    for (AuditorConselho auditorConselho : auditorConselhoList) {
                        if (auditorConselho.getDmTipoConselho().getValor().compareTo("CRM") == 0) {
                            processoRelItem.setCfmUfAuditorOperadora(
                                    (auditorConselho.getNumeroConselho() != null
                                            ? auditorConselho.getNumeroConselho() + "/" : "")
                                            + (auditorConselho.getUfConselho() != null ? auditorConselho.getUfConselho() : "")
                            );
                        } else if (auditorConselho.getDmTipoConselho().getValor().compareTo("CRO") == 0) {
                            processoRelItem.setCroUfAuditorOperadora(
                                    (auditorConselho.getNumeroConselho() != null
                                            ? auditorConselho.getNumeroConselho() + "/" : "")
                                            + (auditorConselho.getUfConselho() != null ? auditorConselho.getUfConselho() : "")
                            );
                        }
                    }
                }
            }

            Operadora operadoraSolicitante = processo.getOperadoraSolicitante();
            if (operadoraSolicitante != null) {
                processoRelItem.setNomeOperadoraSolicitante(operadoraSolicitante.getNomeFantasia());
            }

            processoRelItem.setDocumentacaoCompleta(processo.getDocumentacaoCompleta() != null
                    ? processo.getDocumentacaoCompleta() ? "Sim" : "Não" : "Não Informado");
            processoRelItem.setDataCriacao(processo.getDataCriacao() != null
                    ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(processo.getDataCriacao()) : null);
            processoRelItem.setDataConclusao(processo.getConclusaoProcesso() != null
                    ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(processo.getConclusaoProcesso()) : null);
            processoRelItem.setDataParecer(processo.getDataParecer() != null
                    ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(processo.getDataParecer()) : null);
            processoRelItem.setPrazoInicialAns(processo.getPrazoInicialAns() != null
                    ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(processo.getPrazoInicialAns()) : null);
            processoRelItem.setPrazoFinalAns(processo.getPrazoFinalAns() != null
                    ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(processo.getPrazoFinalAns()) : null);
            processoRelItem.setDataReprovacao(processo.getDataReprovacaoOperadora() != null
                    ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(processo.getDataReprovacaoOperadora()) : null);
            processoRelItem.setMotivoReprovacao(processo.getDmReprovarMotivado() != null
                    ? processo.getDmReprovarMotivado().getDescricao() : null);
            processoRelItem.setDataCancelamento(processo.getDataCancelamento() != null
                    ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(processo.getDataCancelamento()) : null);
            processoRelItem.setMotivoCancelamento(processo.getDmCancelamentoMotivado() != null
                    ? processo.getDmCancelamentoMotivado().getDescricao() : null);
            processoRelItem.setPontosDivergencia(
                    buscaPontosDivergenciaPorProcesso(processo.getProcessoItemList(), hashTipoItem));
            processoRelItem.setResumoParecerDesempatador(processo.getResumoParecerDesempatador() != null
                    ? processo.getResumoParecerDesempatador() : null);
            processoRelItem.setValorEstimadoEconomia(
                    calcularValorEstimadoEconomia(processo.getProcessoItemList()));
            processoRelItem.setNumeroProtocolo(processo.getNumeroProtocolo() != null
                    ? processo.getNumeroProtocolo() : null);
            processoRelItem.setCobrancaProcesso(processo.getDmCobrarProcesso() != null
                    ? processo.getDmCobrarProcesso().getDescricao() : null);
            processoRelItem.setOperadoraOrigem(processo.getOperadoraOrigem() != null
                    ? processo.getOperadoraOrigem().getNomeFantasia() : null);
            processoRelItem.setDataEnvioProcessoDesempatador(processo.getDataEnvioDesempatador() != null
                    ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(processo.getDataEnvioDesempatador()) : null);

            if (processo.getInicioProcesso() != null && processo.getDataCriacao() != null) {
                long periodo = processo.getDataCriacao().until(processo.getInicioProcesso(), ChronoUnit.DAYS);
                processoRelItem.setDiasEntreCriacaoInicio(periodo);
            }

            processoRelItem.setAreasAtuacao("");
            if (processo.getAreaAtuacaoList() != null && !processo.getAreaAtuacaoList().isEmpty()) {
                for (ProcessoAreaAtuacao processoAreaAtuacao : processo.getAreaAtuacaoList()) {
                    if (processoRelItem.getAreasAtuacao() != null
                            && processoRelItem.getAreasAtuacao().length() > 0) {
                        processoRelItem.setAreasAtuacao(processoRelItem.getAreasAtuacao() + " / ");
                    }

                    if (processoAreaAtuacao.getAreaAtuacao() != null) {
                        processoRelItem.setAreasAtuacao(
                                processoRelItem.getAreasAtuacao() + processoAreaAtuacao.getAreaAtuacao().getNome());
                    }
                }
            }

            processoRelItem.setCartas(criaListagemCartasNotificacao(processo));
            processoRelItem.setTelegramas(criaListagemTelegramas(processo));
            processoRelatorioResponseList.add(processoRelItem);
        }

        if (processoFiltersRequest.getDmContagemSla() != null
                && !processoFiltersRequest.getDmContagemSla().equals("")) {
            processoRelatorioResponseList = filtraProcessosPorSlaParaExportacao(processoRelatorioResponseList,
                    processoFiltersRequest.getDmContagemSla());
        }

        ParametroResponse parametro = parametroService.findByNome("indicacao_tempo_diario_hrs_trabalho");
        processoRelatorioResponseList.forEach(p -> p.setSlaProcesso(CalculadorSlaUtil.toDias(p.getSlaProcesso(), parametro, true)));

        return new InputStreamResource(CsvUtils.csvExport(ProcessoRelatorioResponse.class, processoRelatorioResponseList));
    }

    @Override
    public ProcessoChatResponse findByIdChat(Long processoId) {
        Long userOperadoraId = SystemUtil.getCurrentUsuarioLogado().getOperadora() != null
                ? SystemUtil.getCurrentUsuarioLogado().getOperadora().getId()
                : null;
        Processo processo = processoRepository.findByUserOperadoraPermission(processoId, userOperadoraId);

        if (processo == null) {
            throw new ProcessoOperadoraNotAllowedException(userOperadoraId);
        }

        return processoFactory.buildResponseChat(processo);
    }

    @Transactional
    @Override
    public Processo saveProcessoAnexo(Long processoId, Anexo anexo) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException(processoId));
        List<Anexo> anexos = new ArrayList<>(processo.getAnexoList());
        anexos.add(anexo);
        processo.mergeProcessoAnexoList(anexos);

        processo = processoRepository.save(processo);

        return processo;
    }

    @Override
    public ProcessoResponse findByUserOperadoraPermission(Long processoId) {
        Long userOperadoraId = SystemUtil.getCurrentUsuarioLogado().getOperadora() != null
                ? SystemUtil.getCurrentUsuarioLogado().getOperadora().getId()
                : null;

        Processo processo = processoRepository.findByUserOperadoraPermission(processoId, userOperadoraId);

        if (processo == null) {
            throw new ProcessoOperadoraNotAllowedException(userOperadoraId);
        }

        ProcessoResponse processoResponse = processoFactory.buildResponse(processo);

        this.atualizaSlaProcesso(processoResponse);
        this.atualizaSlaStatusProcesso(processoResponse);

        ParametroResponse parametro = parametroService.findByNome("indicacao_tempo_diario_hrs_trabalho");
        processoResponse.setPrazoSlaProcessoEmDias(CalculadorSlaUtil.toDias(processoResponse.getPrazoSlaProcessoFormatado(), parametro, true));

        return processoResponse;
    }

    private String calcularValorEstimadoEconomia(List<ProcessoItem> processoItemList) {
        BigDecimal result = BigDecimal.ZERO;
        if (processoItemList == null || processoItemList.isEmpty()) {
            result = BigDecimal.ZERO;
        }

        for (ProcessoItem item : processoItemList) {
            if (itemPossuiParecerDesfavoravelDesempatador(item)) {
                if (item.getQuantidadeAjustadaDesempatador() != null
                        && (item.getQuantidade() != null
                                && item.getQuantidade().compareTo(item.getQuantidadeAjustadaDesempatador()) > 0)) {
                    BigDecimal diferenca = item.getQuantidade().subtract(item.getQuantidadeAjustadaDesempatador());
                    BigDecimal valorUnitario = item.getValorUnitario() != null ? item.getValorUnitario() : BigDecimal.ZERO;
                    result = result.add(diferenca.multiply(valorUnitario));
                }
            }
        }

        DecimalFormatSymbols decimalFormatSymbols  = DecimalFormatSymbols.getInstance(new Locale("pt", "BR"));
        String totalFormatado = "R$ " + new DecimalFormat("#,##0.00", decimalFormatSymbols).format(result);

        return totalFormatado;
    }

    private String buscaPontosDivergenciaPorProcesso(List<ProcessoItem> processoItemList,
                                                     LinkedHashMap<Long, String> hashItemTipo) {
        if (processoItemList == null || processoItemList.isEmpty()) {
            return null;
        }

        Set<String> descricoesItens = new HashSet<>();

        for (ProcessoItem item : processoItemList) {
            if (itemPossuiParecerDesfavoravelOperadora(item)) {
                String tipoItem = hashItemTipo.get(item.getItemTipoId());
                if (tipoItem != null && !tipoItem.equals("")) {
                    descricoesItens.add(tipoItem);
                }
            }
        }

        return String.join(" + ", descricoesItens);
    }

    private boolean itemPossuiParecerDesfavoravelOperadora(ProcessoItem item) {
        return item.getDmParecerOperadora() != null
                && item.getDmParecerOperadora().getValor().equals("D");
    }

    private boolean itemPossuiParecerDesfavoravelDesempatador(ProcessoItem item) {
        return item.getDmParecerDesempatador() != null
                && item.getDmParecerDesempatador().getValor().equals("D");
    }

    private String calculaPercentualEvolucaoEtapas(LocalDateTime dataConclusaoProcesso,
                                                   ContratoFluxoProcesso contratoFluxoProcesso,
                                                   Long ordemProcessoEtapa) {
        if (contratoFluxoProcesso == null
                || contratoFluxoProcesso.getFluxoProcesso() == null
                || contratoFluxoProcesso.getFluxoProcesso().getProcessoEtapaList() == null) {
            return null;
        }

        Integer quantidadeEtapas = contratoFluxoProcesso.getFluxoProcesso().getProcessoEtapaList().size();

        BigDecimal quantidadeEtapasFluxo = new BigDecimal(quantidadeEtapas);

        BigDecimal ordemEtapa = dataConclusaoProcesso != null
                ? new BigDecimal(ordemProcessoEtapa)
                : new BigDecimal(ordemProcessoEtapa - 1);

        BigDecimal percentual = ordemEtapa.divide(quantidadeEtapasFluxo, 2, RoundingMode.HALF_UP);
        percentual = percentual.multiply(new BigDecimal("100"));
        return percentual != null ? String.format("%,.2f", percentual) + "%" : "0%";
    }

    private void adicionaTempoRestanteSla(List<ProcessoGridResponse> resultList) {
        if (resultList != null && !resultList.isEmpty()) {
            Map<Long, ContratoFluxoProcesso> fluxos = new HashMap<>();

            for (ProcessoGridResponse processo : resultList) {
                if (!processo.getModoRascunho()) {
                    if (!fluxos.containsKey(processo.getFluxoProcessoId())) {
                        Optional<ContratoFluxoProcesso> contratoFluxoProcesso =
                                contratoFluxoProcessoService.findContratoByFluxoProcessoIdAndContrato(
                                        processo.getFluxoProcessoId(), processo.getContratoId()
                                );
                        if (contratoFluxoProcesso.isPresent()) {
                            fluxos.put(processo.getFluxoProcessoId(), contratoFluxoProcesso.get());
                        }
                    }
                }
            }

            for (ProcessoGridResponse processo : resultList) {
                if (!processo.getModoRascunho()) {
                    calculaSlaDoProcesso(processo, fluxos.get(processo.getFluxoProcessoId()));
                    calculaSlaDoStatusDoProcesso(processo, fluxos.get(processo.getFluxoProcessoId()));
                    processo.setPercentualEvolucaoEtapas(
                            calculaPercentualEvolucaoEtapas(processo.getConclusaoProcesso(),
                                    fluxos.get(processo.getFluxoProcessoId()),
                                    processo.getOrdemProcessoEtapa()));
                }
            }
        }
    }

    private void calculaSlaDoProcesso(ProcessoGridResponse processo, ContratoFluxoProcesso contratoFluxoProcesso) {
        if (contratoFluxoProcesso != null && contratoFluxoProcesso.getPreveSla()) {
            Long tempoUtilizadoStatusAtual = null;
            Long tempoUtilizadoSla = processoHistoricoService.findTotalTempoUtilizadoSla(processo.getId());

            String contagemSla = processo.getDominioContagemSlaStatus() != null
                    ? processo.getDominioContagemSlaStatus() : null;

            List<CalendarioDiaHora> horarios = contratoFluxoProcesso.getCalendario().getCalendarioDiaHoraList();
            List<CalendarioFeriado> feriados = contratoFluxoProcesso.getCalendario().getCalendarioFeriadoList();

            LocalDateTime dataFim = processo.getConclusaoProcesso() != null
                    ? processo.getConclusaoProcesso()
                    : processo.getDataCancelamento() != null ? processo.getDataCancelamento() : LocalDateTime.now();

            if (contagemSla != null && contagemSla.equalsIgnoreCase("I")) {
                Optional<ProcessoHistorico> lastProcessoHistorico =
                        processoHistoricoService
                                .findByProcessoIdAndStatusEtapaId(processo.getId(), processo.getStatusEtapaId());

                LocalDateTime dataInicial = lastProcessoHistorico.isPresent()
                        ? lastProcessoHistorico.get().getDataInicial() : processo.getDataInicioProcesso();

                tempoUtilizadoStatusAtual = calculaPrazoSla(contratoFluxoProcesso.getPreveSla() != null
                                ? contratoFluxoProcesso.getPreveSla()
                                : false,
                        contratoFluxoProcesso.getCalendario().getId(),
                        dataInicial, dataFim, horarios, feriados,
                        processo.getCalendarioStatusEtapaId());
            }

            if (tempoUtilizadoSla != null) {
                tempoUtilizadoSla = tempoUtilizadoSla + (tempoUtilizadoStatusAtual != null ? tempoUtilizadoStatusAtual : 0);

                CalculadorSlaUtil calculadorSla = new CalculadorSlaUtil();
                long prazoSlaStatusEmSegundos = contratoFluxoProcesso.getTempoEmHoras() != null ? contratoFluxoProcesso.getTempoEmHoras() * 3600 : 0L;
                Long tempoRestanteConformeSla = (prazoSlaStatusEmSegundos - tempoUtilizadoSla);

                processo.setSlaProcesso(String.valueOf(tempoRestanteConformeSla));
                processo.setSlaProcessoFormatado(calculadorSla.buscaTempoRestanteConformeSla(prazoSlaStatusEmSegundos,
                        tempoUtilizadoSla));

                boolean processoConcluido = processo.getConclusaoProcesso() != null;
                processo.setSlaProcessoEmAlerta(processoConcluido ? false : existeAlertaParaTempoRestanteSla(
                        "sla_processo_percentual_alerta", prazoSlaStatusEmSegundos, tempoRestanteConformeSla)
                );

                if (processo.getDataEstimadaConclusao() == null) {
                    LocalDateTime dataInicio = processo.getDataInicioProcesso() != null ?
                            processo.getDataInicioProcesso() : processo.getDataCriacao();
                    processo.setDataEstimadaConclusao(calculadorSla.calculaDataPrevista(dataInicio,
                            contratoFluxoProcesso.getTempoEmHoras(), horarios, feriados));
                }
            }
        }
    }

    private void calculaSlaDoStatusDoProcesso(ProcessoGridResponse processo,
                                              ContratoFluxoProcesso contratoFluxoProcesso) {
        if (contratoFluxoProcesso != null && contratoFluxoProcesso.getPreveSla()) {
            if (processo.getStatusEtapaId() != null) {
                Optional<ProcessoHistorico> historico =
                        processoHistoricoService.findByProcessoIdAndStatusEtapaId(processo.getId(),
                                processo.getStatusEtapaId());

                if (historico.isPresent()) {
                    StatusEtapa statusEtapa = historico.get().getStatusEtapa();

                    if (!statusEtapa.getPreveSla()) {
                        return;
                    }

                    if (statusEtapa.getPreveSla() && statusEtapa.getTempoHorasSla() == null) {
                        return;
                    }

                    List<CalendarioDiaHora> horarios = statusEtapa.getCalendario().getCalendarioDiaHoraList();
                    List<CalendarioFeriado> feriados = statusEtapa.getCalendario().getCalendarioFeriadoList();

                    LocalDateTime dataFim = historico.get().getDataFinal();

                    if (dataFim == null) {
                        dataFim = processo.getConclusaoProcesso() != null
                                ? processo.getConclusaoProcesso()
                                : processo.getDataCancelamento() != null
                                ? processo.getDataCancelamento() : LocalDateTime.now();
                    }

                    Long statusEtapaCalendarioId = statusEtapa.getCalendario() != null ?
                            statusEtapa.getCalendario().getId() : null;

                    Long prazoUtilizado = calculaPrazoSla(statusEtapa.getPreveSla(), statusEtapaCalendarioId,
                            historico.get().getDataInicial(), dataFim, horarios, feriados, statusEtapaCalendarioId);

                    if (prazoUtilizado != null) {
                        long prazoSlaStatusEmSegundos = statusEtapa.getTempoHorasSla() * 3600;
                        Long tempoRestanteConformeSla = (prazoSlaStatusEmSegundos - prazoUtilizado);

                        CalculadorSlaUtil calculadorSla = new CalculadorSlaUtil();
                        processo.setSlaStatusProcesso(String.valueOf(tempoRestanteConformeSla));
                        processo.setSlaStatusProcessoFormatado(calculadorSla.buscaTempoRestanteConformeSla(
                                prazoSlaStatusEmSegundos, prazoUtilizado));

                        boolean processoConcluido = processo.getConclusaoProcesso() != null;
                        processo.setSlaStatusProcessoEmAlerta(processoConcluido ? false :
                                existeAlertaParaTempoRestanteSla(
                                        "sla_status_percentual_alerta", prazoSlaStatusEmSegundos, tempoRestanteConformeSla)
                        );
                    }
                }
            }
        }
    }

    private Long calculaPrazoSla(boolean statusPreveSla, Long calendarioId, LocalDateTime dataInicio,
                                 LocalDateTime dataFinal, List<CalendarioDiaHora> horariosFuncionamento,
                                 List<CalendarioFeriado> calendarioFeriados, Long statusEtapaCalendarioId) {
        if (!statusPreveSla) {
            return null;
        }

        List<CalendarioDiaHora> horariosExpediente;
        List<CalendarioFeriado> feriados;

        boolean horariosFuncionamentoExists = horariosFuncionamento != null && !horariosFuncionamento.isEmpty();
        boolean feriadosExists = calendarioFeriados != null && !calendarioFeriados.isEmpty();

        if ((!horariosFuncionamentoExists && !feriadosExists) || !calendarioId.equals(statusEtapaCalendarioId)) {
            horariosExpediente = calendarioDiaHoraService.findByCalendarioIds(new HashSet<>(Arrays.asList(calendarioId)));
            feriados = calendarioFeriadoService.findByCalendarioIds(new HashSet<>(Arrays.asList(calendarioId)));
        } else {
            horariosExpediente = horariosFuncionamento
                    .stream().filter(item -> item.getCalendario().getId().equals(calendarioId))
                    .collect(Collectors.toList());
            feriados = calendarioFeriados
                    .stream().filter(item -> item.getCalendario().getId().equals(calendarioId))
                    .collect(Collectors.toList());
        }

        CalculadorSlaUtil calculadorSla = new CalculadorSlaUtil();
        LocalDateTime dataFim = dataFinal != null ? dataFinal : LocalDateTime.now();
        return calculadorSla.calculaTempoGastoSlaEmSegundos(dataInicio, dataFim, horariosExpediente, feriados);
    }

    private boolean existeAlertaParaTempoRestanteSla(String nomeParametro, long prazoSlaSegundos,
                                                     long prazoRestanteSegundos) {
        ParametroResponse percentualAlertaSlaStatus = parametroService.findByNome(nomeParametro);
        Integer valorAlertaSla = percentualAlertaSlaStatus != null ? percentualAlertaSlaStatus.getValorNumber() : null;
        if (valorAlertaSla != null) {
            if (prazoRestanteSegundos < 0) {
                return false;
            }
            CalculadorSlaUtil calculadorSla = new CalculadorSlaUtil();
            return calculadorSla.checkSlaEmAlerta(prazoSlaSegundos, prazoRestanteSegundos, valorAlertaSla);
        }
        return false;
    }

    public void atualizaSlaProcesso(ProcessoResponse processoResponse) {
        if (!processoResponse.getModoRascunho()) {
            ContratoFluxoProcesso contrato =
                    contratoFluxoProcessoService.findContratoByFluxoProcessoIdAndContrato(
                                    processoResponse.getFluxoProcesso().getId(), processoResponse.getContrato().getId())
                            .orElseThrow(ContratoFluxoProcessoNotFoundException::new);

            if (contrato.getPreveSla()) {
                Long tempoUtilizadoStatusAtual = null;
                Long tempoUtilizadoSla = processoHistoricoService.findTotalTempoUtilizadoSla(processoResponse.getId());

                String contagemSla = processoResponse.getStatusEtapa().getDmContagemSla() != null
                        ? processoResponse.getStatusEtapa().getDmContagemSla().getValor() : null;

                List<CalendarioDiaHora> horarios = contrato.getCalendario().getCalendarioDiaHoraList();
                List<CalendarioFeriado> feriados = contrato.getCalendario().getCalendarioFeriadoList();

                LocalDateTime dataFim = processoResponse.getConclusaoProcesso() != null
                        ? processoResponse.getConclusaoProcesso()
                        : processoResponse.getDataCancelamento() != null
                        ? processoResponse.getDataCancelamento() : LocalDateTime.now();

                if (contagemSla != null && contagemSla.equalsIgnoreCase("I")) {
                    ProcessoHistorico lastProcessoHistorico =
                            processoHistoricoService.findByProcessoIdAndStatusEtapaId(processoResponse.getId(),
                                            processoResponse.getStatusEtapa().getId())
                                    .orElse(null);

                    Long statusEtapaCalendarioId = processoResponse.getStatusEtapa().getCalendario() != null ?
                            processoResponse.getStatusEtapa().getCalendario().getId() : null;

                    tempoUtilizadoStatusAtual = calculaPrazoSla(contrato.getPreveSla() != null
                                    ? contrato.getPreveSla() : false, contrato.getCalendario().getId(),
                            lastProcessoHistorico == null
                                    ? processoResponse.getInicioProcesso()
                                    : lastProcessoHistorico.getDataInicial(), dataFim,
                            horarios, feriados, statusEtapaCalendarioId);
                }

                if (tempoUtilizadoSla != null) {
                    tempoUtilizadoSla = tempoUtilizadoSla + (tempoUtilizadoStatusAtual != null ? tempoUtilizadoStatusAtual : 0);

                    CalculadorSlaUtil calculadorSla = new CalculadorSlaUtil();
                    long prazoSlaStatusEmSegundos = contrato.getTempoEmHoras() * 3600;
                    Long tempoRestanteConformeSla = (prazoSlaStatusEmSegundos - tempoUtilizadoSla);

                    processoResponse.setPrazoSlaProcesso(String.valueOf(tempoRestanteConformeSla));
                    processoResponse.setPrazoSlaProcessoFormatado(calculadorSla.buscaTempoRestanteConformeSla(
                            prazoSlaStatusEmSegundos, tempoUtilizadoSla));

                    boolean processoConcluido = processoResponse.getConclusaoProcesso() != null;
                    processoResponse.setPrazoSlaProcessoEmAlerta(processoConcluido ? false :
                            existeAlertaParaTempoRestanteSla("sla_processo_percentual_alerta",
                                    prazoSlaStatusEmSegundos, tempoRestanteConformeSla)
                    );
                }
            }
        }
    }

    public void atualizaSlaStatusProcesso(ProcessoResponse processoResponse) {
        if (!processoResponse.getModoRascunho()) {
            ContratoFluxoProcesso contrato =
                    contratoFluxoProcessoService.findContratoByFluxoProcessoIdAndContrato(
                                    processoResponse.getFluxoProcesso().getId(), processoResponse.getContrato().getId())
                            .orElseThrow(ContratoFluxoProcessoNotFoundException::new);

            if (contrato.getPreveSla()) {
                ProcessoHistorico historico = processoHistoricoService.findByProcessoIdAndStatusEtapaId(
                                processoResponse.getId(), processoResponse.getStatusEtapa().getId())
                        .orElse(null);

                Boolean statusEtapaPreveSLA = historico != null
                        ? historico.getStatusEtapa().getPreveSla() != null
                            ? historico.getStatusEtapa().getPreveSla()
                            : false
                        : processoResponse.getStatusEtapa().getPreveSla() != null
                            ? processoResponse.getStatusEtapa().getPreveSla()
                            : false;

                if (statusEtapaPreveSLA) {
                    Integer statusEtapaTempoHorasSLA = historico != null
                            ? historico.getStatusEtapa().getTempoHorasSla() != null
                                ? historico.getStatusEtapa().getTempoHorasSla()
                                : 0
                            : processoResponse.getStatusEtapa().getTempoHorasSla() != null
                                ? processoResponse.getStatusEtapa().getTempoHorasSla()
                                : 0;

                    LocalDateTime dataInicio = historico != null
                            ? historico.getDataInicial()
                            : processoResponse.getInicioProcesso();

                    LocalDateTime dataFim = historico != null
                            ? historico.getDataFinal()
                            : processoResponse.getInicioProcesso();

                    if (dataFim == null) {
                        dataFim = processoResponse.getConclusaoProcesso() != null ?
                                processoResponse.getConclusaoProcesso()
                                : processoResponse.getDataCancelamento() != null
                                ? processoResponse.getDataCancelamento() : LocalDateTime.now();
                    }

                    Long statusEtapaCalendarioId = historico != null
                            ? historico.getStatusEtapa().getCalendario() != null
                                ? historico.getStatusEtapa().getCalendario().getId()
                                : null
                            : processoResponse.getStatusEtapa().getCalendario() != null
                                ? processoResponse.getStatusEtapa().getCalendario().getId()
                                : null;

                    Long tempoUtilizadoSla = calculaPrazoSla(statusEtapaPreveSLA, statusEtapaCalendarioId,
                            dataInicio, dataFim, null, null, statusEtapaCalendarioId);

                    if (tempoUtilizadoSla != null) {
                        CalculadorSlaUtil calculadorSla = new CalculadorSlaUtil();
                        long prazoSlaStatusEmSegundos = statusEtapaTempoHorasSLA * 3600;
                        Long tempoRestanteConformeSla = (prazoSlaStatusEmSegundos - tempoUtilizadoSla);

                        processoResponse.setPrazoSlaStatusProcesso(String.valueOf(tempoRestanteConformeSla));
                        processoResponse.setPrazoSlaStatusProcessoFormatado(calculadorSla.buscaTempoRestanteConformeSla(
                                prazoSlaStatusEmSegundos, tempoUtilizadoSla));
                        processoResponse.setPrazoSlaStatusProcessoEmAlerta(existeAlertaParaTempoRestanteSla(
                                "sla_status_percentual_alerta", prazoSlaStatusEmSegundos, tempoRestanteConformeSla));
                    }
                }
            }
        }
    }

    private void atualizaSlaProcessoParaExportacao(ProcessoRelatorioResponse processoRelatorioResponse,
                                                   Processo processo, ContratoFluxoProcesso contratoFluxoProcesso) {
        if (contratoFluxoProcesso.getPreveSla()) {
            Long tempoUtilizadoStatusAtual = null;
            Long tempoUtilizadoSla = processoHistoricoService.findTotalTempoUtilizadoSla(processo.getId());

            StatusEtapa statusEtapa = processo.getStatusEtapa();

            LocalDateTime dataFim = processo.getConclusaoProcesso() != null
                    ? processo.getConclusaoProcesso()
                    : processo.getDataCancelamento() != null
                        ? processo.getDataCancelamento()
                        : LocalDateTime.now();

            String contagemSla = statusEtapa.getDmContagemSla() != null ? statusEtapa.getDmContagemSla().getValor() : null;

            List<CalendarioDiaHora> horarios = contratoFluxoProcesso.getCalendario().getCalendarioDiaHoraList();
            List<CalendarioFeriado> feriados = contratoFluxoProcesso.getCalendario().getCalendarioFeriadoList();

            if (contagemSla != null && contagemSla.equalsIgnoreCase("I")) {
                Optional<ProcessoHistorico> lastProcessoHistorico =
                        processoHistoricoService.findByProcessoIdAndStatusEtapaId(processo.getId(), statusEtapa.getId());

                LocalDateTime dataInicio = lastProcessoHistorico.isPresent()
                        ? lastProcessoHistorico.get().getDataInicial() : processo.getInicioProcesso();

                Long contratoFluxoProcessoCalendarioId = contratoFluxoProcesso.getCalendario() != null ?
                        contratoFluxoProcesso.getCalendario().getId() : null;

                Long statusEtapaCalendarioId = statusEtapa.getCalendario() != null ?
                        statusEtapa.getCalendario().getId() : null;

                tempoUtilizadoStatusAtual = calculaPrazoSla(contratoFluxoProcesso.getPreveSla() != null
                                ? contratoFluxoProcesso.getPreveSla() : false,
                        contratoFluxoProcessoCalendarioId, dataInicio,
                        dataFim, horarios, feriados, statusEtapaCalendarioId);
            }

            if (tempoUtilizadoSla != null) {
                tempoUtilizadoSla = tempoUtilizadoSla + (tempoUtilizadoStatusAtual != null ? tempoUtilizadoStatusAtual : 0);
                CalculadorSlaUtil calculadorSla = new CalculadorSlaUtil();
                long prazoSlaStatusEmSegundos = contratoFluxoProcesso.getTempoEmHoras() * 3600;
                Long tempoRestanteConformeSla = (prazoSlaStatusEmSegundos - tempoUtilizadoSla);
                processoRelatorioResponse.setSlaProcessoTempo(String.valueOf(tempoRestanteConformeSla));
                processoRelatorioResponse.setSlaProcesso(calculadorSla.buscaTempoRestanteConformeSla(prazoSlaStatusEmSegundos,
                        tempoUtilizadoSla));
                boolean processoConcluido = processo.getConclusaoProcesso() != null;
                processoRelatorioResponse.setSlaProcessoEmAlerta(processoConcluido ? false : existeAlertaParaTempoRestanteSla(
                        "sla_processo_percentual_alerta", prazoSlaStatusEmSegundos, tempoRestanteConformeSla));
            }
        }
    }

    private String buscaSlaStatusProcessoParaExportacao(Processo processo, ContratoFluxoProcesso contratoFluxoProcesso) {
        String prazoRestante = null;

        if (contratoFluxoProcesso.getPreveSla()) {
            Optional<ProcessoHistorico> historico = processoHistoricoService.findByProcessoIdAndStatusEtapaId(
                    processo.getId(), processo.getStatusEtapa().getId());

            if (historico.isPresent()) {
                StatusEtapa statusEtapa = historico.get().getStatusEtapa();

                if (!statusEtapa.getPreveSla()) {
                    return null;
                }

                if (statusEtapa.getPreveSla() && statusEtapa.getTempoHorasSla() == null) {
                    return null;
                }

                List<CalendarioDiaHora> horarios = statusEtapa.getCalendario() != null ?
                        statusEtapa.getCalendario().getCalendarioDiaHoraList() : null;
                List<CalendarioFeriado> feriados = statusEtapa.getCalendario() != null ?
                        statusEtapa.getCalendario().getCalendarioFeriadoList() : null;

                LocalDateTime dataFim = historico.get().getDataFinal();

                if (dataFim == null) {
                    dataFim = processo.getConclusaoProcesso() != null
                            ? processo.getConclusaoProcesso()
                            : processo.getDataCancelamento() != null
                            ? processo.getDataCancelamento() : LocalDateTime.now();
                }

                Long statusEtapaCalendarioId =  statusEtapa.getCalendario() != null
                        ? statusEtapa.getCalendario().getId() : null;

                Long tempoUtilizadoSla = calculaPrazoSla(statusEtapa.getPreveSla(), statusEtapaCalendarioId,
                        historico.get().getDataInicial(), dataFim, horarios, feriados, statusEtapaCalendarioId);

                if (tempoUtilizadoSla != null) {
                    CalculadorSlaUtil calculadorSla = new CalculadorSlaUtil();
                    long prazoSlaStatusEmSegundos = statusEtapa.getTempoHorasSla() * 3600;
                    prazoRestante = calculadorSla.buscaTempoRestanteConformeSla(prazoSlaStatusEmSegundos, tempoUtilizadoSla);
                }
            }
        }
        return prazoRestante;
    }

    private String calculaDataPrevistaConclusao(Processo processo, ContratoFluxoProcesso contratoFluxoProcesso) {
        if (contratoFluxoProcesso != null && contratoFluxoProcesso.getPreveSla()) {
            List<CalendarioDiaHora> horarios = contratoFluxoProcesso.getCalendario().getCalendarioDiaHoraList();
            List<CalendarioFeriado> feriados = contratoFluxoProcesso.getCalendario().getCalendarioFeriadoList();

            if (contratoFluxoProcesso.getTempoEmHoras() > 0) {
                LocalDateTime dataInicio = processo.getInicioProcesso() != null ?
                        processo.getInicioProcesso() : processo.getDataCriacao();

                CalculadorSlaUtil calculadorSlaUtil = new CalculadorSlaUtil();
                LocalDateTime dataPrevistaConclusao = calculadorSlaUtil.calculaDataPrevista(dataInicio,
                        contratoFluxoProcesso.getTempoEmHoras(), horarios, feriados);
                return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dataPrevistaConclusao);
            }
        }

        return null;
    }
    private Long buscaProximoAdministrativoFescExtId(Processo processo) {
        if (processo.getOperadoraSolicitante() != null) {
            if (processo.getOperadoraSolicitante().getEquipeAdministrativoFesc() != null) {
                Long equipeAdministrativoFescId = processo.getOperadoraSolicitante().getEquipeAdministrativoFesc().getId();

                LocalDate dataProcesso = processo.getInicioProcesso() != null
                        ? processo.getInicioProcesso().toLocalDate() : LocalDate.now();

                List<EquipeMembro> membrosEquipe = equipeMembroService.findMembrosSemInatividadeByEquipeTrabalhoId(
                        equipeAdministrativoFescId, dataProcesso);

                Long ultimoFuncionarioEscolhidoFesc =
                        processoRepository.findUltimoAdministrativoFescExtIdByOperadoraSolicitanteId(
                            processo.getOperadoraSolicitante().getId());

                List<Long> idsMembrosEquipe = membrosEquipe
                        .stream().map(EquipeMembro::getUsuarioExtId).collect(Collectors.toList());

                return retornaProximoIdNaLista(ultimoFuncionarioEscolhidoFesc, idsMembrosEquipe);
            }
        }
        return null;
    }

    private Long buscaProximoAuditorQualidadeExtId(Processo processo) {
        if (processo.getOperadoraSolicitante() != null) {
            if (processo.getOperadoraSolicitante().getEquipeAuditorQualidade() != null) {
                Long equipeAuditorQualidadeId = processo.getOperadoraSolicitante().getEquipeAuditorQualidade().getId();

                LocalDate dataProcesso = processo.getInicioProcesso() != null
                        ? processo.getInicioProcesso().toLocalDate() : LocalDate.now();

                List<EquipeMembro> membrosEquipe = equipeMembroService.findMembrosSemInatividadeByEquipeTrabalhoId(
                        equipeAuditorQualidadeId, dataProcesso);

                Long ultimoFuncionarioAuditorQualidade = processoRepository.findUltimoAuditorQualidadeIdByOperadoraSolicitanteId(
                        processo.getOperadoraSolicitante().getId());

                List<Long> idsMembrosEquipe = membrosEquipe
                        .stream().map(EquipeMembro::getUsuarioExtId).collect(Collectors.toList());

                return retornaProximoIdNaLista(ultimoFuncionarioAuditorQualidade, idsMembrosEquipe);
            }
        }
        return null;
    }

    private Long retornaProximoIdNaLista(Long ultimoEscolhido, List<Long> itens) {
        Long itemEscolhido = null;
        if (itens == null || itens.isEmpty()) {
            return null;
        }

        if (ultimoEscolhido == null || itens.size() == 1) {
            itemEscolhido = itens.get(0);
        }

        ListIterator<Long> it = itens.listIterator();

        while (it.hasNext()) {
            Long item = it.next();

            if (item.equals(ultimoEscolhido)) {
                itemEscolhido = it.hasNext() ? it.next() : itens.get(0);
            }
        }

        if (itemEscolhido == null && !itens.isEmpty()) {
            itemEscolhido = itens.get(0);
        }

        return itemEscolhido;
    }

    private void verificaPerfilLogadoPermiteAcessar(Long statusEtapaId, String acao) {
        if (SystemUtil.getCurrentUsuarioLogado() == null
                || SystemUtil.getCurrentUsuarioLogado().getDefaultUsuarioPerfil() == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Perfil logado não possui acesso");
        }

        Boolean permiteRealizarAcao = statusEtapaAcaoPerfilService.perfilLogadoPodeRealizarAcao(statusEtapaId, acao);

        if (!permiteRealizarAcao) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Perfil logado não possui acesso");
        }
    }

    private void verificaPerfilLogadoPermiteAlterarStatus(Long statusEtapaId, Long acaoId) {
        if (SystemUtil.getCurrentUsuarioLogado() == null
                || SystemUtil.getCurrentUsuarioLogado().getDefaultUsuarioPerfil() == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Perfil logado não possui acesso");
        }

        Boolean permiteRealizarAcao = statusEtapaAcaoPerfilService.perfilLogadoPodeAlterarStatusEtapa(
                statusEtapaId, acaoId);

        if (!permiteRealizarAcao) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Perfil logado não possui acesso");
        }
    }

    private Page<ProcessoGridResponse> filtraProcessosPorSla(Pageable pageable, List<ProcessoGridResponse> processos,
                                                             String contagemSla) {
        if (processos == null || processos.isEmpty()) {
            return Page.empty();
        }

        List<ProcessoGridResponse> itensFiltrados;

        switch (contagemSla) {
            case "A":
                itensFiltrados = processos.stream().filter(
                        processo -> processo.getSlaProcessoEmAlerta() != null && processo.getSlaProcessoEmAlerta()
                ).collect(Collectors.toList());
                break;
            case "F":
                itensFiltrados = processos.stream().filter(
                        processo -> (processo.getSlaProcessoEmAlerta() != null && !processo.getSlaProcessoEmAlerta())
                                && (processo.getSlaProcesso() != null && Long.parseLong(processo.getSlaProcesso()) < 0)
                ).collect(Collectors.toList());
                break;
            case "D":
                itensFiltrados = processos.stream().filter(
                        processo -> (processo.getSlaProcessoEmAlerta() != null && !processo.getSlaProcessoEmAlerta())
                                && (processo.getSlaProcesso() != null && Long.parseLong(processo.getSlaProcesso()) > 0)
                ).collect(Collectors.toList());
                break;
            default: itensFiltrados = processos;
        }

        int inicio = Math.min((int) pageable.getOffset(), itensFiltrados.size());
        int fim = Math.min((inicio + pageable.getPageSize()), itensFiltrados.size());

        return new PageImpl<>(itensFiltrados.subList(inicio, fim), pageable, itensFiltrados.size());
    }

    private List<ProcessoRelatorioResponse> filtraProcessosPorSlaParaExportacao(List<ProcessoRelatorioResponse> processos,
                                                                                String contagemSla) {
        if (processos == null || processos.isEmpty()) {
            return Collections.emptyList();
        }

        List<ProcessoRelatorioResponse> itensFiltrados;
        switch (contagemSla) {
            case "A":
                itensFiltrados = processos.stream().filter(
                        processo -> processo.getSlaProcessoEmAlerta() != null && processo.getSlaProcessoEmAlerta()
                ).collect(Collectors.toList());
                break;
            case "F":
                itensFiltrados = processos.stream().filter(
                        processo -> (processo.getSlaProcessoEmAlerta() != null && !processo.getSlaProcessoEmAlerta())
                                && (processo.getSlaProcessoTempo() != null && Long.parseLong(processo.getSlaProcessoTempo()) < 0)
                ).collect(Collectors.toList());
                break;
            case "D":
                itensFiltrados = processos.stream().filter(
                        processo -> (processo.getSlaProcessoEmAlerta() != null && !processo.getSlaProcessoEmAlerta())
                                && (processo.getSlaProcessoTempo() != null && Long.parseLong(processo.getSlaProcessoTempo()) > 0)
                ).collect(Collectors.toList());
                break;
            default: itensFiltrados = processos;
        }

        return itensFiltrados;
    }

    private String criaListagemCartasNotificacao(Processo processo) {
        if (processo == null || processo.getId() == null) {
            return null;
        }

        List<ProcessoHistoricoNotificacao> notificacoes = processoHistoricoNotificacaoService.findByProcessoId(processo.getId());

        if (notificacoes == null || notificacoes.isEmpty()) {
            return null;
        }

        StringBuilder notificacaoCarta = new StringBuilder();

        for (ProcessoHistoricoNotificacao notificacao : notificacoes) {
            notificacaoCarta.append(notificacao.getNomeCarta() != null ? notificacao.getNomeCarta() : "");
            notificacaoCarta.append(" enviada para ");
            notificacaoCarta.append(notificacao.getPapel() != null ? notificacao.getPapel().getNome() : "");
            notificacaoCarta
                    .append(" em ")
                    .append(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(notificacao.getData()));

            if (notificacao.getComprovanteDataRecebimento() != null) {
                notificacaoCarta
                        .append(" e comprovado o recebimento em ")
                        .append(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(notificacao.getComprovanteDataRecebimento()));
            }

            if (notificacoes.indexOf(notificacao) != notificacoes.size() - 1) {
                notificacaoCarta.append(" / ");
            }
        }

        return notificacaoCarta.toString();
    }

    @Override
    public void uploadProcesso(Long processoId, ProcessoAnexoUploadRequest request) {
        if (request.getAnexos() != null && !request.getAnexos().isEmpty()) {
            Processo processo = processoRepository.findById(processoId)
                    .orElseThrow(() -> new ProcessoNotFoundException(processoId));

            List<Anexo> anexosSalvos = anexoService.saveAllEntities(request.getAnexos());
            List<Anexo> anexos = new ArrayList<>(processo.getAnexoList());
            anexos.addAll(anexosSalvos);
            processo.mergeProcessoAnexoList(anexos);
            processoRepository.save(processo);
        }
    }

    @Override
    @Transactional
    public ProcessoResponse enviarComunicadoFinalizacao(Long processoId,
                                                        Long statusEtapaDestinoId,
                                                        ProcessoAlterarStatusRequest processoAlterarStatusRequest) {
        return this.enviarProcessoParecer(processoId, statusEtapaDestinoId, processoAlterarStatusRequest);
    }

    @Override
    @Transactional
    public ProcessoResponse enviarParecerOperadora(Long processoId,
                                                   Long statusEtapaDestinoId,
                                                   ProcessoAlterarStatusRequest processoAlterarStatusRequest) {
        return this.enviarProcessoParecer(processoId, statusEtapaDestinoId, processoAlterarStatusRequest);
    }

    @Override
    @Transactional
    public ProcessoResponse solicitarRevisao(Long processoId,
                                             Long statusEtapaDestinoId,
                                             ProcessoAlterarStatusRequest processoAlterarStatusRequest) {
        this.alterarStatusEtapa(processoId, statusEtapaDestinoId, processoAlterarStatusRequest);
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException(processoId));
        validateRequiredFields(processo);

        processo.setParecerVisivelOperadora(false);
        processoRepository.save(processo);

        return processoFactory.buildResponse(processo);
    }

    @Transactional
    public ProcessoResponse enviarProcessoParecer(Long processoId,
                                                  Long statusEtapaDestinoId,
                                                  ProcessoAlterarStatusRequest processoAlterarStatusRequest) {
        this.alterarStatusEtapa(processoId, statusEtapaDestinoId, processoAlterarStatusRequest);
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException(processoId));
        validateRequiredFields(processo);

        processo.setParecerVisivelOperadora(true);
        processoRepository.save(processo);

        return processoFactory.buildResponse(processo);
    }

    private void validateAreaAtuacao(List<ProcessoAreaAtuacaoRequest> areaAtuacaoList) {
        List<AreaAtuacao> areasAtuacao = areaAtuacaoService.findAll();

        for (ProcessoAreaAtuacaoRequest processoAreaAtuacaoRequest : areaAtuacaoList) {
            Optional<AreaAtuacao> areaAtuacao = areasAtuacao.stream().filter(area -> area.getId().equals(processoAreaAtuacaoRequest.getAreaAtuacaoId())).findFirst();

            if (areaAtuacao.isEmpty()) {
                throw new AreaAtuacaoException(processoAreaAtuacaoRequest.getAreaAtuacaoId());
            }

            if (!areaAtuacao.get().getAtivo()) {
                throw new AreaAtuacaoInativaException(areaAtuacao.get().getId());
            }
        }
    }

    @Override
    @Transactional
    public ProcessoResponse editProcessoByInfoEdit(Long infoEditId, ProcessoRequest processoRequest) {
        infoEditService.findEntity(infoEditId);
        Processo processo = findEntityById(processoRequest.getId());
        statusEtapaInfoEditService.findEntity(processo.getStatusEtapa().getId(),
                SystemUtil.getCurrentUsuarioLogado().getDefaultUsuarioPerfil().getPerfil().getId(),
                infoEditId);

        if (processoRequest.getBeneficiario() != null) {
            processo.setBeneficiario(beneficiarioService.editValuesByInfoEdit(processo.getBeneficiario(),
                    processoRequest.getBeneficiario()));
        }

        if (processoRequest.getProfissionalAssistente() != null) {
            processo.setProfissionalAssistente(profissionalAssistenteService.editValuesByInfoEdit(
                    processo, processoRequest.getProfissionalAssistente()));
        }

        if (processoRequest.getAuditor() != null) {
            processo.setAuditor(auditorService.editValuesByInfoEdit(processo, processoRequest.getAuditor()));
        }

        if (processoRequest.getConsultor() != null) {
            if (!CollectionUtils.isNotEmpty(processoRequest.getConsultor().getConselhoList()) &&
                    processoRequest.getConsultor().getNome() == null &&
                    processoRequest.getConsultor().getObservacao() == null &&
                    !CollectionUtils.isNotEmpty(processoRequest.getConsultor().getEspecialidadeList())) {
                processo.setConsultor(null);
            } else {
                if(SystemUtil.getCurrentUsuarioLogado().getOperadora() == null) {
                    ConsultorRequest consultorRequestTemp = processoRequest.getConsultor();
                    consultorRequestTemp.setOperadoraId(processo.getOperadoraSolicitante().getId());
                    processoRequest.setConsultor(consultorRequestTemp);
                }
                processo.setConsultor(consultorService.editValuesByInfoEdit(
                        processo.getConsultor(), processoRequest.getConsultor()));
            }
        }

        if (processoRequest.getPrioridade() != null) {
            processo.setPrioridade(processoRequest.getPrioridade());
        }

        if (processoRequest.getOperadoraOrigemId() != null) {
            Operadora operadoraOrigem = operadoraService.findEntity(processoRequest.getOperadoraOrigemId());
            processo.setOperadoraOrigem(operadoraOrigem);
            processo.setOperadoraLayout(this.updateOperadoraLayoutByOrigem(operadoraOrigem));
        }

        if (processoRequest.getAdministrativoOperadoraId() != null) {
            usuarioService.findEntityById(processoRequest.getAdministrativoOperadoraId());
            processo.setAdministrativoOperadora(Usuario.builder().id(processoRequest.getAdministrativoOperadoraId()).build());
        }

        if (processoRequest.getAdministrativoFescExtId() != null) {
            usuarioService.findEntityById(processoRequest.getAdministrativoFescExtId());
            processo.setAdministrativoFesc(Usuario.builder().id(processoRequest.getAdministrativoFescExtId()).build());
        }

        if (processoRequest.getAuditorQualidadeExtId() != null) {
            usuarioService.findEntityById(processoRequest.getAuditorQualidadeExtId());
            processo.setAuditorQualidade(Usuario.builder().id(processoRequest.getAuditorQualidadeExtId()).build());
        }

        if (processoRequest.getPrazoInicialAns() != null) {
            if (processo.getPrazoFinalAns() != null
                    && processoRequest.getPrazoInicialAns().isAfter(processo.getPrazoFinalAns())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "O Prazo Inicial ANS não deve ser maior que o Prazo Final ANS!");
            }

            processo.setPrazoInicialAns(processoRequest.getPrazoInicialAns());
        }

        if (processoRequest.getPrazoFinalAns() != null) {
            if (processo.getPrazoInicialAns() != null
                    && processo.getPrazoInicialAns().isAfter(processoRequest.getPrazoFinalAns())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "O Prazo Final ANS não deve ser menor que Prazo Inicial ANS!");
            }

            processo.setPrazoFinalAns(processoRequest.getPrazoFinalAns());
        }

        if (processoRequest.getDataParecer() != null) {
            processo.setDataParecer(processoRequest.getDataParecer());
        }

        if (processoRequest.getDocumentacaoCompleta() != null) {
            processo.setDocumentacaoCompleta(processoRequest.getDocumentacaoCompleta());
        }

        if (processoRequest.getPrazoGuiaLegivel() != null) {
            processo.setPrazoGuiaLegivel(processoRequest.getPrazoGuiaLegivel());
        }

        if (processoRequest.getDesempatadorId() != null) {
            Desempatador desempatador = desempatadorService.findEntity(processoRequest.getDesempatadorId());
            processo.setDesempatador(desempatador);
        }

        if (processoRequest.getDmCaraterAtendimentoId() != null) {
            processo.setDmCaraterAtendimento(Dominio.builder().id(processoRequest.getDmCaraterAtendimentoId()).build());
        }

        if (processoRequest.getProcedimentoRealizado() != null) {
            processo.setProcedimentoRealizado(processoRequest.getProcedimentoRealizado());
        }

        if (processoRequest.getPossuiLiminar() != null) {
            processo.setPossuiLiminar(processoRequest.getPossuiLiminar());
        }

        if (processoRequest.getPossuiNip() != null) {
            processo.setPossuiNip(processoRequest.getPossuiNip());
        }

        if (processoRequest.getPlanoRegulamentado() != null) {
            processo.setPlanoRegulamentado(processoRequest.getPlanoRegulamentado());
        }

        if (processoRequest.getIndicacaoClinica() != null) {
            if (processoRequest.getIndicacaoClinica().length() > 500) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Indicação clínica pode conter até 500 caracteres");
            }
            processo.setIndicacaoClinica(processoRequest.getIndicacaoClinica());
        }

        if (processoRequest.getNumeroPedidoGuia() != null) {
            if (processoRequest.getNumeroPedidoGuia().equals("")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número pedido guia é obrigatório");
            }

            if (!StringUtils.isNumeric(processoRequest.getNumeroPedidoGuia())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número pedido guia pode conter apenas números");
            }

            if (processoRequest.getNumeroPedidoGuia().length() > 20) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número pedido guia pode conter até 20 caracteres");
            }
            processo.setNumeroPedidoGuia(processoRequest.getNumeroPedidoGuia());
        }

        if (processoRequest.getJustificativaOperadora() != null) {
            processo.setJustificativaOperadora(processoRequest.getJustificativaOperadora());
        }

        if (processoRequest.getProcessoItemList() != null) {
            processo.mergeProcessoItemList(processoItemService.editValuesByInfoEdit(
                    processoRequest.getProcessoItemList(), processo));
        }

        if (processoRequest.getNumeroProtocolo() != null) {
            processo.setNumeroProtocolo(processoRequest.getNumeroProtocolo());
        }

        if (processoRequest.getParecerVisivelOperadora() != null) {
           processo.setParecerVisivelOperadora(processoRequest.getParecerVisivelOperadora());
        }

        if (processoRequest.getAreaAtuacaoList() != null) {
            processo.mergeProcessoAreaAtuacaoList(
                    processoAreaAtuacaoService.editValuesByInfoEdit(processoRequest.getAreaAtuacaoList(), processo));
        }

        if (processoRequest.getOperadoraLayoutId() != null) {
            processo.setOperadoraLayout(OperadoraLayout.builder().id(processoRequest.getOperadoraLayoutId()).build());
        }

        processo = processoRepository.save(processo);

        return processoFactory.buildResponse(processo);
    }

    private List<String> validateProcessoEdit(ProcessoEditRequest processoEditRequest) {
        List<String> listValidations = new ArrayList<>();

        if (processoEditRequest.getPrazoInicialAns() == null) {
            listValidations.add("O Prazo Inicial ANS é obrigatório.");
        } else if (processoEditRequest.getPrazoInicialAns() == null) {
            listValidations.add("O Prazo Final ANS é obrigatório.");
        } else if (processoEditRequest.getPrazoInicialAns() != null && processoEditRequest.getPrazoFinalAns() != null
                && processoEditRequest.getPrazoInicialAns().isAfter(processoEditRequest.getPrazoFinalAns())) {
            listValidations.add("O Prazo Inicial ANS não deve ser maior que o Prazo Final ANS");
        } else if (processoEditRequest.getIndicacaoClinica() == null || processoEditRequest.getIndicacaoClinica().length() == 0) {
            listValidations.add("Indicação clinica é obrigatório.");
        }
        if (processoEditRequest.getJustificativaOperadora() == null || processoEditRequest.getJustificativaOperadora().length() == 0) {
            listValidations.add("A justificativa da operadora é obrigatório.");
        }

        return listValidations;
    }

    private OperadoraLayout updateOperadoraLayoutByOrigem(Operadora operadoraOrigem) {
        List<OperadoraLayout> layouts = operadoraOrigem.getLayouts();

        if (Objects.isNull(layouts) || layouts.size() != 1) {
            return null;
        }

        return layouts.get(0);
    }

    private void validateRequiredFields(Processo processo) {
        if (processo.getBeneficiario().getNome() == null) {
            throw new ProcessoMissingRequiredFieldException("Nome do Beneficiário obrigatório");
        }

        if (processo.getBeneficiario().getDmSexoBeneficiario() == null) {
            throw new ProcessoMissingRequiredFieldException("Sexo do Beneficiário obrigatório");
        }

        if (processo.getBeneficiario().getDataNascimento() == null) {
            throw new ProcessoMissingRequiredFieldException("Data nascimento do Beneficiário obrigatório");
        }

        if (processo.getBeneficiario().getContatoList() == null || processo.getBeneficiario().getContatoList().size() < 2) {
            throw new ProcessoMissingRequiredFieldException("Email/Telefone Fixo/Telefone Celular do Beneficiário obrigatório");
        } else {
            Integer telefoneCelular = 0;
            Integer telefoneFixo = 0;
            Integer email = 0;
            DominioResponse dominioCelular = dominioService.findByNomeAndValor("dm_tipo_contato", "C");
            DominioResponse dominioFixo = dominioService.findByNomeAndValor("dm_tipo_contato", "M");
            DominioResponse dominioEmail = dominioService.findByNomeAndValor("dm_tipo_contato", "E");
            for (Contato contato : processo.getBeneficiario().getContatoList()) {
                if (contato.getDmTipoContato().getId() == dominioCelular.getId()) telefoneCelular++;
                if (contato.getDmTipoContato().getId() == dominioFixo.getId()) telefoneFixo++;
                if (contato.getDmTipoContato().getId() == dominioEmail.getId()) email++;
            }

            if (telefoneCelular + telefoneFixo == 0) {
                throw new ProcessoMissingRequiredFieldException("Telefone Fixo ou Telefone Celular do Beneficiário obrigatório");
            }
            if (email == 0) {
                throw new ProcessoMissingRequiredFieldException("Email do Beneficiário obrigatório");
            }
        }

        if (processo.getBeneficiario().getEndereco() == null) {
            throw new ProcessoMissingRequiredFieldException("Endereço do Beneficiário obrigatório");
        } else {
            if (processo.getBeneficiario().getEndereco().getCep() == null) {
                throw new ProcessoMissingRequiredFieldException("CEP do Beneficiário obrigatório");
            }

            if (processo.getBeneficiario().getEndereco().getLogradouro() == null) {
                throw new ProcessoMissingRequiredFieldException("Logradouro do Beneficiário obrigatório");
            }

            if (processo.getBeneficiario().getEndereco().getNumero() == null) {
                throw new ProcessoMissingRequiredFieldException("Número do Beneficiário obrigatório");
            }

            if (processo.getBeneficiario().getEndereco().getBairro() == null) {
                throw new ProcessoMissingRequiredFieldException("Bairro do Beneficiário obrigatório");
            }

            if (processo.getBeneficiario().getEndereco().getCidade() == null) {
                throw new ProcessoMissingRequiredFieldException("Cidade do Beneficiário obrigatório");
            }

            if (processo.getBeneficiario().getEndereco().getUf() == null) {
                throw new ProcessoMissingRequiredFieldException("Estado do Beneficiário obrigatório");
            }
        }

        if (processo.getProfissionalAssistente().getNome() == null) {
            throw new ProcessoMissingRequiredFieldException("Nome do Profissional Assistente obrigatório");

        }

        if (processo.getProfissionalAssistente().getEspecialidadeList() == null) {
            throw new ProcessoMissingRequiredFieldException("Especialidade do Profissional Assistente obrigatório");
        }

        if (processo.getProfissionalAssistente().getContatoList() == null || processo.getProfissionalAssistente().getContatoList().size() < 2) {
            throw new ProcessoMissingRequiredFieldException("Email/Telefone Fixo/Telefone Celular do Profissional Assistente obrigatório");
        } else {
            Integer telefoneCelular = 0;
            Integer telefoneComercial = 0;
            Integer email = 0;

            DominioResponse dominioCelular = dominioService.findByNomeAndValor("dm_tipo_contato", "C");
            DominioResponse dominioComercial = dominioService.findByNomeAndValor("dm_tipo_contato", "M");
            DominioResponse dominioEmail = dominioService.findByNomeAndValor("dm_tipo_contato", "E");

            for (Contato contato : processo.getProfissionalAssistente().getContatoList()) {
                if (contato.getDmTipoContato().getId() == dominioCelular.getId()) telefoneCelular++;
                if (contato.getDmTipoContato().getId() == dominioComercial.getId()) telefoneComercial++;
                if (contato.getDmTipoContato().getId() == dominioEmail.getId()) email++;
            }

            if (telefoneCelular + telefoneComercial == 0) {
                throw new ProcessoMissingRequiredFieldException("Telefone Comercial/Telefone Celular do Profissional Assistente obrigatório");
            }
            if (email == 0) {
                throw new ProcessoMissingRequiredFieldException("Email/Telefone Comercial/Telefone Celular do Profissional Assistente obrigatório");
            }
        }

        if (processo.getProfissionalAssistente().getEndereco() == null) {
            throw new ProcessoMissingRequiredFieldException("Endereço do Profissional Assistente obrigatório");
        } else {
            if (processo.getProfissionalAssistente().getEndereco().getCep() == null) {
                throw new ProcessoMissingRequiredFieldException("CEP do Profissional Assistente obrigatório");
            }

            if (processo.getProfissionalAssistente().getEndereco().getLogradouro() == null) {
                throw new ProcessoMissingRequiredFieldException("Logradouro do Profissional Assistente obrigatório");
            }

            if (processo.getProfissionalAssistente().getEndereco().getNumero() == null) {
                throw new ProcessoMissingRequiredFieldException("Número do Profissional Assistente obrigatório");
            }

            if (processo.getProfissionalAssistente().getEndereco().getBairro() == null) {
                throw new ProcessoMissingRequiredFieldException("Bairro do Profissional Assistente obrigatório");
            }

            if (processo.getProfissionalAssistente().getEndereco().getCidade() == null) {
                throw new ProcessoMissingRequiredFieldException("Cidade do Profissional Assistente obrigatório");
            }

            if (processo.getProfissionalAssistente().getEndereco().getUf() == null) {
                throw new ProcessoMissingRequiredFieldException("Estado do Profissional Assistente obrigatório");
            }
        }

        if (processo.getAuditor().getNome() == null) {
            throw new ProcessoMissingRequiredFieldException("Nome do Auditor obrigatório");
        }

        if (processo.getAuditor().getEspecialidadeList() == null ||
                processo.getAuditor().getEspecialidadeList().isEmpty()) {
            throw new ProcessoMissingRequiredFieldException("Especialidade do Auditor obrigatório");
        }

        if (processo.getConsultor() != null) {
            if (processo.getConsultor().getNome() == null && (processo.getConsultor().getEspecialidadeList() != null ||
                    processo.getConsultor().getConselhoList() != null)) {
                throw new ProcessoMissingRequiredFieldException("Nome do consultor obrigatório");
            }

            if (!CollectionUtils.isNotEmpty(processo.getConsultor().getEspecialidadeList()) && (processo.getConsultor().getNome() != null ||
                    processo.getConsultor().getConselhoList() != null)) {
                throw new ProcessoMissingRequiredFieldException("Especialidade do consultor obrigatório");
            }

            if (!CollectionUtils.isNotEmpty(processo.getConsultor().getConselhoList()) && (processo.getConsultor().getNome() != null ||
                    processo.getConsultor().getEspecialidadeList() != null)) {
                throw new ProcessoMissingRequiredFieldException("Conselho do consultor obrigatório");
            }
        }
    }

    private LocalDateTime getProximoDiaUtil(List<CalendarioDiaHora> horarios, List<CalendarioFeriado> feriados) {

        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        LocalDateTime proximoDiaUtil = currentLocalDateTime.plusDays(1);

        while (this.isHoliday(proximoDiaUtil, feriados) || !this.isWorkDay(proximoDiaUtil, horarios)) {
            proximoDiaUtil = proximoDiaUtil.plusDays(1);
        }

        LocalDateTime finalProximoDiaUtil = proximoDiaUtil;
        CalendarioDiaHora calendarioDiaHora = horarios.stream()
                .filter(h -> finalProximoDiaUtil.getDayOfWeek().getValue() == Integer.parseInt(h.getDmDiaSemana().getValor()))
                .min(Comparator.comparing(CalendarioDiaHora::getHoraInicial))
                .orElse(null);

        if (Objects.nonNull(calendarioDiaHora) && Objects.nonNull(proximoDiaUtil)) {
            List<String> calendarioTime = List.of(calendarioDiaHora.getHoraInicial().split(":"));

            return proximoDiaUtil
                    .withHour(Integer.parseInt(calendarioTime.get(0)))
                    .withMinute(Integer.parseInt(calendarioTime.get(1)))
                    .withSecond(Integer.parseInt(calendarioTime.get(2)))
                    .withNano(0);
        }

        return proximoDiaUtil;
    }

    private boolean isWorkDay(LocalDateTime date, List<CalendarioDiaHora> horarios) {
        return horarios.stream()
                .anyMatch(h -> date.getDayOfWeek().getValue() == Integer.parseInt(h.getDmDiaSemana().getValor()));
    }

    private boolean isHoliday(LocalDateTime date, List<CalendarioFeriado> feriados) {
        if (Objects.isNull(date)) {
            return false;
        }

        return feriados.stream()
                .anyMatch(cf -> cf.getData().getDayOfMonth() == date.getDayOfMonth()
                        && cf.getData().getMonthValue() == date.getMonthValue());
    }

    private String criaListagemTelegramas(Processo processo) {
        if (processo == null || processo.getId() == null) {
            return null;
        }

        List<ProcessoHistoricoNotificacao> notificacoes = processoHistoricoNotificacaoService
                .findByProcessoIdAndNotificacaoTipo(processo.getId(), NotificacaoTipo.TELEGRAMA);

        if (notificacoes == null || notificacoes.isEmpty()) {
            return null;
        }

        StringBuilder notificacaoCarta = new StringBuilder();

        for (ProcessoHistoricoNotificacao notificacao : notificacoes) {
            notificacaoCarta.append("Telegrama nº ");
            notificacaoCarta.append(notificacao.getTelegramaNumero() != null ? notificacao.getTelegramaNumero() : "");

            DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance(new Locale("pt", "BR"));
            String valorFormatado = "R$ " + notificacao.getTelegramaValor() != null
                    ? new DecimalFormat("#,##0.00", decimalFormatSymbols).format(notificacao.getTelegramaValor())
                    : "0,00";

            notificacaoCarta
                    .append(" no valor de R$ ").append(valorFormatado);
            notificacaoCarta.append(" em ").append(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(notificacao.getData()));

            if (notificacoes.indexOf(notificacao) != notificacoes.size() - 1) {
                notificacaoCarta.append(" / ");
            }
        }

        return notificacaoCarta.toString();
    }
}

