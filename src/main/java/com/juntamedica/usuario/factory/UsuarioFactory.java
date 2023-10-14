package com.juntamedica.usuario.factory;

import com.juntamedica.operadora.factory.OperadoraFactory;
import com.juntamedica.operadora.payload.OperadoraResponse;
import com.juntamedica.papel.factory.PapelFactory;
import com.juntamedica.papel.payload.PapelResponse;
import com.juntamedica.usuario.Usuario;
import com.juntamedica.usuario.UsuarioLogado;
import com.juntamedica.usuario.payload.UsuarioGridResponse;
import com.juntamedica.usuario.payload.UsuarioLogadoResponse;
import com.juntamedica.usuario.payload.UsuarioRequest;
import com.juntamedica.usuario.payload.UsuarioResponse;
import com.juntamedica.usuarioperfil.factory.UsuarioPerfilFactory;
import com.juntamedica.usuarioperfil.payload.UsuarioPerfilResponse;
import com.juntamedica.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UsuarioFactory {

    @Autowired
    private OperadoraFactory operadoraFactory;

    @Autowired
    private UsuarioPerfilFactory usuarioPerfilFactory;

    @Autowired
    private PapelFactory papelFactory;

    @Autowired
    private Argon2PasswordEncoder argon2PasswordEncoder;

    public Usuario build(UsuarioRequest usuarioRequest) {
        return Usuario.builder()
                .id(usuarioRequest.getId())
                .active(usuarioRequest.getActive())
                .email(usuarioRequest.getEmail())
                .name(usuarioRequest.getName())
                .username(usuarioRequest.getUsername())
                .status(UsuarioStatusEnum.HABILITADO.getValue()) // TODO: mudar, com envio de confirmação do user
                .password(argon2PasswordEncoder.encode(usuarioRequest.getPassword()))
                .passwordExpirationDate(LocalDateTime.now().plusMonths(3))
                .expiredPassword(false)
                .autenticacaoDuploFator(usuarioRequest.getAutenticacaoDuploFator() != null
                        ? usuarioRequest.getAutenticacaoDuploFator() : Boolean.FALSE)
                .build();
    }

    public UsuarioLogadoResponse buildLogadoResponse() {
        UsuarioLogado usuarioLogado = Objects.requireNonNull(SystemUtil.getCurrentUsuarioLogado());
        OperadoraResponse operadora = usuarioLogado.getOperadora() != null
                ? operadoraFactory.buildResponse(usuarioLogado.getOperadora())
                : null;
        List<UsuarioPerfilResponse> perfilList = usuarioLogado.getPerfilList() != null
                ? usuarioPerfilFactory.buildResponseList(usuarioLogado.getPerfilList())
                : Collections.emptyList();
        UsuarioPerfilResponse defaultPerfil = usuarioLogado.getDefaultUsuarioPerfil() != null
                ? usuarioPerfilFactory.buildResponse(usuarioLogado.getDefaultUsuarioPerfil())
                : null;
        PapelResponse papel = usuarioLogado.getPapel() != null
                ? papelFactory.buildResponse(usuarioLogado.getPapel())
                : null;

        return UsuarioLogadoResponse.builder()
                .id(usuarioLogado.getId())
                .nome(usuarioLogado.getNome())
                .username(usuarioLogado.getUsername())
                .email(usuarioLogado.getEmail())
                .token(usuarioLogado.getToken())
                .operadora(operadora)
                .perfilList(perfilList)
                .defaultPerfil(defaultPerfil)
                .papel(papel)
                .autenticacaoDuploFator(Boolean.FALSE)
                .build();
    }

    public UsuarioResponse buildResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .active(usuario.getActive())
                .email(usuario.getEmail())
                .name(usuario.getName())
                .username(usuario.getUsername())
                .status(usuario.getStatus())
                .expiredPassword(usuario.getExpiredPassword())
                .autenticacaoDuploFator(usuario.getAutenticacaoDuploFator())
                .build();
    }

    public UsuarioGridResponse buildGridResponse(Usuario usuario) {
        return UsuarioGridResponse.builder()
                .id(usuario.getId())
                .active(usuario.getActive())
                .username(usuario.getUsername())
                .name(usuario.getName())
                .email(usuario.getEmail())
                .expiredPassword(usuario.getExpiredPassword())
                .createdAt(usuario.getCreatedAt())
                .build();
    }

    public List<UsuarioResponse> buildResponseList(List<Usuario> usuarioList) {
        return usuarioList.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    public List<UsuarioGridResponse> buildGridResponseList(List<Usuario> usuarioList) {
        return usuarioList.stream().map(this::buildGridResponse).collect(Collectors.toList());
    }

    public Usuario updateValues(Usuario usuario, UsuarioRequest usuarioRequest) {
        usuario.setActive(usuarioRequest.getActive());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setName(usuarioRequest.getName());
        usuario.setUsername(usuarioRequest.getUsername());
        usuario.setPassword(Objects.nonNull(usuarioRequest.getPassword()) && !usuarioRequest.getPassword().isEmpty()
                ? argon2PasswordEncoder.encode(usuarioRequest.getPassword())
                : usuario.getPassword());
        usuario.setAutenticacaoDuploFator(usuarioRequest.getAutenticacaoDuploFator());

        return usuario;
    }

    public UsuarioLogadoResponse buildLogadoResponseDuploFator(Usuario usuario, String uuidDuploFator) {
        return UsuarioLogadoResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getName())
                .email(usuario.getEmail())
                .perfilList(Collections.emptyList())
                .autenticacaoDuploFator(Boolean.TRUE)
                .uuidDuploFator(uuidDuploFator)
                .build();
    }
}
