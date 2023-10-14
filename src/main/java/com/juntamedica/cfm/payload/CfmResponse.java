package com.juntamedica.cfm.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CfmResponse {

    private String crm;
    private String uf;
    private String nome;
    private String dataAtualizacao;
    private String situacao;
    private String tipoInscricao;
    private List<String> especialidades;
}
