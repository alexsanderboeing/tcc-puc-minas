package com.juntamedica.processo.enumerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Leitura {

    VAZIO(1L, ""),
    LIDO(2L, "Lido Totalmente"),
    NAO_LIDO(3L, "Não Lido"),
    LIDO_PARCIALMENTE(4L, "Lido Parcialmente");

    private Long id;
    private String texto;

    private static final Map<String, Leitura> lookup = new HashMap<>();

    static {
        for (Leitura a : Leitura.values()) {
            lookup.put(a.getTexto(), a);
        }
    }

    Leitura(Long id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public static Leitura get(String texto) {
        Leitura leitura = lookup.get(texto);

        return Objects.isNull(leitura) ? null : leitura;
    }
}
