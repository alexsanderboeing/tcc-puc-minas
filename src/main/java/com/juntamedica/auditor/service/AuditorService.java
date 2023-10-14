package com.juntamedica.auditor.service;

import com.juntamedica.auditor.Auditor;
import com.juntamedica.auditor.payload.AuditorComboRequest;
import com.juntamedica.auditor.payload.AuditorComboResponse;
import com.juntamedica.auditor.payload.AuditorFromRestricaoRequest;
import com.juntamedica.auditor.payload.AuditorRequest;
import com.juntamedica.auditor.payload.AuditorResponse;
import com.juntamedica.processo.Processo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuditorService {

    Auditor saveEntity(AuditorRequest auditorRequest);

    AuditorResponse save(AuditorRequest auditorRequest);

    AuditorResponse saveFromRestricao(AuditorFromRestricaoRequest auditorRequest);

    Auditor editEntity(AuditorRequest auditorRequest);

    AuditorResponse edit(AuditorRequest auditorRequest);

    AuditorResponse findAuditorConselho(Long dmTipoConselhoId,
                                        String numeroConselho,
                                        String ufConselho,
                                        Long operadoraId);

    Page<AuditorResponse> findAll(AuditorRequest auditorRequest, Pageable pageable);

    Page<AuditorResponse> findAllByNomeOrConselhoNumero(AuditorComboRequest auditorComboRequest, Pageable pageable);

    List<AuditorComboResponse> findAllByNomeOrConselhoNumeroAndPreCadastro(AuditorComboRequest auditorComboRequest);

    Page<AuditorResponse> findAllGrid(AuditorRequest auditorRequest, Pageable pageable);

    Auditor findEntity(Long id);

    AuditorResponse findById(Long id);

    Auditor editValuesByInfoEdit(Processo processo, AuditorRequest auditor1);

    void delete(Long id);
}
