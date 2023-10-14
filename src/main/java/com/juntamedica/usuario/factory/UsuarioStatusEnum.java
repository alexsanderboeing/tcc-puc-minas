package com.juntamedica.usuario.factory;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public enum UsuarioStatusEnum {
    HABILITADO("HABILITADO"),
    PENDENTE("PENDENTE"),
    BLOQUEADO("BLOQUEADO");

    private final String value;

    private static final Map<String, UsuarioStatusEnum> lookup = new HashMap<>();

    static {
        for (UsuarioStatusEnum u : UsuarioStatusEnum.values()) {
            lookup.put(u.getValue(), u);
        }
    }

    UsuarioStatusEnum(String value) {
        this.value = value;
    }

    public static UsuarioStatusEnum get(String value) {
        UsuarioStatusEnum usuarioStatusEnum = lookup.get(value);

        return Objects.isNull(usuarioStatusEnum) ? UsuarioStatusEnum.HABILITADO : usuarioStatusEnum;
    }
}
