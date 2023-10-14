package com.juntamedica.beneficiario.service;

import com.juntamedica.beneficiario.Beneficiario;
import com.juntamedica.beneficiario.payload.BeneficiarioComboResponse;
import com.juntamedica.beneficiario.payload.BeneficiarioGridResponse;
import com.juntamedica.beneficiario.payload.BeneficiarioRequest;
import com.juntamedica.beneficiario.payload.BeneficiarioResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BeneficiarioService {

    void deletePreCadastro(Long beneficiarioId);

    Page<BeneficiarioGridResponse> findAllGridPreCadastro(BeneficiarioRequest beneficiarioRequest, Pageable pageable);

    BeneficiarioResponse findByIdAndPreCadastro(Long beneficiarioId);

    Beneficiario saveEntity(BeneficiarioRequest beneficiarioRequest);

    BeneficiarioResponse save(BeneficiarioRequest beneficiarioRequest);

    Beneficiario editEntity(BeneficiarioRequest beneficiarioRequest);

    BeneficiarioResponse edit(BeneficiarioRequest beneficiarioRequest);

    Page<BeneficiarioComboResponse> findAllCombo(BeneficiarioRequest beneficiarioRequest, Pageable pageable);

    BeneficiarioResponse findByCodigoAndPreCadastro(String codigo, Long operadoraId);

    BeneficiarioResponse find(Long id);

    Beneficiario findEntity(Long id);

    Page<BeneficiarioComboResponse> findAllComboPreCadastro(BeneficiarioRequest beneficiarioRequest, Pageable pageable);

    Beneficiario editValuesByInfoEdit(Beneficiario beneficiario, BeneficiarioRequest beneficiarioRequest);
}
