package com.juntamedica.acao;

import com.juntamedica.acao.payload.AcaoComboRequest;
import com.juntamedica.acao.payload.AcaoComboResponse;
import com.juntamedica.acao.payload.AcaoCreateRequest;
import com.juntamedica.acao.payload.AcaoRequest;
import com.juntamedica.acao.payload.AcaoResponse;
import com.juntamedica.acao.service.AcaoService;
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
@RequestMapping("acao")
public class AcaoController {

    @Autowired
    private AcaoService acaoService;

    @GetMapping("findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<AcaoResponse> findAll() {
        return acaoService.findAll();
    }

    @PostMapping("findAllCombo")
    @ResponseStatus(HttpStatus.OK)
    public Page<AcaoComboResponse> findAllCombo(
            @RequestBody(required = false) AcaoComboRequest acaoComboRequest,
            @PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return acaoService.findAllCombo(acaoComboRequest, pageable);
    }

    @PostMapping("findAll")
    @ResponseStatus(HttpStatus.OK)
    public Page<AcaoResponse> findAllFilters(@RequestBody(required = false) AcaoRequest acaoRequest,
                                             @PageableDefault(sort = {"ordemEmTela"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return acaoService.findAllWithFilters(acaoRequest, pageable);
    }

    @GetMapping("findById/{acaoId}")
    @ResponseStatus(HttpStatus.OK)
    public AcaoResponse findById(@PathVariable Long acaoId) {
        return acaoService.findById(acaoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AcaoResponse save(@Valid @RequestBody AcaoCreateRequest acaoRequest) {
        return acaoService.save(acaoRequest);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public AcaoResponse edit(@Valid @RequestBody AcaoRequest acaoRequest) {
        return acaoService.edit(acaoRequest);
    }

    @DeleteMapping("{acaoId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long acaoId) {
        acaoService.delete(acaoId);
    }

    @PatchMapping("ordemEmTela/{ordemEmTela}/{direcao}")
    @ResponseStatus(HttpStatus.OK)
    public void editAcaoOrdemEmTela(@PathVariable Long ordemEmTela, @PathVariable Long direcao) {
        acaoService.editAcaoOrdemEmTela(ordemEmTela, direcao);
    }
}
