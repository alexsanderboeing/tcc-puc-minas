package com.juntamedica.anexo.factory;

import com.juntamedica.anexo.Anexo;
import com.juntamedica.anexo.payload.AnexoDownloadResponse;
import com.juntamedica.anexo.payload.AnexoRequest;
import com.juntamedica.anexo.payload.AnexoResponse;
import com.juntamedica.anexo.payload.AnexoResponseGrid;
import com.juntamedica.anexo.payload.AnexoUploadResponse;
import com.juntamedica.anexo.service.AnexoService;
import com.juntamedica.classificacaoanexo.ClassificacaoAnexo;
import com.juntamedica.modelocarta.payload.ModeloCartaFileResponse;
import com.juntamedica.utils.Base64File;
import com.juntamedica.utils.SystemUtil;
import com.juntamedica.utils.storage.payload.StorageDocumentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AnexoFactory {

    @Autowired
    AnexoService anexoService;

    public Anexo build(AnexoRequest anexoRequest, AnexoUploadResponse anexoUploadResponse) {
        return Anexo.builder()
                .id(anexoRequest.getId())
                .uuid(anexoUploadResponse.getUuid())
                .nome(anexoUploadResponse.getNome())
                .mimeType(AnexoMimeTypeEnum.get(anexoRequest.getMimeType()))
                .classificacaoAnexo(
                        anexoRequest.getClassificacaoAnexoId() == null
                                ? null
                                : ClassificacaoAnexo.builder().id(anexoRequest.getClassificacaoAnexoId()).build())
                .usuarioId(Objects.requireNonNull(SystemUtil.getCurrentUsuarioLogado()).getId())
                .build();
    }

    public Anexo build(AnexoRequest anexoRequest) {
        return Anexo.builder()
                .id(anexoRequest.getId())
                .uuid(anexoRequest.getUuid())
                .nome(anexoRequest.getNome())
                .mimeType(AnexoMimeTypeEnum.get(anexoRequest.getMimeType()))
                .classificacaoAnexo(
                        anexoRequest.getClassificacaoAnexoId() == null
                                ? null
                                : ClassificacaoAnexo.builder().id(anexoRequest.getClassificacaoAnexoId()).build())
                .usuarioId(Objects.requireNonNull(SystemUtil.getCurrentUsuarioLogado()).getId())
                .extensao(anexoRequest.getExtensao())
                .build();
    }

    public AnexoRequest build(ModeloCartaFileResponse modeloCartaFileResponse) {
        String nome = modeloCartaFileResponse.getFilename().lastIndexOf(".") > 0
                ? modeloCartaFileResponse.getFilename().substring(0, modeloCartaFileResponse.getFilename().lastIndexOf("."))
                : modeloCartaFileResponse.getFilename();

        return AnexoRequest.builder()
                .id(null)
                .nome(nome)
                .mimeType(AnexoMimeTypeEnum.get("application/pdf").getValue())
                .classificacaoAnexoId(1L)
                .extensao("pdf")
                .base64(modeloCartaFileResponse.getBase64())
                .build();
    }

    public AnexoUploadResponse build(Base64File base64File, StorageDocumentResponse storageDocumentResponse) {
        return AnexoUploadResponse.builder()
                .uuid(storageDocumentResponse.getKey())
                .nome(base64File.getNameFile())
                .build();
    }

    public AnexoDownloadResponse build(Anexo anexo, String base64) {
        return AnexoDownloadResponse.builder()
                .id(anexo.getId())
                .nome(anexo.getNome())
                .mimeType(anexo.getMimeType().getValue())
                .base64(base64)
                .extensao(anexo.getExtensao())
                .build();
    }

    public List<Anexo> buildList(List<AnexoRequest> anexoRequestList) {
        if (anexoRequestList == null || anexoRequestList.isEmpty()) {
            return Collections.emptyList();
        }

        return anexoRequestList.stream().map(this::build).collect(Collectors.toList());
    }

    public AnexoResponse buildResponse(Anexo anexo) {
        return AnexoResponse.builder()
                .id(anexo.getId())
                .classificacaoAnexoId(!Objects.isNull(anexo.getClassificacaoAnexo())
                        ? anexo.getClassificacaoAnexo().getId()
                        : null)
                .uuid(anexo.getUuid())
                .nome(anexo.getNome())
                .mimeType(Objects.isNull(anexo.getMimeType())
                        ? null
                        : anexo.getMimeType().getValue())
                .usuarioId(anexo.getUsuarioId())
                .isNewFile(false)
                .deleted(false)
                .extensao(anexo.getExtensao())
                .build();
    }

    public List<AnexoResponse> buildResponseList(List<Anexo> processoAnexoList) {
        return processoAnexoList.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    public AnexoResponseGrid buildResponseGrid(Anexo anexo) {
        return AnexoResponseGrid.builder()
                .id(anexo.getId())
                .classificacaoAnexo(!Objects.isNull(anexo.getClassificacaoAnexo())
                        ? anexo.getClassificacaoAnexo().getDescricao()
                        : null)
                .mimeType(Objects.isNull(anexo.getMimeType())
                        ? null
                        : anexo.getMimeType().getValue())
                .uuid(anexo.getUuid())
                .nome(anexo.getNome())
                .usuarioId(anexo.getUsuarioId())
                .createdAt(anexo.getUpdatedAt() != null ? anexo.getUpdatedAt() : anexo.getCreatedAt())
                .extensao(anexo.getExtensao())
                .build();
    }

    public List<AnexoResponseGrid> buildResponseListGrid(List<Anexo> processoAnexoList) {
        return processoAnexoList.stream().map(this::buildResponseGrid).collect(Collectors.toList());
    }
}
