package com.juntamedica.anexo.service;

import com.juntamedica.anexo.Anexo;
import com.juntamedica.anexo.AnexoRepository;
import com.juntamedica.anexo.exception.AnexoBase64FileDownloadErrorException;
import com.juntamedica.anexo.exception.AnexoNotFoundException;
import com.juntamedica.anexo.exception.AnexoUploadErrorException;
import com.juntamedica.anexo.factory.AnexoFactory;
import com.juntamedica.anexo.payload.AnexoDownloadResponse;
import com.juntamedica.anexo.payload.AnexoRequest;
import com.juntamedica.anexo.payload.AnexoResponse;
import com.juntamedica.anexo.payload.AnexoResponseGrid;
import com.juntamedica.anexo.payload.AnexoUploadResponse;
import com.juntamedica.anexo.payload.AnexoZipRequest;
import com.juntamedica.processo.ProcessoRepository;
import com.juntamedica.processo.exception.ProcessoNotFoundException;
import com.juntamedica.usuario.payload.UsuarioResponse;
import com.juntamedica.usuario.service.UsuarioService;
import com.juntamedica.utils.Base64File;
import com.juntamedica.utils.storage.payload.StorageDocumentResponse;
import com.juntamedica.utils.storage.service.StorageService;
import feign.FeignException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class AnexoServiceImpl implements AnexoService {

    @Autowired
    private StorageService storageService;

    @Autowired
    private AnexoFactory anexoFactory;

    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProcessoRepository processoRepository;

    @Override
    public List<Anexo> saveAllEntities(List<AnexoRequest> anexoRequestList) {
        anexoRequestList = this.uploadAnexosToStorage(anexoRequestList);

        return anexoRepository.saveAll(anexoFactory.buildList(anexoRequestList));
    }

    @Override
    public List<AnexoResponse> saveAll(List<AnexoRequest> anexoRequestList) {
        anexoRequestList = this.uploadAnexosToStorage(anexoRequestList);

        return anexoFactory.buildResponseList(anexoRepository.saveAll(anexoFactory.buildList(anexoRequestList)));
    }

    @Override
    public AnexoResponse save(AnexoRequest anexoRequest) {
        return anexoFactory.buildResponse(
                anexoRepository.save(anexoFactory.build(this.uploadAnexoToStorage(anexoRequest))));
    }

    @Override
    public Anexo saveEntity(AnexoRequest anexoRequest) {
        return anexoRepository.save(anexoFactory.build(this.uploadAnexoToStorage(anexoRequest)));
    }

    public AnexoDownloadResponse getConteudo(String uuid) {
        Anexo anexo = anexoRepository.findTop1ByUuid(uuid).orElseThrow(() -> new AnexoNotFoundException(uuid));
        String base64 = storageService.downloadFile(uuid);

        return anexoFactory.build(anexo, base64);
    }

    public AnexoUploadResponse uploadAnexo(AnexoRequest anexoRequest) {
        Base64File file = new Base64File(anexoRequest.getBase64(), anexoRequest.getNome());
        StorageDocumentResponse storageDocumentResponse = null;

        try {
            storageDocumentResponse = storageService.uploadFile(file, anexoRequest.getMimeType());
        } catch (Exception e) {
            if (anexoRequest.getUuid() != null) {
                storageService.delete(anexoRequest.getUuid());
            }

            throw e;
        } finally {
            file.deleteFile();
        }

        return anexoFactory.build(file, storageDocumentResponse);
    }

    @Override
    public Anexo findByIdEntity(Long id) {
        return anexoRepository.findById(id).orElseThrow(() -> new AnexoNotFoundException(id));
    }

    @Override
    public void downloadAnexo(HttpServletResponse response, String uuid) {
        try {
            Anexo anexo = anexoRepository.findTop1ByUuid(uuid).orElseThrow(() -> new AnexoNotFoundException(uuid));
            String base64 = storageService.downloadFile(anexo.getUuid());

            byte[] base64Bytes = Base64.decodeBase64(base64);

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", anexo.getNome()));
            response.setContentType(anexo.getMimeType().getValue());

            InputStream inputStream = new ByteArrayInputStream(base64Bytes);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());

            bufferedInputStream.transferTo(bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedInputStream.close();
        } catch (IOException e) {
            throw new AnexoBase64FileDownloadErrorException(e.getMessage());
        }
    }

    @Override
    public void delete(Long anexoId) {
        Anexo anexo = anexoRepository.findById(anexoId).orElseThrow(() -> new AnexoNotFoundException(anexoId));

        storageService.delete(anexo.getUuid());
        anexoRepository.deleteById(anexoId);
    }

    @Override
    public void deleteStorage(Long anexoId) {
        Anexo anexo = anexoRepository.findById(anexoId).orElseThrow(() -> new AnexoNotFoundException(anexoId));

        try {
            storageService.delete(anexo.getUuid());
        } catch (FeignException fe) {
            if (fe.status() != HttpStatus.NOT_FOUND.value()) {
                fe.printStackTrace();
            }
        }
    }

    @Override
    public void deleteStorage(List<Anexo> anexos) {
        for (Anexo anexo : anexos) {
            storageService.delete(anexo.getUuid());
        }
    }

    @Override
    public List<AnexoRequest> uploadAnexosToStorage(List<AnexoRequest> anexoRequestList) {
        return anexoRequestList.stream().map(this::uploadAnexoToStorage).collect(Collectors.toList());
    }

    private AnexoRequest uploadAnexoToStorage(AnexoRequest anexoRequest) {
        AnexoUploadResponse anexoUploadResponse = this.uploadAnexo(anexoRequest);
        anexoRequest.setUuid(anexoUploadResponse.getUuid());

        return anexoRequest;
    }

    @Override
    public List<AnexoResponse> findByClassificacaoAnexoId(Long id) {
        return anexoFactory.buildResponseList(anexoRepository.findByClassificacaoAnexoId(id));
    }

    @Override
    public Page<AnexoResponseGrid> findByProcessoId(Long processoId, Boolean visivelTelaAnexo, Pageable pageable) {
        processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNotFoundException(processoId));

        Page<Anexo> anexosPage = anexoRepository.findByProcessoId(processoId, visivelTelaAnexo, pageable);

        List<AnexoResponseGrid> list = getAnexoResponseGrids(anexosPage);

        return new PageImpl<>(list, pageable, anexosPage.getTotalElements());
    }

    private List<AnexoResponseGrid> getAnexoResponseGrids(Page<Anexo> processoAnexoPage) {
        List<AnexoResponseGrid> anexos = anexoFactory.buildResponseListGrid(processoAnexoPage.getContent());
        Map<Long, UsuarioResponse> usersMap = getMapOfUsersFromAnexos(processoAnexoPage);

        anexos.stream().forEach(anexo -> {
            Long userKey = anexo.getUsuarioId();
            UsuarioResponse userLoginResponse = usersMap.get(userKey);
            anexo.setUsuario(userLoginResponse.getName());
        });

        return anexos;
    }

    private Map<Long, UsuarioResponse> getMapOfUsersFromAnexos(Page<Anexo> processoAnexoPage) {
        List<Long> userIdList = processoAnexoPage.getContent().stream()
                .mapToLong(Anexo::getUsuarioId)
                .boxed()
                .collect(Collectors.toList());

        if (!userIdList.isEmpty()) {
            List<UsuarioResponse> allUsers = usuarioService.findByUsuarioIdList(userIdList);

            return allUsers.stream()
                    .collect(Collectors.toMap(UsuarioResponse::getId, Function.identity()));
        }

        return Collections.emptyMap();
    }

    @Override
    public void downloadAnexoZip(HttpServletResponse response, AnexoZipRequest request) {
        if (request.getAnexosIds() == null || request.getAnexosIds().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anexos são obrigatórios!");
        }

        List<Anexo> anexos = anexoRepository.findByIds(request.getAnexosIds());

        if (anexos == null || anexos.isEmpty()) {
            return;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        Map<String, Anexo> arquivos = new HashMap<>();

        for (Anexo anexo: anexos) {
            String arquivoComExtensao = anexo.getNome() + "." + anexo.getExtensao();
            if (!arquivos.containsKey(arquivoComExtensao)) {
                arquivos.put(arquivoComExtensao, anexo);
            }
        }

        for (Map.Entry<String, Anexo> entry : arquivos.entrySet()) {
            adicionaAnexoAoZip(zos, entry.getValue());
        }

        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "anexos.zip"));
        response.setContentType("application/octet-stream");

        try {
            zos.closeEntry();
            zos.close();

            InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());

            bufferedInputStream.transferTo(bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedInputStream.close();
        } catch (Exception e) {
            throw new AnexoBase64FileDownloadErrorException(e.getMessage());
        }
    }

    private void adicionaAnexoAoZip(ZipOutputStream zos, Anexo anexo) {
        String base64 = storageService.downloadFile(anexo.getUuid());
        byte[] base64Bytes = Base64.decodeBase64(base64);

        ZipEntry entry = new ZipEntry(anexo.getNome() + "." + anexo.getExtensao());
        try {
            entry.setSize(base64Bytes.length);
            zos.putNextEntry(entry);
            zos.write(base64Bytes);
        } catch (Exception e) {
            throw new AnexoBase64FileDownloadErrorException(e.getMessage());
        }
    }
}
