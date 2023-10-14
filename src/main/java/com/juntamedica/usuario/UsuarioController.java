package com.juntamedica.usuario;

import com.juntamedica.usuario.payload.UsuarioGridFilterRequest;
import com.juntamedica.usuario.payload.UsuarioGridResponse;
import com.juntamedica.usuario.payload.UsuarioLogadoResponse;
import com.juntamedica.usuario.payload.UsuarioNewPasswordRequest;
import com.juntamedica.usuario.payload.UsuarioRequest;
import com.juntamedica.usuario.payload.UsuarioResetPasswordRequest;
import com.juntamedica.usuario.payload.UsuarioResponse;
import com.juntamedica.usuario.payload.UsuarioValidatePasswordRequest;
import com.juntamedica.usuario.service.UsuarioService;
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
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse createUser(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.save(usuarioRequest);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse updateUser(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.edit(usuarioRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UsuarioLogadoResponse getLoggedUser() {
        return usuarioService.loadLoggedUser();
    }

    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse findById(@PathVariable Long userId) {
        return usuarioService.findById(userId);
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponse> findAll(@PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return usuarioService.findAll(pageable);
    }

    @PostMapping("/findAllGrid")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioGridResponse> findAllGrid(@Valid @RequestBody UsuarioGridFilterRequest usuarioGridFilterRequest,
                                             Pageable pageable) {
        return usuarioService.findAllByFilterGrid(usuarioGridFilterRequest, pageable);
    }

    @GetMapping("findByIdList/{userIdList}")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponse> findByUsuarioIdList(@PathVariable List<Long> userIdList) {
         return usuarioService.findByUsuarioIdList(userIdList);
    }

    @GetMapping("findAllByPapel/{papel}")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponse> findAllByPapel(@PathVariable String papel, Pageable pageable) {
        return usuarioService.findAllByPapel(papel, pageable);
    }

    @DeleteMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long usuarioId) {
        usuarioService.delete(usuarioId);
    }

    @PatchMapping("resetPassword")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@Valid @RequestBody UsuarioResetPasswordRequest usuarioResetPasswordRequest) {
        usuarioService.resetPassword(usuarioResetPasswordRequest);
    }

    @PostMapping("validatePassword")
    @ResponseStatus(HttpStatus.OK)
    void validatePassword(@Valid @RequestBody UsuarioValidatePasswordRequest usuarioValidatePasswordRequest) {
        usuarioService.validatePassword(usuarioValidatePasswordRequest);
    }

    @PatchMapping("newPassword")
    @ResponseStatus(HttpStatus.OK)
    void newPassword(@Valid @RequestBody UsuarioNewPasswordRequest usuarioNewPasswordRequest) {
        usuarioService.newPassword(usuarioNewPasswordRequest);
    }

    @GetMapping("/userinformation")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioLogadoResponse findUserInformationByToken() {
        return usuarioService.findUserInformationByToken();
    }
}
