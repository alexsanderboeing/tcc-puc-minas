package com.juntamedica.processo.enumerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LeituraPapel {


    LIDO_ADMINISTRATIVO_OPERADORA(1L, "Lido totalmente pelo Administrativo Operadora"),
    NAO_LIDO_ADMINISTRATIVO_OPERADORA(2L, "Não lido pelo Administrativo Operadora"),
    LIDO_PARCIALMENTE_ADMINISTRATIVO_OPERADORA(3L, "Lido parcialmente pelo Administrativo Operadora"),

    LIDO_AUDITOR_OPERADORA(4L, "Lido totalmente pelo Auditor Operadora"),
    NAO_LIDO_LIDO_AUDITOR_OPERADORA(5L, "Não lido pelo Auditor Operadora"),
    LIDO_PARCIALMENTE_LIDO_AUDITOR_OPERADORA(6L, "Lido parcialmente pelo Auditor Operadora"),

    LIDO_PROFISSIONAL_ASSISTENTE(7L, "Lido totalmente pelo Profissional Assistente"),
    NAO_LIDO_PROFISSIONAL_ASSISTENTE(8L, "Não lido pelo Profissional Assistente"),
    LIDO_PARCIALMENTE_PROFISSIONAL_ASSISTENTE(9L, "Lido parcialmente pelo Profissional Assistente"),

    LIDO_BENEFICIARIO(10L, "Lido totalmente pelo Beneficiário"),
    NAO_LIDO_BENEFICIARIO(11L, "Não lido pelo Beneficiário"),
    LIDO_PARCIALMENTE_BENEFICIARIO(12L, "Lido parcialmente pelo Beneficiário"),

    LIDO_ADMINISTRATIVO_FESC(13L, "Lido totalmente pelo Administrativo FESC"),
    NAO_LIDO_ADMINISTRATIVO_FESC(14L, "Não lido pelo Administrativo FESC"),
    LIDO_PARCIALMENTE_ADMINISTRATIVO_FESC(15L, "Lido parcialmente pelo Administrativo FESC"),

    LIDO_AUDITOR_QUALIDADE_FESC(16L, "Lido totalmente pelo Auditor Qualidade FESC"),
    NAO_LIDO_AUDITOR_QUALIDADE_FESC(17L, "Não lido pelo Auditor Qualidade FESC"),
    LIDO_PARCIALMENTE_AUDITOR_QUALIDADE_FESC(18L, "Lido parcialmente pelo Auditor Qualidade FESC"),

    LIDO_DESEMPATADOR(19L, "Lido totalmente pelo Desempatador"),
    NAO_LIDO_DESEMPATADOR(20L, "Não lido pelo Desempatador"),
    LIDO_PARCIALMENTE_DESEMPATADOR(21L, "Lido parcialmente pelo Desempatador");

    private Long id;
    private String texto;

    private static final Map<String, LeituraPapel> lookup = new HashMap<>();

    static {
        for (LeituraPapel a : LeituraPapel.values()) {
            lookup.put(a.getTexto(), a);
        }
    }

    LeituraPapel(Long id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public static LeituraPapel get(String texto) {
        LeituraPapel leitura = lookup.get(texto);

        return Objects.isNull(leitura) ? null : leitura;
    }
}
