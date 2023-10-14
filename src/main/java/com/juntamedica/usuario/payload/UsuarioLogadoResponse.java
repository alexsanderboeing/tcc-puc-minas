package com.juntamedica.usuario.payload;

import com.juntamedica.operadora.payload.OperadoraResponse;
import com.juntamedica.papel.payload.PapelResponse;
import com.juntamedica.usuarioperfil.payload.UsuarioPerfilResponse;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class UsuarioLogadoResponse {

    private Long id;
    private String nome;
    private String username;
    private String email;
    private String token;
    private OperadoraResponse operadora;
    private List<UsuarioPerfilResponse> perfilList;
    private UsuarioPerfilResponse defaultPerfil;
    private PapelResponse papel;
    private Boolean autenticacaoDuploFator;
    private String uuidDuploFator;
}
