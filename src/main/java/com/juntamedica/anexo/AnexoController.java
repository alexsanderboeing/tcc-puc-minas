package com.juntamedica.anexo;

import com.juntamedica.anexo.payload.AnexoDownloadResponse;
import com.juntamedica.anexo.payload.AnexoResponse;
import com.juntamedica.anexo.payload.AnexoResponseGrid;
import com.juntamedica.anexo.payload.AnexoZipRequest;
import com.juntamedica.anexo.service.AnexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("anexo")
public class AnexoController {

    @Autowired
    private AnexoService anexoService;

    @GetMapping("findAllByClassificacaoAnexo/{id}")
    public List<AnexoResponse> findByClassificacaoAnexoId(@PathVariable Long id) {
        return anexoService.findByClassificacaoAnexoId(id);
    }

    @GetMapping("{processoId}/{visivelTelaAnexo}")
    public Page<AnexoResponseGrid> findByProcessoId(
            @PathVariable Long processoId, @PathVariable Boolean visivelTelaAnexo, @PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return anexoService.findByProcessoId(processoId, visivelTelaAnexo, pageable);
    }

    @GetMapping("conteudo/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public AnexoDownloadResponse findConteudoAnexo(@PathVariable String uuid) {
        return anexoService.getConteudo(uuid);
    }

    @GetMapping("download/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void downloadAnexo(HttpServletResponse response, @PathVariable String uuid) {
        anexoService.downloadAnexo(response, uuid);
    }

    @DeleteMapping("{anexoId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long anexoId) {
        anexoService.delete(anexoId);
    }

    @PostMapping("zip")
    @ResponseStatus(HttpStatus.OK)
    public void downloadZip(HttpServletResponse response, @RequestBody AnexoZipRequest request) {
        anexoService.downloadAnexoZip(response, request);
    }
}
