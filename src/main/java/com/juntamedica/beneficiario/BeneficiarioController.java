package com.juntamedica.beneficiario;

import com.juntamedica.beneficiario.payload.BeneficiarioComboResponse;
import com.juntamedica.beneficiario.payload.BeneficiarioGridResponse;
import com.juntamedica.beneficiario.payload.BeneficiarioRequest;
import com.juntamedica.beneficiario.payload.BeneficiarioResponse;
import com.juntamedica.beneficiario.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("beneficiario")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @PostMapping("findAllCombo")
    @ResponseStatus(HttpStatus.OK)
    public Page<BeneficiarioComboResponse> findAllCombo(
            @RequestBody(required = false) BeneficiarioRequest beneficiarioRequest, @PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return beneficiarioService.findAllCombo(beneficiarioRequest, pageable);
    }

    @GetMapping("find/{codigo}/{operadoraId}")
    @ResponseStatus(HttpStatus.OK)
    public BeneficiarioResponse findByCodigo(@PathVariable String codigo, @PathVariable Long operadoraId) {
        return beneficiarioService.findByCodigoAndPreCadastro(codigo, operadoraId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public BeneficiarioResponse save(@Valid @RequestBody BeneficiarioRequest beneficiarioRequest) {
        return beneficiarioService.save(beneficiarioRequest);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public BeneficiarioResponse edit(@Valid @RequestBody BeneficiarioRequest beneficiarioRequest) {
        return beneficiarioService.edit(beneficiarioRequest);
    }

    @GetMapping("find/{beneficiarioId}/preCadastro")
    @ResponseStatus(HttpStatus.OK)
    public BeneficiarioResponse findByIdAndPreCadastro(@PathVariable Long beneficiarioId) {
        return beneficiarioService.findByIdAndPreCadastro(beneficiarioId);
    }


    @PostMapping("findAllCombo/preCadastro")
    @ResponseStatus(HttpStatus.OK)
    public Page<BeneficiarioComboResponse> findAllComboPreCadastro(
            @RequestBody(required = false) BeneficiarioRequest beneficiarioRequest, @PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return beneficiarioService.findAllComboPreCadastro(beneficiarioRequest, pageable);
    }

    @PostMapping("findAllGrid/preCadastro")
    @ResponseStatus(HttpStatus.OK)
    public Page<BeneficiarioGridResponse> findAllGridPreCadastro(
            @RequestBody(required = false) BeneficiarioRequest beneficiarioRequest, @PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return beneficiarioService.findAllGridPreCadastro(beneficiarioRequest, pageable);
    }

    @DeleteMapping("{beneficiarioId}/preCadastro")
    @ResponseStatus(HttpStatus.OK)
    public void deletePreCadastro(@PathVariable Long beneficiarioId) {
        beneficiarioService.deletePreCadastro(beneficiarioId);
    }
}
