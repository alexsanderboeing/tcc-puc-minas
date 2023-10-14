package com.juntamedica.processo;

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
import com.juntamedica.processo.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("processo")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @GetMapping("{processoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoResponse find(@PathVariable Long processoId) {
        return processoService.findById(processoId);
    }

    @PostMapping("findAllGrid")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProcessoGridResponse> findAllGrid(
            @RequestBody(required = false) ProcessoFiltersRequest processoFiltersRequest, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return processoService.findAllGrid(processoFiltersRequest, pageable);
    }

    @PostMapping("findTotalizadorGrid")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoTotalizadorPendenciasResponse findTotalizadorGrid(
            @RequestBody(required = false) ProcessoFiltersRequest processoFiltersRequest) {
        return processoService.findTotalizadorPendencias(processoFiltersRequest);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProcessoResponse save(@Valid @RequestBody ProcessoRequest processoRequest) throws IOException {
        return processoService.save(processoRequest);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public ProcessoResponse edit(@Valid @RequestBody ProcessoEditRequest processoEditRequest) {
        return processoService.edit(processoEditRequest);
    }

    @PatchMapping("{processoId}/{newForeignKey}/{fieldName}")
    @ResponseStatus(HttpStatus.OK)
    public void updateField(@PathVariable Long processoId,
                            @PathVariable Long newForeignKey,
                            @PathVariable String fieldName) {
        processoService.updateField(processoId, newForeignKey, fieldName);
    }

    @DeleteMapping("{processoId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long processoId) {
        processoService.delete(processoId);
    }

    @PostMapping("gerarrelatorio")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InputStreamResource> generateReport(
            @RequestBody(required = false) ProcessoFiltersRequest processoFiltersRequest) throws Exception {

        InputStreamResource exportFile = processoService.loadToCsvExport(processoFiltersRequest);
        String filename = "Processos-" + LocalDateTime.now().getNano() + ".csv";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(exportFile);
    }

    @PostMapping("/{processoId}/iniciar/{statusEtapaDestinoId}")
    @ResponseStatus(HttpStatus.OK)
    public void iniciarProcesso(@PathVariable Long processoId, @PathVariable Long statusEtapaDestinoId) {
        processoService.iniciarProcesso(processoId, statusEtapaDestinoId);
    }

    @PostMapping("/{processoId}/cancelar")
    @ResponseStatus(HttpStatus.OK)
    public void cancelarProcesso(@PathVariable Long processoId, @RequestBody ProcessoCancelRequest processoCancelRequest) {
        processoService.cancelarProcesso(processoId, processoCancelRequest);
    }

    @PostMapping("/{processoId}/escolherdesempatadores")
    @ResponseStatus(HttpStatus.OK)
    public void saveProcessoDesempatadores(@PathVariable Long processoId,
                                           @RequestBody ProcessoDesempatadoresRequest processoDesempatadoresRequest) {
        processoService.saveProcessoDesempatadores(processoId, processoDesempatadoresRequest);
    }

    @GetMapping("/{processoId}/escolherdesempatadores")
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessoDesempatadoresResponse> findDesempatadoresByProcessoId(@PathVariable Long processoId) {
        return processoService.findDesempatadoresByProcessoId(processoId);
    }

    @PostMapping("/{processoId}/definirdesempatador")
    @ResponseStatus(HttpStatus.OK)
    public void definirDesempatadorProcesso(@PathVariable Long processoId,
                                            @RequestBody ProcessoDesempatadorDefinirRequest processoDesempatadorDefinirRequest) {
        processoService.definirDesempatadorProcesso(processoId, processoDesempatadorDefinirRequest);
    }

    @PostMapping("/{processoId}/realizarparecer/rascunho")
    @ResponseStatus(HttpStatus.OK)
    public void realizarParecerRascunho(@PathVariable Long processoId,
                                        @RequestBody ProcessoRealizarParecerRequest processoRealizarParecerRequest) {
        processoService.realizarParecerRascunho(processoId, processoRealizarParecerRequest);
    }

    @PostMapping("/{processoId}/realizarparecer/{statusEtapaDestinoId}")
    @ResponseStatus(HttpStatus.OK)
    public void realizarParecer(@PathVariable Long processoId, @PathVariable Long statusEtapaDestinoId,
                                @RequestBody ProcessoRealizarParecerRequest processoRealizarParecerRequest) {
        processoService.realizarParecer(processoId, statusEtapaDestinoId, processoRealizarParecerRequest);
    }

    @PostMapping("/{processoId}/aprovarparecer/qualidade")
    @ResponseStatus(HttpStatus.OK)
    public void aprovarParecerQualidade(@PathVariable Long processoId,
                                        @Valid @RequestBody ProcessoAprovarParecerQualidadeRequest processoAprovarParecerQualidadeRequest) {
        processoService.aprovarParecerQualidade(processoId, processoAprovarParecerQualidadeRequest);
    }

    @PostMapping("/{processoId}/aprovarparecer/administrativo/{statusEtapaDestinoId}")
    @ResponseStatus(HttpStatus.OK)
    public void aprovarParecerAdministrativo(@PathVariable Long processoId, @PathVariable Long statusEtapaDestinoId) {
        processoService.aprovarParecerAdministrativo(processoId, statusEtapaDestinoId);
    }

    @PostMapping("/{processoId}/aprovarparecer/operadora")
    @ResponseStatus(HttpStatus.OK)
    public void aprovarParecerOperadora(@PathVariable Long processoId,
                                        @RequestBody ProcessoAprovarParecerOperadoraRequest processoAprovarParecerOperadoraRequest) {
        processoService.aprovarParecerOperadora(processoId, processoAprovarParecerOperadoraRequest);
    }

    @PostMapping("/{processoId}/reprovarprocesso/operadora")
    @ResponseStatus(HttpStatus.OK)
    public void reprovarProcessoOperadora(@PathVariable Long processoId,
                                          @RequestBody ProcessoReprovarRequest processoReprovarRequest) {
        processoService.reprovarProcessoOperadora(processoId, processoReprovarRequest);
    }

    @PostMapping("/{processoId}/concluirprocesso/{statusEtapaDestinoId}")
    @ResponseStatus(HttpStatus.OK)
    public void concluirProcesso(@PathVariable Long processoId, @PathVariable Long statusEtapaDestinoId) {
        processoService.concluirProcesso(processoId, statusEtapaDestinoId);
    }

    @PostMapping("/{processoId}/alterarstatusetapa/{statusEtapaDestinoId}")
    @ResponseStatus(HttpStatus.OK)
    public void alterarStatusEtapa(@PathVariable Long processoId,
                                   @PathVariable Long statusEtapaDestinoId,
                                   @Valid @RequestBody(required = false) ProcessoAlterarStatusRequest processoAlterarStatusRequest) {
        processoService.alterarStatusEtapa(processoId, statusEtapaDestinoId, processoAlterarStatusRequest);
    }

    @PostMapping("/{processoId}/enviarcomunicadofinalizacao/{statusEtapaDestinoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoResponse enviarComunicadoFinalizacao(@PathVariable Long processoId,
                                   @PathVariable Long statusEtapaDestinoId,
                                   @Valid @RequestBody(required = false) ProcessoAlterarStatusRequest processoAlterarStatusRequest) {
        return processoService.enviarComunicadoFinalizacao(processoId, statusEtapaDestinoId, processoAlterarStatusRequest);
    }

    @PostMapping("/{processoId}/enviarpareceroperadora/{statusEtapaDestinoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoResponse enviarParecerOperadora(@PathVariable Long processoId,
                                                        @PathVariable Long statusEtapaDestinoId,
                                                        @Valid @RequestBody(required = false) ProcessoAlterarStatusRequest processoAlterarStatusRequest) {
        return processoService.enviarParecerOperadora(processoId, statusEtapaDestinoId, processoAlterarStatusRequest);
    }

    @PostMapping("/{processoId}/solicitarrevisao/{statusEtapaDestinoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoResponse solicitarRevisao(@PathVariable Long processoId,
                                                   @PathVariable Long statusEtapaDestinoId,
                                                   @Valid @RequestBody(required = false) ProcessoAlterarStatusRequest processoAlterarStatusRequest) {
        return processoService.solicitarRevisao(processoId, statusEtapaDestinoId, processoAlterarStatusRequest);
    }

    @PostMapping("/{processoId}/enviarcarta/{statusEtapaDestinoId}/{anexoId}/notificacoes/{notificacaoIdList}/modeloCarta/{modeloCartaId}")
    @ResponseStatus(HttpStatus.OK)
    public void enviarCarta(@PathVariable Long processoId, @PathVariable Long statusEtapaDestinoId,
                            @PathVariable Long anexoId, @PathVariable List<Long> notificacaoIdList, @PathVariable Long modeloCartaId) {
        processoService.enviarCarta(processoId, statusEtapaDestinoId, anexoId, notificacaoIdList, modeloCartaId);
    }

    @GetMapping("/{processoId}/chat")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoChatResponse findByIdChat(@PathVariable Long processoId) {
        return processoService.findByIdChat(processoId);
    }

    @GetMapping("/{processoId}/permissao")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoResponse findByUserOperadoraPermission(@PathVariable Long processoId) {
        return processoService.findByUserOperadoraPermission(processoId);
    }

    @GetMapping("/{processoId}/{userPapelId}/findTotalizadorMenu")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoTotalizadorMenuResponse findTotalizadorMenu(@PathVariable Long processoId,
                                                               @PathVariable Long userPapelId) {
        return processoService.findTotalizadorMenu(processoId, userPapelId);
    }

    @PostMapping("/{processoId}/upload")
    @ResponseStatus(HttpStatus.OK)
    public void uploadProcesso(@PathVariable Long processoId, @RequestBody ProcessoAnexoUploadRequest request) {
        processoService.uploadProcesso(processoId, request);
    }

    @PostMapping("editProcessoByInfoEdit/{infoEditId}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoResponse editProcessoByInfoEdit(@PathVariable Long infoEditId, @RequestBody ProcessoRequest processoRequest) {
        return processoService.editProcessoByInfoEdit(infoEditId, processoRequest);
    }
}
