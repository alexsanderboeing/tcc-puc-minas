package com.juntamedica.processo.service;

import com.juntamedica.anexo.Anexo;
import com.juntamedica.processo.Processo;
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
import com.juntamedica.processo.payload.ProcessoReprovarRequest;
import com.juntamedica.processo.payload.ProcessoRequest;
import com.juntamedica.processo.payload.ProcessoResponse;
import com.juntamedica.processo.payload.ProcessoTotalizadorMenuResponse;
import com.juntamedica.processo.payload.ProcessoTotalizadorPendenciasResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.List;

public interface ProcessoService {

    void alterarStatusEtapa(Long processoId,
                            Long statusEtapaDestinoId,
                            ProcessoAlterarStatusRequest processoAlterarStatusRequest);

    ProcessoResponse editProcessoByInfoEdit(Long infoEditId, ProcessoRequest processoRequest);

    void enviarCarta(Long processoId, Long statusEtapaDestinoId, Long anexoId, List<Long> notificacaoIdList,
                     Long modeloCartaId);

    void aprovarParecerAdministrativo(Long processoId, Long statusEtapaDestinoId);

    void aprovarParecerOperadora(Long processoId,
                                 ProcessoAprovarParecerOperadoraRequest processoAprovarParecerOperadoraRequest);

    void aprovarParecerQualidade(Long processoId,
                                 ProcessoAprovarParecerQualidadeRequest processoAprovarParecerQualidadeRequest);

    void cancelarProcesso(Long processoId, ProcessoCancelRequest processoCancelRequest);

    void concluirProcesso(Long processoId, Long statusEtapaDestinoId);

    void definirDesempatadorProcesso(Long processoId,
                                     ProcessoDesempatadorDefinirRequest processoDesempatadorDefinirRequest);

    List<ProcessoDesempatadoresResponse> findDesempatadoresByProcessoId(Long processoId);

    ProcessoTotalizadorMenuResponse findTotalizadorMenu(Long processoId, Long userPapelId);

    void iniciarProcesso(Long processoId, Long statusEtapaDestinoId);

    void realizarParecer(Long processoId, Long statusEtapaDestinoId,
                         ProcessoRealizarParecerRequest processoRealizarParecerRequest);

    void realizarParecerRascunho(Long processoId, ProcessoRealizarParecerRequest processoRealizarParecerRequest);

    void reprovarProcessoOperadora(Long processoId, ProcessoReprovarRequest processoReprovarRequest);

    ProcessoResponse save(ProcessoRequest processoRequest) throws IOException;

    ProcessoResponse edit(ProcessoEditRequest processoEditRequest);

    void saveProcessoDesempatadores(Long processoId, ProcessoDesempatadoresRequest processoDesempatadoresRequest);

    void updateField(Long processoId, Long foreignKey, String fieldName);

    void delete(Long processoId);

    ProcessoResponse findById(Long processoId);

    Processo findEntityById(Long processoId);

    Integer findByFluxoProcessoId(Long fluxoProcessoId);

    ProcessoTotalizadorPendenciasResponse findTotalizadorPendencias(ProcessoFiltersRequest processoFiltersRequest);

    Page<ProcessoGridResponse> findAllGrid(ProcessoFiltersRequest processoFiltersRequest, Pageable pageable);

    InputStreamResource loadToCsvExport(ProcessoFiltersRequest processoFiltersRequest) throws Exception;

    ProcessoChatResponse findByIdChat(Long processoId);

    Processo saveProcessoAnexo(Long processoId, Anexo anexo);

    ProcessoResponse findByUserOperadoraPermission(Long processoId);

    void uploadProcesso(Long processoId, ProcessoAnexoUploadRequest request);

    ProcessoResponse enviarComunicadoFinalizacao(Long processoId,
                                        Long statusEtapaDestinoId,
                                        ProcessoAlterarStatusRequest processoAlterarStatusRequest);

    ProcessoResponse enviarParecerOperadora(Long processoId,
                                            Long statusEtapaDestinoId,
                                            ProcessoAlterarStatusRequest processoAlterarStatusRequest);

    ProcessoResponse solicitarRevisao(Long processoId,
                                            Long statusEtapaDestinoId,
                                            ProcessoAlterarStatusRequest processoAlterarStatusRequest);
}
