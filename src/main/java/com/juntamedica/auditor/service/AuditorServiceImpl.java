package com.juntamedica.auditor.service;

import com.juntamedica.auditor.Auditor;
import com.juntamedica.auditor.AuditorRepository;
import com.juntamedica.auditor.exception.AuditorNotFoundException;
import com.juntamedica.auditor.exception.AuditorRestricaoDesempatadorException;
import com.juntamedica.auditor.factory.AuditorFactory;
import com.juntamedica.auditor.payload.AuditorComboRequest;
import com.juntamedica.auditor.payload.AuditorComboResponse;
import com.juntamedica.auditor.payload.AuditorFromRestricaoRequest;
import com.juntamedica.auditor.payload.AuditorRequest;
import com.juntamedica.auditor.payload.AuditorResponse;
import com.juntamedica.auditor.payload.AuditorRestricaoDesempatadorResponse;
import com.juntamedica.auditorconselho.AuditorConselho;
import com.juntamedica.auditorconselho.AuditorConselhoRepository;
import com.juntamedica.auditorconselho.payload.AuditorConselhoRequest;
import com.juntamedica.auditorconselho.payload.AuditorConselhoResponse;
import com.juntamedica.auditorespecialidade.payload.AuditorEspecialidadeRequest;
import com.juntamedica.cfm.service.CfmService;
import com.juntamedica.desempatador.Desempatador;
import com.juntamedica.dominio.Dominio;
import com.juntamedica.dominio.service.DominioService;
import com.juntamedica.permissao.service.PermissaoService;
import com.juntamedica.processo.Processo;
import com.juntamedica.usuario.UsuarioLogado;
import com.juntamedica.utils.SystemUtil;
import com.juntamedica.cfm.payload.CfmResponse;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuditorServiceImpl implements AuditorService {

    @Autowired
    private AuditorRepository auditorRepository;

    @Autowired
    private AuditorFactory auditorFactory;

    @Autowired
    private AuditorConselhoRepository auditorConselhoRepository;

    @Autowired
    private CfmService cfmService;

    @Autowired
    private DominioService dominioService;

    @Autowired
    private PermissaoService permissaoService;

    @Override
    public Auditor saveEntity(AuditorRequest auditorRequest) {
        List<String> validations = validateAuditor(auditorRequest);

        if (!validations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validations.stream().findFirst().get());
        }

        if (auditorRequest.getOperadoraId() == null) {
            UsuarioLogado usuario = SystemUtil.getCurrentUsuarioLogado();
            if (usuario.getOperadora() != null) {
                auditorRequest.setOperadoraId(usuario.getOperadora().getId());
            }
        }

        return auditorRepository.save(auditorFactory.build(auditorRequest));
    }

    @Override
    public AuditorResponse save(AuditorRequest auditorRequest) {
        permissaoService.hasPermission("menu_cadastros.menu_auditores:view");

        Auditor auditor = this.saveEntity(auditorRequest);

        return auditorFactory.buildResponse(auditor);
    }

    @Override
    public AuditorResponse saveFromRestricao(AuditorFromRestricaoRequest auditorRequest) {
        Auditor auditor = auditorRepository.save(auditorFactory.build(auditorRequest));

        return auditorFactory.buildResponse(auditor);
    }

    @Override
    public Auditor editEntity(AuditorRequest auditorRequest) {
        Auditor auditor = auditorRepository.findById(auditorRequest.getId())
                .orElseThrow(() -> new AuditorNotFoundException(auditorRequest.getId()));

        auditor = auditorFactory.updateValues(auditor, auditorRequest);
        return auditorRepository.save(auditor);
    }

    @Override
    public AuditorResponse edit(AuditorRequest auditorRequest) {
        permissaoService.hasPermission("menu_cadastros.menu_auditores:view");

        List<String> validations = validateAuditor(auditorRequest);

        if (!validations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validations.stream().findFirst().get());
        }

        if (auditorRequest.getOperadoraId() == null) {
            UsuarioLogado usuario = SystemUtil.getCurrentUsuarioLogado();
            if (usuario.getOperadora() != null) {
                auditorRequest.setOperadoraId(usuario.getOperadora().getId());
            }
        }

        Auditor auditor = this.editEntity(auditorRequest);

        return auditorFactory.buildResponse(auditor);
    }

    @Override
    public AuditorResponse findAuditorConselho(Long dmTipoConselhoId, String numeroConselho,
                                               String ufConselho, Long operadoraId) {
        Optional<AuditorConselho> auditorConselho =
                auditorConselhoRepository.findAuditorByConselho(dmTipoConselhoId, numeroConselho, ufConselho,
                                true, operadoraId);

        if (auditorConselho.isEmpty()) {
            auditorConselho = auditorConselhoRepository.findAuditorByConselho(dmTipoConselhoId, numeroConselho,
                    ufConselho,false, operadoraId);

            if (auditorConselho.isEmpty()) {
                auditorConselho = auditorConselhoRepository
                        .findAuditorByConselho(dmTipoConselhoId, numeroConselho, ufConselho, false, null);

                if (auditorConselho.isEmpty()) {
                    Dominio dominio = dominioService.findEntity(dmTipoConselhoId);
                    if (dominio.getValor().toUpperCase().compareTo("CRM") == 0) {
                        try {
                            Integer conselho = Integer.valueOf(numeroConselho);
                            CfmResponse cfmResponse = cfmService.findCfmByConselhoNumeroAndConselhoUf(conselho, ufConselho);
                            if (cfmResponse != null) {
                                return AuditorResponse.builder()
                                        .nome(cfmResponse.getNome())
                                        .conselhoList(Arrays.asList(AuditorConselhoResponse.builder()
                                                .numeroConselho(numeroConselho)
                                                .ufConselho(ufConselho)
                                                .dmTipoConselhoId(dmTipoConselhoId)
                                                .build()))
                                        .build();
                            }
                        } catch (Exception e) {
                            throw new AuditorNotFoundException();
                        }
                    }

                    throw new AuditorNotFoundException();
                }
            }
        }

        return auditorFactory.buildResponse(auditorConselho.get().getAuditor());
    }

    @Override
    public Page<AuditorResponse> findAll(AuditorRequest auditorRequest, Pageable pageable) {
        Page<Auditor> auditorPage = auditorRepository.findByNome(
                auditorRequest == null ? "" : auditorRequest.getNome(), pageable);

        return new PageImpl<>(
                auditorFactory.buildResponseList(auditorPage.getContent()), pageable, auditorPage.getTotalElements());
    }

    @Override
    public Page<AuditorResponse> findAllByNomeOrConselhoNumero(AuditorComboRequest auditorComboRequest,
                                                               Pageable pageable) {
        String term = auditorComboRequest == null ? null : auditorComboRequest.getTerm();
        Long auditorId = auditorComboRequest == null ? null : auditorComboRequest.getSelectedAuditorId();

        Page<Auditor> auditorPage = auditorRepository.findAllByNomeOrConselhoNumeroOrAuditorId(term, auditorId, pageable);

        return new PageImpl<>(
                auditorFactory.buildResponseList(auditorPage.getContent()), pageable, auditorPage.getTotalElements());
    }

    @Override
    public List<AuditorComboResponse> findAllByNomeOrConselhoNumeroAndPreCadastro(AuditorComboRequest auditorComboRequest) {
        String term = auditorComboRequest == null ? null : auditorComboRequest.getTerm();
        Boolean preCadastro = auditorComboRequest != null && auditorComboRequest.getPreCadastro();

        return auditorRepository.findAllByNomeOrConselhoNumeroAndPreCadastro(term, preCadastro);
    }

    @Override
    public Page<AuditorResponse> findAllGrid(AuditorRequest auditorRequest, Pageable pageable) {
        permissaoService.hasPermission("menu_cadastros.menu_auditores:view");

        String nome = null;
        Long operadoraId = null;
        Boolean preCadastro = null;

        if (Objects.nonNull(auditorRequest)) {
            nome = auditorRequest.getNome();
            operadoraId = auditorRequest.getOperadoraId();
            preCadastro = auditorRequest.getPreCadastro();
        }

        Page<Auditor> auditorPage = auditorRepository.findAllGrid(nome, operadoraId, preCadastro, pageable);

        return new PageImpl<>(
                auditorFactory.buildResponseList(auditorPage.getContent()), pageable, auditorPage.getTotalElements());
    }

    @Override
    public Auditor findEntity(Long id) {
        return auditorRepository.findById(id).orElseThrow(() -> new AuditorNotFoundException(id));
    }

    @Override
    public AuditorResponse findById(Long id) {
        Auditor auditor = auditorRepository.findById(id).orElseThrow(() -> new AuditorNotFoundException(id));

        return auditorFactory.buildResponse(auditor);
    }

    private List<String> validateAuditor(AuditorRequest auditorRequest) {
        List<String> listValidations = new ArrayList<>();

        UsuarioLogado usuario = SystemUtil.getCurrentUsuarioLogado();

        if (usuario != null) {
            if (auditorRequest.getOperadoraId() == null && usuario.getOperadora() == null) {
                listValidations.add("O ID da Operadora é obrigatorio.");
            }
        }

        if (auditorRequest.getNome() == null || auditorRequest.getNome().equals("")) {
            listValidations.add("O nome do Auditor é obrigatório.");
        }

        if (auditorRequest.getConselhoList() == null) {
            listValidations.add("Conselho do Auditor deve ser informado.");
        } else {
            if (auditorRequest.getConselhoList().isEmpty()) {
                listValidations.add("Conselho do Auditor deve ser informado.");
            } else {
                for (AuditorConselhoRequest conselho : auditorRequest.getConselhoList()) {
                    if (conselho.getDmTipoConselhoId() == null) {
                        listValidations.add("Tipo do conselho é obrigatório.");
                    } else if (conselho.getNumeroConselho() == null || conselho.getNumeroConselho().equals("")) {
                        listValidations.add("Número do conselho é obrigatório.");
                    } else if (conselho.getUfConselho() == null || conselho.getUfConselho().equals("")) {
                        listValidations.add("UF do conselho é obrigatório.");
                    }
                }
            }
        }

        if (auditorRequest.getEspecialidadeList() == null) {
            listValidations.add("Especialidade do Auditor é obrigatório.");
        } else {
            if (auditorRequest.getEspecialidadeList().isEmpty()) {
                listValidations.add("Especialidade do Auditor é obrigatório.");
            } else {
                for (AuditorEspecialidadeRequest especialidade : auditorRequest.getEspecialidadeList()) {
                    if (especialidade.getEspecialidadeId() == null) {
                        listValidations.add("Código da especialidade do Auditor é obrigatório.");
                    }
                }
            }
        }

        return listValidations;
    }

    @Override
    public Auditor editValuesByInfoEdit(Processo processo, AuditorRequest auditorRequest) {
        this.checkAuditorHasDesempatadorRestricao(processo, auditorRequest);

        Auditor auditor = processo.getAuditor();
        auditor = auditorFactory.updateValuesByInfoEdit(auditor, auditorRequest);

        return auditorRepository.save(auditor);
    }

    @Override
    public void delete(Long id) {
        try {
            auditorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            String constraintName = ((ConstraintViolationException) e.getCause())
                    .getConstraintName().replaceAll(".*\\.", "");

            String tableFK = "";
            switch (constraintName) {
                case "PROCESSO_AUDITOR_FK":
                    tableFK = "processos";
                    break;
                case "DESEMPATADOR_RESTRICAO_AUDITOR_ID_FK":
                    tableFK = "desempatadores restrições";
                default:
                    tableFK = "tabelas";
                    break;
            }

            String tableName = "auditor";
            String mensagem = "Existem " + tableFK + " relacionados a este " + tableName + ", não foi possível excluir!";

            throw new DatabaseConstraintViolationException(mensagem);
        }
    }

    private void checkAuditorHasDesempatadorRestricao(Processo processo, AuditorRequest auditorRequest) {
        Desempatador desempatador = processo.getDesempatador();

        if (Objects.isNull(desempatador)
                || Objects.isNull(auditorRequest.getConselhoList())
                || Objects.isNull(auditorRequest.getNovoConselho())) {
            return;
        }

        List<AuditorConselhoRequest> conselhos = auditorRequest.getConselhoList();

        if (conselhos.isEmpty()) {
            return;
        }

        for (AuditorConselhoRequest conselho : conselhos) {
            String crm = conselho.getNumeroConselho();
            String uf = conselho.getUfConselho();

            List<AuditorRestricaoDesempatadorResponse> result =
                    auditorRepository.findAllByRestricaoDesempatador(desempatador.getId(), crm, uf);
            boolean hasRestricao = !result.isEmpty();

            if (hasRestricao) {
                throw new AuditorRestricaoDesempatadorException();
            }
        }
    }
}
