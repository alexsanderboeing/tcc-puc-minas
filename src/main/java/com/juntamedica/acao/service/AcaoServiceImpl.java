package com.juntamedica.acao.service;

import com.juntamedica.acao.Acao;
import com.juntamedica.acao.AcaoRepository;
import com.juntamedica.acao.exception.AcaoNotFoundException;
import com.juntamedica.acao.factory.AcaoFactory;
import com.juntamedica.acao.payload.AcaoComboRequest;
import com.juntamedica.acao.payload.AcaoComboResponse;
import com.juntamedica.acao.payload.AcaoCreateRequest;
import com.juntamedica.acao.payload.AcaoRequest;
import com.juntamedica.acao.payload.AcaoResponse;
import com.juntamedica.dominio.payload.DominioResponse;
import com.juntamedica.dominio.service.DominioService;
import com.juntamedica.permissao.service.PermissaoService;
import com.juntamedica.utils.exception.DatabaseConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AcaoServiceImpl implements AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private AcaoFactory acaoFactory;

    @Autowired
    private DominioService dominioService;

    @Autowired
    private PermissaoService permissaoService;

    @Override
    public void editAcaoOrdemEmTela(Long ordemEmTela, Long direcao) {
        permissaoService.hasPermission("menu_configuracoes.menu_acoes:view");

        Acao acaoOrdemAtual = acaoRepository.findByOrdemEmTela(ordemEmTela).orElseThrow(() -> new AcaoNotFoundException());
        Acao acaoOrdemNova = acaoRepository.findByOrdemEmTela(ordemEmTela + direcao).orElseThrow(() -> new AcaoNotFoundException());

        Long acaoOrdemAtualTemp = acaoOrdemAtual.getOrdemEmTela();
        Long acaoOrdemNovaTemp = acaoOrdemNova.getOrdemEmTela();

        acaoOrdemAtual.setOrdemEmTela(acaoOrdemNovaTemp);
        acaoOrdemNova.setOrdemEmTela(acaoOrdemAtualTemp);

        acaoRepository.saveAll(Arrays.asList(acaoOrdemAtual, acaoOrdemNova));
    }

    @Override
    public AcaoResponse save(AcaoCreateRequest acaoRequest) {
        permissaoService.hasPermission("menu_configuracoes.menu_acoes:view");

        validaDominioTelaId(acaoRequest.getDmModeloTelaId());

        Long lastScreenOrder = acaoRepository.findLastScreenOrder();
        acaoRequest.setOrderEmTela(lastScreenOrder + 1);

        Acao acao = acaoRepository.save(acaoFactory.build(acaoRequest));

        return acaoFactory.buildResponse(acao);
    }

    @Override
    public AcaoResponse edit(AcaoRequest acaoRequest) {
        permissaoService.hasPermission("menu_configuracoes.menu_acoes:view");

        Acao acao = acaoRepository.findById(acaoRequest.getId())
                .orElseThrow(() -> new AcaoNotFoundException(acaoRequest.getId()));

        validaDominioTelaId(acaoRequest.getDmModeloTelaId());

        validaExistenciaAcaoComMesmaOrdem(acaoRequest);

        acao = acaoRepository.save(acaoFactory.updateValues(acao, acaoRequest));

        return acaoFactory.buildResponse(acao);
    }

    @Override
    public void delete(Long acaoId) {
        permissaoService.hasPermission("menu_configuracoes.menu_acoes:view");

        Acao acaoOrdemAtual = acaoRepository.findById(acaoId).orElseThrow(() -> new AcaoNotFoundException(acaoId));
        try {
            acaoRepository.deleteById(acaoId);
        } catch (DataIntegrityViolationException e) {
            String constraintName = ((ConstraintViolationException) e.getCause())
                    .getConstraintName().replaceAll(".*\\.", "");

            String tableFK = "";
            switch (constraintName) {
                case "STATUS_ETAPA_ACAO_FK":
                    tableFK = "status e etapas";
                    break;
                default:
                    tableFK = "tabelas";
                    break;
            }

            String tableName = "ação";
            String mensagem = "Existem " + tableFK + " relacionados a esta " + tableName + ", não foi possível excluir!";

            throw new DatabaseConstraintViolationException(mensagem);
        }
        acaoRepository.updateOrdemEmTela(acaoOrdemAtual.getOrdemEmTela());
    }

    @Override
    public List<AcaoResponse> findAll() {
        return acaoFactory.buildResponseList(acaoRepository.findAll());
    }

    @Override
    public AcaoResponse findById(Long acaoId) {
        return acaoFactory.buildResponse(
                acaoRepository.findById(acaoId).orElseThrow(() -> new AcaoNotFoundException(acaoId)));
    }

    @Override
    public Acao findEntityById(Long acaoId) {
        return acaoRepository.findById(acaoId).orElseThrow(() -> new AcaoNotFoundException(acaoId));
    }

    @Override
    public Page<AcaoResponse> findAllWithFilters(AcaoRequest acaoRequest, Pageable pageable) {
        permissaoService.hasPermission("menu_configuracoes.menu_acoes:view");

        Page<Acao> acaoPage = acaoRepository.findByNomeStartingWithIgnoreCase(
                acaoRequest == null ? "" : acaoRequest.getNome(), pageable);

        return new PageImpl<>(
                acaoFactory.buildResponseList(acaoPage.getContent()), pageable, acaoPage.getTotalElements());
    }

    @Override
    public Page<AcaoComboResponse> findAllCombo(AcaoComboRequest acaoComboRequest, Pageable pageable) {
        String nome = acaoComboRequest != null ? acaoComboRequest.getNome() : null;

        return acaoRepository.findAllCombo(nome, pageable);
    }

    private void validaDominioTelaId(Long modeloTelaId) {
        List<DominioResponse> modelosTelas = dominioService.findByNome("dm_modelo_tela");
        Optional<DominioResponse> dominioExistente = modelosTelas.stream()
                .filter(item -> item.getId().equals(modeloTelaId)).findFirst();

        if (dominioExistente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Modelo de Tela inexistente.");
        }
    }

    private void validaExistenciaAcaoComMesmaOrdem(AcaoRequest acaoRequest) {
        Optional<Acao> acaoExistente =
                acaoRepository.findByOrdemEmTelaAndNotId(acaoRequest.getOrderEmTela(), acaoRequest.getId());

        if (acaoExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma Ação cadastrada com esta ordem.");
        }
    }
}
