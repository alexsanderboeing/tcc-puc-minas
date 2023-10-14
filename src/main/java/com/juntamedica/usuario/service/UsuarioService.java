package com.juntamedica.usuario.service;

import com.juntamedica.usuario.Usuario;
import com.juntamedica.usuario.payload.UsuarioGridFilterRequest;
import com.juntamedica.usuario.payload.UsuarioGridResponse;
import com.juntamedica.usuario.payload.UsuarioLogadoResponse;
import com.juntamedica.usuario.payload.UsuarioNewPasswordRequest;
import com.juntamedica.usuario.payload.UsuarioRequest;
import com.juntamedica.usuario.payload.UsuarioResetPasswordRequest;
import com.juntamedica.usuario.payload.UsuarioResponse;
import com.juntamedica.usuario.payload.UsuarioValidatePasswordRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UsuarioService {

    UsuarioLogadoResponse findUserInformationByToken();

    void newPassword(UsuarioNewPasswordRequest usuarioNewPasswordRequest);

    void resetPassword(UsuarioResetPasswordRequest usuarioResetPasswordRequest);

    UsuarioResponse save(UsuarioRequest usuarioRequest);

    UsuarioResponse edit(UsuarioRequest usuarioRequest);

    UsuarioLogadoResponse loadLoggedUser();

    List<UsuarioResponse> findAll(Pageable pageable);

    List<UsuarioResponse> findAllByPapel(String papel, Pageable pageable);

    List<UsuarioResponse> findByUsuarioIdList(List<Long> userIdList);

    Page<UsuarioGridResponse> findAllByFilterGrid(UsuarioGridFilterRequest usuarioGridFilterRequest, Pageable pageable);

    UsuarioResponse findById(Long userId);

    void delete(Long usuarioId);

    void validatePassword(UsuarioValidatePasswordRequest usuarioValidatePasswordRequest);

    Usuario findByEmail(String email);

    Usuario findEntityById(Long id);
}
