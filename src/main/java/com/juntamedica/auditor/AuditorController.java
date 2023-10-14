package com.juntamedica.auditor;

import com.juntamedica.auditor.payload.AuditorComboRequest;
import com.juntamedica.auditor.payload.AuditorComboResponse;
import com.juntamedica.auditor.payload.AuditorFromRestricaoRequest;
import com.juntamedica.auditor.payload.AuditorRequest;
import com.juntamedica.auditor.payload.AuditorResponse;
import com.juntamedica.auditor.service.AuditorService;
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
import java.util.List;

@RestController
@RequestMapping("auditor")
public class AuditorController {

    @Autowired
    private AuditorService auditorService;

    @PostMapping("findAll")
    @ResponseStatus(HttpStatus.OK)
    public Page<AuditorResponse> findAll(@RequestBody(required = false) AuditorRequest auditorRequest,
                                         Pageable pageable) {
        return auditorService.findAll(auditorRequest, pageable);
    }

    @PostMapping("findAllByNomeOrConselhoNumero")
    @ResponseStatus(HttpStatus.OK)
    public Page<AuditorResponse> findAll(@RequestBody(required = false) AuditorComboRequest auditorComboRequest,
                                         @PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return auditorService.findAllByNomeOrConselhoNumero(auditorComboRequest, pageable);
    }

    @PostMapping("findAllByNomeOrConselhoNumeroAndPreCadastro")
    @ResponseStatus(HttpStatus.OK)
    public List<AuditorComboResponse> findAllPreCadastro(@RequestBody(required = false) AuditorComboRequest auditorComboRequest) {
        return auditorService.findAllByNomeOrConselhoNumeroAndPreCadastro(auditorComboRequest);
    }

    @PostMapping("findAllGrid")
    @ResponseStatus(HttpStatus.OK)
    public Page<AuditorResponse> findAllGrid(@RequestBody(required = false) AuditorRequest auditorRequest,
                                         @PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return auditorService.findAllGrid(auditorRequest, pageable);
    }

    @GetMapping("find/{dmTipoConselhoId}/{numeroConselho}/{ufConselho}/{operadoraId}")
    @ResponseStatus(HttpStatus.OK)
    public AuditorResponse findProfissinalAssistConselho(@PathVariable Long dmTipoConselhoId,
                                                         @PathVariable String numeroConselho,
                                                         @PathVariable String ufConselho,
                                                         @PathVariable Long operadoraId) {
        return auditorService.findAuditorConselho(dmTipoConselhoId, numeroConselho, ufConselho, operadoraId);
    }

    @GetMapping("findById/{auditorId}")
    @ResponseStatus(HttpStatus.OK)
    public AuditorResponse findById(@PathVariable Long auditorId) {
        return auditorService.findById(auditorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AuditorResponse save(@Valid @RequestBody AuditorRequest auditorRequest) {
        return auditorService.save(auditorRequest);
    }

    @PostMapping("saveFromRestricao")
    @ResponseStatus(HttpStatus.OK)
    public AuditorResponse saveFromRestricao(@Valid @RequestBody AuditorFromRestricaoRequest auditorRequest) {
        return auditorService.saveFromRestricao(auditorRequest);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public AuditorResponse edit(@Valid @RequestBody AuditorRequest auditorRequest) {
        return auditorService.edit(auditorRequest);
    }

    @DeleteMapping("{auditorId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long auditorId) {
        auditorService.delete(auditorId);
    }
}
