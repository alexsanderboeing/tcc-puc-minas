package com.juntamedica.processo.factory;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum ProcessoSortByEnum {
    NOME_BENEFICIARIO("nomeBeneficiario", "b.nome", "String"),
    ETAPA_PROCESSO("etapaProcesso", "pe.nome", "String"),
    STATUS_ETAPA("statusEtapa", "se.nome", "String"),
    NOME_DESEMPATADOR("nomeDesempatador", "d.nome", "String"),
    NOME_PROFISSIONAL_ASSISTENTE("nomeProfissionalAssistente", "pa.nome", "String"),
    FLUXO_PROCESSO("fluxoProcesso", "fp.nomeApresentacao", "String"),
    OPERADORA_SOLICITANTE("operadora", "os.nomeFantasia", "String"),
    INICIO_PROCESSO("dataInicioProcesso", "p.inicioProcesso", "Date");

    private final String colunaGrid;
    private final String colunaTabela;
    private final String tipoColuna;

    private static final Map<String, ProcessoSortByEnum> lookup = new HashMap<>();

    static {
        for (ProcessoSortByEnum p : ProcessoSortByEnum.values()) {
            lookup.put(p.getColunaGrid(), p);
        }
    }

    ProcessoSortByEnum(String colunaGrid, String colunaTabela, String tipoColuna) {
        this.colunaGrid = colunaGrid;
        this.colunaTabela = colunaTabela;
        this.tipoColuna = tipoColuna;
    }

    public static ProcessoSortByEnum get(String colunaGrid) {
        return lookup.get(colunaGrid);
    }
}
