package com.juntamedica.anexo.converter;

import com.juntamedica.anexo.factory.AnexoMimeTypeEnum;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class AnexoMimeTypeConverter implements AttributeConverter<AnexoMimeTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(AnexoMimeTypeEnum anexoMimeTypeEnum) {
        if (Objects.isNull(anexoMimeTypeEnum)) {
            return null;
        }

        return anexoMimeTypeEnum.getValue();
    }

    @Override
    public AnexoMimeTypeEnum convertToEntityAttribute(String s) {
        if (Objects.isNull(s)) {
            return null;
        }

        return AnexoMimeTypeEnum.get(s);
    }
}
