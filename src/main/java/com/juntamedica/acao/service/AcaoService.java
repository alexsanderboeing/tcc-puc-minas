package com.juntamedica.acao.service;

import com.juntamedica.acao.Acao;
import com.juntamedica.acao.payload.AcaoComboRequest;
import com.juntamedica.acao.payload.AcaoComboResponse;
import com.juntamedica.acao.payload.AcaoCreateRequest;
import com.juntamedica.acao.payload.AcaoRequest;
import com.juntamedica.acao.payload.AcaoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AcaoService {

    void editAcaoOrdemEmTela(Long ordemEmTela, Long direcao);

    AcaoResponse save(AcaoCreateRequest acaoRequest);

    AcaoResponse edit(AcaoRequest acaoRequest);

    void delete(Long acaoId);

    List<AcaoResponse> findAll();

    AcaoResponse findById(Long acaoId);

    Acao findEntityById(Long acaoId);

    Page<AcaoResponse> findAllWithFilters(AcaoRequest acaoRequest, Pageable pageable);

    Page<AcaoComboResponse> findAllCombo(AcaoComboRequest acaoComboRequest, Pageable pageable);
}
