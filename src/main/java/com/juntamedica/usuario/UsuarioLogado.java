package com.juntamedica.usuario;

import com.juntamedica.operadora.Operadora;
import com.juntamedica.operadorausuario.OperadoraUsuario;
import com.juntamedica.operadorausuario.service.OperadoraUsuarioService;
import com.juntamedica.papel.Papel;
import com.juntamedica.papelperfil.PapelPerfil;
import com.juntamedica.papelperfil.service.PapelPerfilService;
import com.juntamedica.usuarioperfil.UsuarioPerfil;
import com.juntamedica.usuarioperfil.service.UsuarioPerfilService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Cacheable(cacheNames = "usuario", key = "#usuarioLogado.id")
public class UsuarioLogado implements UserDetails {

    protected static final Logger LOGGER = LoggerFactory.getLogger(UsuarioLogado.class);
    private static OperadoraUsuarioService operadoraUsuarioService;
    private static PapelPerfilService papelPerfilService;
    private static UsuarioPerfilService usuarioPerfilService;

    private Long id;
    private String nome;
    private String username;
    private String email;
    private String password;
    private String token;
    private Operadora operadora;
    private List<UsuarioPerfil> perfilList;
    private UsuarioPerfil defaultPerfil;
    private Papel papel;

    @Autowired
    private UsuarioLogado(OperadoraUsuarioService operadoraUsuarioService,
                          PapelPerfilService papelPerfilService,
                          UsuarioPerfilService usuarioPerfilService) {
        UsuarioLogado.operadoraUsuarioService = operadoraUsuarioService;
        UsuarioLogado.papelPerfilService = papelPerfilService;
        UsuarioLogado.usuarioPerfilService = usuarioPerfilService;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Operadora getOperadora() {
        if (this.id != null && this.operadora == null) {
            try {
                OperadoraUsuario operadoraUsuario = operadoraUsuarioService.findEntityByUsuario(this.id);
                this.setOperadora(operadoraUsuario != null ? operadoraUsuario.getOperadora() : null);
            } catch(Exception e) {
                this.setOperadora(null);
                LOGGER.error(String.format("Falha ao buscar operadora do usuário logado (id=%d).", this.id));
            }
        }

        return this.operadora;
    }

    public UsuarioPerfil getDefaultUsuarioPerfil() {
        if (this.id != null && this.defaultPerfil == null) {
            try {
                UsuarioPerfil usuarioDefaultPerfil = usuarioPerfilService.findEntityUsuarioDefaultPerfil(this.id);
                this.setDefaultPerfil(usuarioDefaultPerfil);
            } catch(Exception e) {
                this.setDefaultPerfil(null);
                LOGGER.error(String.format("Falha ao buscar perfil padrão do usuário logado (id=%d).", this.id));
            }
        }

        return this.defaultPerfil;
    }

    public List<UsuarioPerfil> getPerfilList() {
        if (this.id != null && this.perfilList == null) {
            try {
                List<UsuarioPerfil> usuarioPerfilList = usuarioPerfilService.findAllEntityByUsuarioId(this.id);
                this.setPerfilList(usuarioPerfilList);
            } catch(Exception e) {
                this.setDefaultPerfil(null);
                LOGGER.error(String.format("Falha ao buscar perfis do usuário logado (id=%d).", this.id));
            }
        }

        return this.perfilList;
    }

    public Papel getPapel() {
        if (this.id != null && this.papel == null && this.defaultPerfil != null) {
            try {
                PapelPerfil papelPerfil = papelPerfilService.findByPerfilIdEntity(this.defaultPerfil.getPerfil().getId());
                this.setPapel(papelPerfil != null ? papelPerfil.getPapel() : null);
            } catch(Exception e) {
                this.setPapel(null);
                LOGGER.error(String.format("Falha ao buscar papel do usuário logado (id=%d).", this.id));
            }
        }

        return this.papel;
    }
}
