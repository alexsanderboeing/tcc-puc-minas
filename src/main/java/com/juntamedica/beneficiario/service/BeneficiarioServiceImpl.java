package com.juntamedica.beneficiario.service;

import com.juntamedica.anexo.Anexo;
import com.juntamedica.anexo.factory.AnexoFactory;
import com.juntamedica.anexo.payload.AnexoRequest;
import com.juntamedica.anexo.service.AnexoService;
import com.juntamedica.beneficiario.Beneficiario;
import com.juntamedica.beneficiario.BeneficiarioRepository;
import com.juntamedica.beneficiario.exception.BeneficiarioNotFoundException;
import com.juntamedica.beneficiario.factory.BeneficiarioFactory;
import com.juntamedica.beneficiario.payload.BeneficiarioComboResponse;
import com.juntamedica.beneficiario.payload.BeneficiarioGridResponse;
import com.juntamedica.beneficiario.payload.BeneficiarioRequest;
import com.juntamedica.beneficiario.payload.BeneficiarioResponse;
import com.juntamedica.permissao.service.PermissaoService;
import com.juntamedica.usuario.UsuarioLogado;
import com.juntamedica.utils.SystemUtil;
import com.juntamedica.utils.exception.DatabaseConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BeneficiarioServiceImpl implements BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private BeneficiarioFactory beneficiarioFactory;

    @Autowired
    private AnexoService anexoService;

    @Autowired
    private AnexoFactory anexoFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PermissaoService permissaoService;

    @Override
    public Beneficiario saveEntity(BeneficiarioRequest beneficiarioRequest) {
        return beneficiarioRepository.save(beneficiarioFactory.build(beneficiarioRequest));
    }

    @Override
    @Transactional
    public BeneficiarioResponse save(BeneficiarioRequest beneficiarioRequest) {
        permissaoService.hasPermission("menu_cadastros.menu_beneficiarios:view");

        List<String> validations = validateBeneficiario(beneficiarioRequest);

        if (!validations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validations.stream().findFirst().get());
        }

        if (beneficiarioRequest.getOperadoraId() == null) {
            UsuarioLogado usuario = SystemUtil.getCurrentUsuarioLogado();
            if (usuario != null && usuario.getOperadora() != null) {
                beneficiarioRequest.setOperadoraId(usuario.getOperadora().getId());
            }
        }
        Beneficiario beneficiario = beneficiarioFactory.build(beneficiarioRequest);
        beneficiario = saveBeneficiarioChildRegistrations(beneficiario, beneficiarioRequest);

        return beneficiarioFactory.buildResponse(beneficiarioRepository.save(beneficiario), false);
    }

    @Transactional
    @Override
    public Beneficiario editEntity(BeneficiarioRequest beneficiarioRequest) {
        Beneficiario beneficiario = beneficiarioRepository.findById(beneficiarioRequest.getId())
                .orElseThrow(() -> new BeneficiarioNotFoundException(beneficiarioRequest.getId()));

        beneficiario = beneficiarioFactory.updateValues(beneficiario, beneficiarioRequest);
        beneficiario = saveBeneficiarioChildRegistrations(beneficiario, beneficiarioRequest);

        return beneficiarioRepository.save(beneficiario);
    }

    @Override
    public BeneficiarioResponse edit(BeneficiarioRequest beneficiarioRequest) {
        permissaoService.hasPermission("menu_cadastros.menu_beneficiarios:view");

        List<String> validations = validateBeneficiario(beneficiarioRequest);

        if (!validations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validations.stream().findFirst().get());
        }

        if (beneficiarioRequest.getOperadoraId() == null) {
            UsuarioLogado usuario = SystemUtil.getCurrentUsuarioLogado();
            if (usuario.getOperadora() != null) {
                beneficiarioRequest.setOperadoraId(usuario.getOperadora().getId());
            }
        }

        Beneficiario beneficiario = this.editEntity(beneficiarioRequest);

        return beneficiarioFactory.buildResponse(beneficiario, false);
    }

    @Override
    public Page<BeneficiarioComboResponse> findAllCombo(BeneficiarioRequest beneficiarioRequest, Pageable pageable) {
        Page<BeneficiarioComboResponse> beneficiarioPage = beneficiarioRepository
                .findWithFiltersCombo(beneficiarioRequest.getNome(), beneficiarioRequest.getOperadoraId(), pageable);

        return new PageImpl<>(beneficiarioPage.getContent(),
                pageable,
                beneficiarioPage.getTotalElements());
    }

    private String createBeneficiarioHql(BeneficiarioRequest beneficiarioRequest, Boolean isCount) {
        String hql = "select " + (isCount ? "count(b.id)" : "b") + " from Beneficiario b";
        String where = "";

        if (beneficiarioRequest != null) {
            if (beneficiarioRequest.getCodigo() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "b.codigo like '%" + beneficiarioRequest.getCodigo().trim() + "%'";
            }

            if (beneficiarioRequest.getPreCadastro() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "b.preCadastro = " + beneficiarioRequest.getPreCadastro();
            }

            if (beneficiarioRequest.getNome() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "b.codigo || ' - ' || b.nome like '%" + beneficiarioRequest.getNome().trim() +"%'";
            }

            if (beneficiarioRequest.getOperadoraId() != null) {
                where += where.length() > 0 ? " and " : " ";
                where += "b.operadora.id = " + beneficiarioRequest.getOperadoraId();
            }
        }

        where = where.length() > 0 ? " where " + where : "";
        hql += where;

        return hql;
    }

    @Override
    public BeneficiarioResponse findByCodigoAndPreCadastro(String codigo, Long operadoraId) {
        Beneficiario beneficiario =
                beneficiarioRepository.findBeneficiarioByCodigoAndOperadora(codigo, operadoraId, true);

        if (beneficiario == null) {
            beneficiario = beneficiarioRepository.findBeneficiarioByCodigoAndOperadora(codigo, operadoraId, false);

            if (beneficiario == null) {
                throw new BeneficiarioNotFoundException();
            }
        }

        return beneficiarioFactory.buildResponse(beneficiario, false);
    }

    @Override
    public BeneficiarioResponse find(Long id) {
        Beneficiario beneficiario = beneficiarioRepository.findById(id)
                .orElseThrow(() -> new BeneficiarioNotFoundException(id));

        return beneficiarioFactory.buildResponse(beneficiario, false);
    }

    @Override
    public Beneficiario findEntity(Long id) {
        return beneficiarioRepository.findById(id).orElseThrow(() -> new BeneficiarioNotFoundException(id));
    }

    @Override
    public Page<BeneficiarioComboResponse> findAllComboPreCadastro(BeneficiarioRequest beneficiarioRequest,
                                                                   Pageable pageable) {
        String searchTerm = null;
        Long operadoraId = null;

        if (!Objects.isNull(beneficiarioRequest)) {
            searchTerm = beneficiarioRequest.getSearchTerm();
            operadoraId = beneficiarioRequest.getOperadoraId();
        }

        Page<BeneficiarioComboResponse> beneficiarioPage = beneficiarioRepository
                .findWithFiltersComboPreCadastro(searchTerm, operadoraId, pageable);

        return new PageImpl<>(beneficiarioPage.getContent(),
                pageable,
                beneficiarioPage.getTotalElements());
    }

    @Override
    public void deletePreCadastro(Long beneficiarioId) {
        permissaoService.hasPermission("menu_cadastros.menu_beneficiarios:view");

        beneficiarioRepository.findByIdAndPreCadastro(beneficiarioId, true)
                .orElseThrow(() -> new BeneficiarioNotFoundException());

        try {
            beneficiarioRepository.deleteById(beneficiarioId);
        } catch (DataIntegrityViolationException e) {
            String constraintName = ((ConstraintViolationException) e.getCause())
                    .getConstraintName().replaceAll(".*\\.", "");

            String tableFK = "";
            switch (constraintName) {
                case "PROCESSO_BENEFICIARIO_FK":
                    tableFK = "processos";
                    break;
                default:
                    tableFK = "tabelas";
                    break;
            }

            String tableName = "beneficiário";
            String mensagem = "Existem " + tableFK + " relacionados a este " + tableName + ", não foi possível excluir!";

            throw new DatabaseConstraintViolationException(mensagem);
        }
    }

    @Override
    public Page<BeneficiarioGridResponse> findAllGridPreCadastro(BeneficiarioRequest beneficiarioRequest,
                                                                 Pageable pageable) {
        permissaoService.hasPermission("menu_cadastros.menu_beneficiarios:view");

        String nome = null;
        String codigo = null;
        Long operadoraId = null;

        if (!Objects.isNull(beneficiarioRequest)) {
            nome = beneficiarioRequest.getNome();
            codigo = beneficiarioRequest.getCodigo();
            operadoraId = beneficiarioRequest.getOperadoraId();
        }

        Page<BeneficiarioGridResponse> beneficiarioPage = beneficiarioRepository
                .findWithFiltersGridPreCadastro(nome, codigo, operadoraId, pageable);

        return new PageImpl<>(beneficiarioPage.getContent(),
                pageable,
                beneficiarioPage.getTotalElements());
    }

    @Override
    public BeneficiarioResponse findByIdAndPreCadastro(Long beneficiarioId) {
        Beneficiario beneficiario = beneficiarioRepository.findByIdAndPreCadastro(beneficiarioId, true)
                .orElseThrow(() -> new BeneficiarioNotFoundException());

        return beneficiarioFactory.buildResponse(beneficiario, false);
    }

    @Transactional
    private Beneficiario saveBeneficiarioChildRegistrations(Beneficiario beneficiario,
                                                        BeneficiarioRequest beneficiarioRequest) {
        List<AnexoRequest> anexoList = beneficiarioRequest.getAnexoList();
        if (beneficiario.getAnexoList() == null || beneficiario.getAnexoList().isEmpty()) {
            if (anexoList != null && !anexoList.isEmpty()) {
                List<Anexo> newAnexoList = anexoService.saveAllEntities(beneficiarioRequest.getAnexoList());
                beneficiario.mergeAnexoList(newAnexoList);
            }
        } else {
            if (anexoList != null && !anexoList.isEmpty()) {
                this.mergeAnexosList(beneficiario, beneficiarioRequest.getAnexoList());
            } else {
                beneficiario.setAnexoList(Collections.emptyList());
            }
        }

        return beneficiario;
    }

    private void mergeAnexosList(Beneficiario beneficiario, List<AnexoRequest> anexoRequestList) {
        List<AnexoRequest> deletados = anexoRequestList.stream()
                .filter(AnexoRequest::getDeleted).collect(Collectors.toList());
        List<AnexoRequest> existentes = anexoRequestList.stream()
                .filter(anexoRequest -> !anexoRequest.getIsNewFile() && !anexoRequest.getDeleted())
                .collect(Collectors.toList());
        List<AnexoRequest> novos = anexoRequestList.stream()
                .filter(AnexoRequest::getIsNewFile)
                .collect(Collectors.toList());

        if (!deletados.isEmpty()) {
            anexoService.deleteStorage(anexoFactory.buildList(deletados));
        }

        List<Anexo> merged = new ArrayList<>(anexoFactory.buildList(existentes));

        if (novos != null && !novos.isEmpty()) {
            novos = anexoService.uploadAnexosToStorage(novos);
            merged.addAll(anexoFactory.buildList(novos));
        }

        beneficiario.mergeAnexoList(merged);
    }

    private List<String> validateBeneficiario(BeneficiarioRequest beneficiarioRequest) {
        List<String> listValidations = new ArrayList<>();

        UsuarioLogado usuario = SystemUtil.getCurrentUsuarioLogado();

        if (usuario != null ) {
            if (beneficiarioRequest.getOperadoraId() == null && usuario.getOperadora() == null) {
                listValidations.add("O ID da Operadora é obrigatorio.");
            }
        }

        if (beneficiarioRequest.getCodigo() == null) {
            listValidations.add("Código não pode ser nulo.");
        }

        if (beneficiarioRequest.getNome() == null || beneficiarioRequest.getNome().equals("")) {
            listValidations.add("Nome não pode ser nulo.");
        }

        if (beneficiarioRequest.getDmSexoBeneficiarioId() == null) {
            listValidations.add("Sexo não pode ser nulo.");
        }

        if (beneficiarioRequest.getDataNascimento() == null) {
            listValidations.add("Data de Nascimento não pode ser nula.");
        }

        return listValidations;
    }

    @Override
    @Transactional
    public Beneficiario editValuesByInfoEdit(Beneficiario beneficiario, BeneficiarioRequest beneficiarioRequest) {
        beneficiario = beneficiarioFactory.updateValuesByInfoEdit(beneficiario, beneficiarioRequest);

        return beneficiarioRepository.save(beneficiario);
    }
}
