package com.juntamedica.usuario.service;

import com.juntamedica.papelperfil.exception.PapelPerfilNotFoundException;
import com.juntamedica.papelperfil.service.PapelPerfilService;
import com.juntamedica.permissao.service.PermissaoService;
import com.juntamedica.usuario.Usuario;
import com.juntamedica.usuario.UsuarioRepository;
import com.juntamedica.usuario.exception.UsuarioAlreadyExistException;
import com.juntamedica.usuario.exception.UsuarioMismatchPasswordException;
import com.juntamedica.usuario.exception.UsuarioNotFoundException;
import com.juntamedica.usuario.factory.UsuarioFactory;
import com.juntamedica.usuario.payload.UsuarioGridFilterRequest;
import com.juntamedica.usuario.payload.UsuarioGridResponse;
import com.juntamedica.usuario.payload.UsuarioLogadoResponse;
import com.juntamedica.usuario.payload.UsuarioNewPasswordRequest;
import com.juntamedica.usuario.payload.UsuarioRequest;
import com.juntamedica.usuario.payload.UsuarioResetPasswordRequest;
import com.juntamedica.usuario.payload.UsuarioResponse;
import com.juntamedica.usuario.payload.UsuarioValidatePasswordRequest;
import com.juntamedica.usuarioperfil.payload.UsuarioPerfilResponse;
import com.juntamedica.usuarioperfil.service.UsuarioPerfilService;
import com.juntamedica.usuariorecovery.UsuarioRecovery;
import com.juntamedica.usuariorecovery.service.UsuarioRecoveryService;
import com.juntamedica.utils.SystemUtil;
import com.juntamedica.utils.exception.DatabaseConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private PapelPerfilService papelPerfilService;

    @Autowired
    private UsuarioPerfilService usuarioPerfilService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioFactory usuarioFactory;

    @Autowired
    private Argon2PasswordEncoder argon2PasswordEncoder;

    @Autowired
    private UsuarioRecoveryService usuarioRecoveryService;

    @Autowired
    private PermissaoService permissaoService;

    private Boolean isPasswordMatches(String sentPassword, String storedPassword) {
        return argon2PasswordEncoder.matches(sentPassword, storedPassword);
    }

    @Override
    public UsuarioLogadoResponse findUserInformationByToken() {
        return usuarioFactory.buildLogadoResponse();
    }

    public void generateMessageDataIntegrityViolationException(DataIntegrityViolationException e) {
        String constraintName = ((ConstraintViolationException) e.getCause())
                .getConstraintName().replaceAll(".*\\.", "");

        String tableFK = "";
        switch (constraintName) {
            case "USUARIO_RESET_TOKEN_USUARIO_ID_FK":
            case "USUARIO_RESET_TOKEN_CREATED_BY_FK":
            case "USUARIO_RESET_TOKEN_UPDATED_BY_FK":
                tableFK = "usuarios reset token";
                break;
            case "USUARIO_PERFIL_USARIO_ID_PERFIL_ID_UK":
            case "USUARIO_PERFIL_PERFIL_ID_FK":
            case "USUARIO_PERFIL_USUARIO_ID_FK":
            case "USUARIO_PERFIL_CREATED_BY_FK":
            case "USUARIO_PERFIL_UPDATED_BY_FK":
                tableFK = "usuario perfil";
                break;
            case "USUARIO_RECOVERY_FK":
                tableFK = "usuario recovery";
                break;
            case "FAILED_LOGIN_USUARIO_ID_FK":
                tableFK = "failed login";
                break;
            case "OPERADORA_USUARIO_PK":
                tableFK = "operadora usuário";
                break;
            case "PAPEL_PERFIL_PERFIL_ID_FK":
            case "PAPEL_PERFIL_CREATED_BY_FK":
            case "PAPEL_PERFIL_UPDATED_BY_FK":
                tableFK = "papel perfil";
                break;
            case "PERFIL_GRID_USUARIO_ID_FK":
            case "PERFIL_GRID_CREATED_BY_FK":
            case "PERFIL_GRID_UPDATED_BY_FK":
                tableFK = "perfil grid";
                break;
            case "PERFIL_PERMISSAO_CREATED_BY_FK":
            case "PERFIL_PERMISSAO_UPDATED_BY_FK":
                tableFK = "perfil permissão";
                break;
            case "PAPEL_CREATED_BY_FK":
            case "PAPEL_UPDATED_BY_FK":
                tableFK = "papel";
                break;
            case "DOMINIO_CREATED_BY_FK":
            case "DOMINIO_UPDATED_BY_FK":
                tableFK = "dominio";
                break;
            case "PARAMETRO_CREATED_BY_FK":
            case "PARAMETRO_UPDATED_BY_FK":
                tableFK = "parametro";
                break;
            case "CONTATO_CREATED_BY_FK":
            case "CONTATO_UPDATED_BY_FK":
                tableFK = "contato";
                break;
            case "DESEMPATADOR_USUARIO_ID_FK":
            case "DESEMPATADOR_CREATED_BY_FK":
            case "DESEMPATADOR_UPDATED_BY_FK":
                tableFK = "desempatador";
                break;
            case "DESEMPATADOR_FAVORITO_CREATED_BY_FK":
            case "DESEMPATADOR_FAVORITO_UPDATED_BY_FK":
                tableFK = "desempatador favorito";
                break;
            case "DESEMPATADOR_PRECO_CREATED_BY_FK":
            case "DESEMPATADOR_PRECO_UPDATED_BY_FK":
                tableFK = "desempatador preço";
                break;
            case "DESEMPATADOR_CBOS_CREATED_BY_FK":
            case "DESEMPATADOR_CBOS_UPDATED_BY_FK":
                tableFK = "desempatador cbos";
                break;
            case "DESEMPATADOR_CONSELHO_CREATED_BY_FK":
            case "DESEMPATADOR_CONSELHO_UPDATED_BY_FK":
                tableFK = "desempatador conselho";
                break;
            case "EQUIPE_CREATED_BY_FK":
            case "EQUIPE_UPDATED_BY_FK":
                tableFK = "equipe";
                break;
            case "EQUIPE_MEMBRO_USUARIO_EXT_ID_FK":
            case "EQUIPE_MEMBRO_CREATED_BY_FK":
            case "EQUIPE_MEMBRO_UPDATED_BY_FK":
                tableFK = "equipe membro";
                break;
            case "EQUIPE_MEMBRO_INATIVIDADE_CREATED_BY_FK":
            case "EQUIPE_MEMBRO_INATIVIDADE_UPDATED_BY_FK":
                tableFK = "equipe membro inatividade";
                break;
            case "STATUS_ETAPA_CREATED_BY_FK":
            case "STATUS_ETAPA_UPDATED_BY_FK":
                tableFK = "status etapa";
                break;
            case "STATUS_ETAPA_ACAO_CREATED_BY_FK":
            case "STATUS_ETAPA_ACAO_UPDATED_BY_FK":
                tableFK = "status etapa ação";
                break;
            case "STATUS_ETAPA_ACAO_NOTIF_CREATED_BY_FK":
            case "STATUS_ETAPA_ACAO_NOTIF_UPDATED_BY_FK":
                tableFK = "status etapa ação notificação";
                break;
            case "STATUS_ETAPA_ACAO_PERFIL_PERFIL_ID_FK":
            case "STATUS_ETAPA_ACAO_PERFIL_CREATED_BY_FK":
            case "STATUS_ETAPA_ACAO_PERFIL_UPDATED_BY_FK":
                tableFK = "status etapa ação perfil";
                break;
            case "CALENDARIO_CREATED_BY_FK":
            case "CALENDARIO_UPDATED_BY_FK":
                tableFK = "calendário";
                break;
            case "CALENDARIO_FERIADOS_CREATED_BY_FK":
            case "CALENDARIO_FERIADOS_UPDATED_BY_FK":
                tableFK = "calendário feriado";
                break;
            case "CALENDARIO_DIAS_HORAS_CREATED_BY_FK":
            case "CALENDARIO_DIAS_HORAS_UPDATED_BY_FK":
                tableFK = "calendário dias horas";
                break;
            case "CONSULTOR_CREATED_BY_FK":
            case "CONSULTOR_UPDATED_BY_FK":
                tableFK = "consultor";
                break;
            case "CONSULTOR_CONSELHO_CREATED_BY_FK":
            case "CONSULTOR_CONSELHO_UPDATED_BY_FK":
                tableFK = "consultor conselho";
                break;
            case "CONSULTOR_CBOS_CREATED_BY_FK":
            case "CONSULTOR_CBOS_UPDATED_BY_FK":
                tableFK = "consultor cbos";
                break;
            case "AUDITOR_CREATED_BY_FK":
            case "AUDITOR_UPDATED_BY_FK":
                tableFK = "auditor";
                break;
            case "AUDITOR_CBOS_CREATED_BY_FK":
            case "AUDITOR_CBOS_UPDATED_BY_FK":
                tableFK = "auditor cbos";
                break;
            case "AUDITOR_CONSELHO_CREATED_BY_FK":
            case "AUDITOR_CONSELHO_UPDATED_BY_FK":
                tableFK = "auditor conselho";
                break;
            case "BENEFICIARIO_CREATED_BY_FK":
            case "BENEFICIARIO_UPDATED_BY_FK":
                tableFK = "beneficiário";
                break;
            case "BENEFICIARIO_ANEXO_CREATED_BY_FK":
            case "BENEFICIARIO_ANEXO_UPDATED_BY_FK":
                tableFK = "beneficiário anexo";
                break;
            case "PROCESSO_ADMINISTRATIVO_FESC_EXT_ID_FK":
            case "PROCESSO_AUDITOR_QUALIDADE_EXT_ID_FK":
            case "PROCESSO_ADMINISTRATIVO_OPERADORA_ID_FK":
            case "PROCESSO_CREATED_BY_FK":
            case "PROCESSO_UPDATED_BY_FK":
                tableFK = "processo";
                break;
            case "PROCESSO_HIST_NOTIFICACAO_CREATED_BY_FK":
            case "PROCESSO_HIST_NOTIFICACAO_UPDATED_BY_FK":
                tableFK = "processo histórico notificação";
                break;
            case "PROCESSO_DESEMPATADORES_CREATED_BY_FK":
            case "PROCESSO_DESEMPATADORES_UPDATED_BY_FK":
                tableFK = "processo desempatador";
                break;
            case "PROCESSO_ETAPA_CREATED_BY_FK":
            case "PROCESSO_ETAPA_UPDATED_BY_FK":
                tableFK = "processo etapa";
                break;
            case "PROCESSO_ANEXO_CREATED_BY_FK":
            case "PROCESSO_ANEXO_UPDATED_BY_FK":
                tableFK = "processo anexo";
                break;
            case "PROCESSO_HISTORICO_CREATED_BY_FK":
            case "PROCESSO_HISTORICO_UPDATED_BY_FK":
                tableFK = "processo histórico";
                break;
            case "PROCESSO_ITEM_CREATED_BY_FK":
            case "PROCESSO_ITEM_UPDATED_BY_FK":
                tableFK = "processo item";
                break;
            case "FLUXO_PROCESSO_CREATED_BY_FK":
            case "FLUXO_PROCESSO_UPDATED_BY_FK":
                tableFK = "fluxo processo";
                break;
            case "CONTRATO_FLUXO_PROCESSO_CREATED_BY_FK":
            case "CONTRATO_FLUXO_PROCESSO_UPDATED_BY_FK":
                tableFK = "contrato fluxo processo";
                break;
            case "ANEXO_USUARIO_ID_FK":
            case "ANEXO_CREATED_BY_FK":
            case "ANEXO_UPDATED_BY_FK":
                tableFK = "anexo";
                break;
            case "CLASSIFICACAO_ANEXO_CREATED_BY_FK":
            case "CLASSIFICACAO_ANEXO_UPDATED_BY_FK":
                tableFK = "classificação anexo";
                break;
            case "PROFISSIONAL_ASSISTENTE_CREATED_BY_FK":
            case "PROFISSIONAL_ASSISTENTE_UPDATED_BY_FK":
                tableFK = "profissional assistente";
                break;
            case "PROFISSIONAL_ASSIS_ANEXO_CREATED_BY_FK":
            case "PROFISSIONAL_ASSIS_ANEXO_UPDATED_BY_FK":
                tableFK = "profissional assistente anexo";
                break;
            case "PROFISSIONAL_ASSIST_CONSELHO_CREATED_BY_FK":
            case "PROFISSIONAL_ASSIST_CONSELHO_UPDATED_BY_FK":
                tableFK = "profissional assistente conselho";
                break;
            case "PROFISSIONAL_ASSIS_CBOS_CREATED_BY_FK":
            case "PROFISSIONAL_ASSIS_CBOS_UPDATED_BY_FK":
                tableFK = "profissional assistente cbos";
                break;
            case "CONTRATO_PRECO_CREATED_BY_FK":
            case "CONTRATO_PRECO_UPDATED_BY_FK":
                tableFK = "contrato preço";
                break;
            case "CONTRATO_ADITIVO_CREATED_BY_FK":
            case "CONTRATO_ADITIVO_UPDATED_BY_FK":
                tableFK = "contrato aditivo";
                break;
            case "CONTRATO_OPERADORAS_CREATED_BY_FK":
            case "CONTRATO_OPERADORAS_UPDATED_BY_FK":
                tableFK = "contrato operadora";
                break;
            case "NOTIFICACAO_CREATED_BY_FK":
            case "NOTIFICACAO_UPDATED_BY_FK":
                tableFK = "notificacao";
                break;
            case "ENDERECO_CREATED_BY_FK":
            case "ENDERECO_UPDATED_BY_FK":
                tableFK = "endereco";
                break;
            case "CHAT_MENSAGEM_CREATED_BY_FK":
            case "CHAT_MENSAGEM_UPDATED_BY_FK":
                tableFK = "chat mensagem";
                break;
            case "CHAT_MENSAGEM_LIDA_USUARIO_EXT_ID_FK":
            case "CHAT_MENSAGEM_LIDA_CREATED_BY_FK":
            case "CHAT_MENSAGEM_LIDA_UPDATED_BY_FK":
                tableFK = "chat mensagem lida";
                break;
            case "MODELO_CARTA_UPDATED_BY_FK":
            case "MODELO_CARTA_CREATED_BY_FK":
                tableFK = "modelo carta";
                break;
            case "MODELO_CARTA_NOTIFICACAO_CREATED_BY_FK":
            case "MODELO_CARTA_NOTIFICACAO_UPDATED_BY_FK":
                tableFK = "modelo carta notificação";
                break;
            case "OPERADORA_CREATED_BY_FK":
            case "OPERADORA_UPDATED_BY_FK":
                tableFK = "operadora";
                break;
            case "OPERADORA_USUARIO_USUARIO_EXT_ID_FK":
            case "OPERADORA_USUARIO_CREATED_BY_FK":
            case "OPERADORA_USUARIO_UPDATED_BY_FK":
                tableFK = "operadora usuário";
                break;
            case "ACAO_CREATED_BY_FK":
            case "ACAO_UPDATED_BY_FK":
                tableFK = "ação";
                break;
            case "CLASSIFICACAO_INTERNA_CREATED_BY_FK":
            case "CLASSIFICACAO_INTERNA_UPDATED_BY_FK":
                tableFK = "classificação interna";
                break;
            case "ALERTA_USUARIO_FK":
                tableFK = "alertas";
                break;
            default:
                tableFK = "tabelas";
                break;
        }

        String tableName = "usuário";
        String mensagem = "Existem " + tableFK + " relacionados a esta " + tableName + ", não foi possível excluir!";

        throw new DatabaseConstraintViolationException(mensagem);
    }

    @Override
    public void newPassword(UsuarioNewPasswordRequest usuarioNewPasswordRequest) {
        UsuarioRecovery usuarioRecovery = usuarioRecoveryService.validateUuid(usuarioNewPasswordRequest.getUuid());

        if(usuarioNewPasswordRequest.getNewPassword().equals(usuarioNewPasswordRequest.getConfirmNewPassword())) {
            Usuario usuario = usuarioRecovery.getUsuario();
            usuario.setPassword(argon2PasswordEncoder.encode(usuarioNewPasswordRequest.getNewPassword()));
            usuarioRepository.save(usuario);
            usuarioRecoveryService.delete(usuarioRecovery);
        } else {
            throw new UsuarioMismatchPasswordException();
        }

    }

    @Override
    public void resetPassword(UsuarioResetPasswordRequest usuarioResetPasswordRequest) {
        Usuario usuario = usuarioRepository.findById(SystemUtil.getCurrentUsuarioLogado().getId())
                .orElseThrow(() -> new UsuarioNotFoundException());

        if (this.isPasswordMatches(usuarioResetPasswordRequest.getPassword(), usuario.getPassword())) {
           if (usuarioResetPasswordRequest.getNewPassword().equals(usuarioResetPasswordRequest.getConfirmNewPassword())) {
               usuario.setPassword(argon2PasswordEncoder.encode(usuarioResetPasswordRequest.getNewPassword()));
               usuarioRepository.save(usuario);
           } else {
               throw new UsuarioMismatchPasswordException();
           }
        } else {
            throw new UsuarioMismatchPasswordException();
        }
    }

    @Override
    public UsuarioResponse save(UsuarioRequest usuarioRequest) {
        permissaoService.hasPermission("usuario:create");
        if (Objects.nonNull(usuarioRepository.findByEmail(usuarioRequest.getEmail()))) {
            throw new UsuarioAlreadyExistException(usuarioRequest.getEmail());
        }

        if (Objects.nonNull(usuarioRepository.findByUsername(usuarioRequest.getUsername()))) {
            throw new UsuarioAlreadyExistException(usuarioRequest.getUsername());
        }

        Usuario usuario = usuarioRepository.save(usuarioFactory.build(usuarioRequest));

        LOGGER.info("Criado novo usuário na base.");
        return usuarioFactory.buildResponse(usuario);
    }

    @Override
    public UsuarioResponse edit(UsuarioRequest usuarioRequest) {
        permissaoService.hasPermission("usuario:edit");
        Usuario usuario = usuarioRepository.findById(usuarioRequest.getId())
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioRequest.getId()));
        if(!usuarioRequest.getEmail().equals(usuario.getEmail())) {
            if (Objects.nonNull(usuarioRepository.findByEmail(usuarioRequest.getEmail()))) {
                throw new UsuarioAlreadyExistException(usuarioRequest.getEmail());
            }
        }

        if(!usuarioRequest.getUsername().equals(usuario.getUsername())) {
            if (Objects.nonNull(usuarioRepository.findByUsername(usuarioRequest.getUsername()))) {
                throw new UsuarioAlreadyExistException(usuarioRequest.getUsername());
            }
        }
        try {
            usuario = usuarioFactory.updateValues(usuario, usuarioRequest);
            usuario = usuarioRepository.save(usuario);

            return usuarioFactory.buildResponse(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseConstraintViolationException(e, "usuário");
        }
    }

    @Override
    public UsuarioLogadoResponse loadLoggedUser() {
        return usuarioFactory.buildLogadoResponse();
    }

    @Override
    public List<UsuarioResponse> findAll(Pageable pageable) {
        return usuarioFactory.buildResponseList(usuarioRepository.findAll(pageable).getContent());
    }

    @Override
    public List<UsuarioResponse> findAllByPapel(String papel, Pageable pageable) {
        List<Long> perfilIdList = papelPerfilService.findAllPerfisIdByPapel(papel);
        if (perfilIdList.isEmpty()) {
            throw new PapelPerfilNotFoundException(papel);
        }

        List<UsuarioResponse> usuarioResponseList = this.findAll(pageable);
        List<UsuarioPerfilResponse> perfis = usuarioPerfilService
                .findAllUsuarioPerfilByUsuarioIdList(
                        usuarioResponseList.stream().map(UsuarioResponse::getId).collect(Collectors.toList()));

        List<Long> linkedUsers = perfis.stream().filter(usuarioPerfil -> {
            if (usuarioPerfil.getPerfil() != null) {
                return usuarioPerfil.getIsDefault() && perfilIdList.contains(usuarioPerfil.getPerfil().getId());
            }

            return false;
        }).map(usuarioPerfil -> usuarioPerfil.getUsuario().getId()).collect(Collectors.toList());

        return usuarioResponseList.stream()
                .filter(usuario -> linkedUsers.contains(usuario.getId()))
                .sorted(Comparator.comparing(o -> o.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponse> findByUsuarioIdList(List<Long> userIdList) {
        return usuarioFactory.buildResponseList(usuarioRepository.findAllByIds(userIdList));
    }

    @Override
    public Page<UsuarioGridResponse> findAllByFilterGrid(UsuarioGridFilterRequest usuarioGridFilterRequest, Pageable pageable) {
        permissaoService.hasPermission("usuario:view");
        Page<Usuario> usuarioPage = usuarioRepository.findByFilter(usuarioGridFilterRequest.getNome(),
                                                                   usuarioGridFilterRequest.getActive(),
                                                                   usuarioGridFilterRequest.getNomePerfil(),
                                                                   pageable);

        return new PageImpl<>(usuarioFactory.buildGridResponseList(usuarioPage.getContent()),
                pageable,
                usuarioPage.getTotalElements());
    }

    @Override
    public UsuarioResponse findById(Long userId) {
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow(() -> new UsuarioNotFoundException(userId));

        return usuarioFactory.buildResponse(usuario);
    }

    @Transactional
    private void executeDeletes(Long usuarioId) {
        usuarioPerfilService.deleteByUsuarioId(usuarioId);
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public void delete(Long usuarioId) {
        permissaoService.hasPermission("usuario:delete");
        try {
            executeDeletes(usuarioId);
        } catch (DataIntegrityViolationException e) {
            generateMessageDataIntegrityViolationException(e);
        }
    }

    @Override
    public void validatePassword(UsuarioValidatePasswordRequest usuarioValidatePasswordRequest) {
        Usuario usuario = usuarioRepository.findById(SystemUtil.getCurrentUsuarioLogado().getId())
                .orElseThrow(() -> new UsuarioNotFoundException());

        if (Boolean.FALSE.equals(this.isPasswordMatches(usuarioValidatePasswordRequest.getPassword(),
                                                        usuario.getPassword()))) {
            throw new UsuarioMismatchPasswordException();
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null) throw new UsuarioNotFoundException();

        return usuario;
    }

    @Override
    public Usuario findEntityById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
    }
}
