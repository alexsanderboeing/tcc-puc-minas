package com.juntamedica.anexo.service;

import com.juntamedica.anexo.Anexo;
import com.juntamedica.anexo.payload.AnexoDownloadResponse;
import com.juntamedica.anexo.payload.AnexoRequest;
import com.juntamedica.anexo.payload.AnexoResponse;
import com.juntamedica.anexo.payload.AnexoResponseGrid;
import com.juntamedica.anexo.payload.AnexoUploadResponse;
import com.juntamedica.anexo.payload.AnexoZipRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AnexoService {

    List<Anexo> saveAllEntities(List<AnexoRequest> anexoRequestList);

    AnexoResponse save(AnexoRequest anexoRequest);

    Anexo saveEntity(AnexoRequest anexoRequest);

    List<AnexoResponse> saveAll(List<AnexoRequest> anexoRequestList);

    AnexoDownloadResponse getConteudo(String key);

    List<AnexoRequest> uploadAnexosToStorage(List<AnexoRequest> anexoRequestList);

    AnexoUploadResponse uploadAnexo(AnexoRequest anexoRequest);

    List<AnexoResponse> findByClassificacaoAnexoId(Long id);

    Page<AnexoResponseGrid> findByProcessoId(Long processoId, Boolean visivelTelaAnexo, Pageable pageable);

    Anexo findByIdEntity(Long id);

    void downloadAnexo(HttpServletResponse response, String key);

    void delete(Long anexoId);

    void deleteStorage(Long anexoId);

    void deleteStorage(List<Anexo> anexos);

    void downloadAnexoZip(HttpServletResponse response, AnexoZipRequest request);
}
