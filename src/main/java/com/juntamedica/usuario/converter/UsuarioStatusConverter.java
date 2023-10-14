package com.juntamedica.usuario.converter;

import com.juntamedica.usuario.factory.UsuarioStatusEnum;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class UsuarioStatusConverter implements AttributeConverter<UsuarioStatusEnum, String> {
    @Override
    public String convertToDatabaseColumn(UsuarioStatusEnum usuarioStatusEnum) {
        if (Objects.isNull(usuarioStatusEnum)) {
            return null;
        }

        return usuarioStatusEnum.getValue();
    }

    @Override
    public UsuarioStatusEnum convertToEntityAttribute(String s) {
        if (Objects.isNull(s)) {
            return null;
        }

        return UsuarioStatusEnum.get(s);
    }
}
