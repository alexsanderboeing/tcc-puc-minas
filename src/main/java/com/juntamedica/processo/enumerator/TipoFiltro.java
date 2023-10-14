package com.juntamedica.processo.enumerator;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum TipoFiltro {

    MINHAS_PENDENCIAS("MINHAS_PENDENCIAS"),
    PENDENCIAS_AREA("PENDENCIAS_AREA"),
    FINALIZADOS("FINALIZADOS"),
    TODOS("TODOS");

    private String valor;

    TipoFiltro(String valor) { this.valor = valor; }

    public static TipoFiltro from(String valor) {
        for (TipoFiltro tipoFiltro : values()) {
            if (tipoFiltro.valor.equalsIgnoreCase(valor)) {
                return tipoFiltro;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Filtro não encontrado");
    }
}
