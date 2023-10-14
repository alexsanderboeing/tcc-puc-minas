package com.juntamedica.webservice.dadosautorizacao.payload;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import java.util.List;

@Data
public class DadosAutorizacaoAuditorResponse {

    @JsonAlias("crm_numero")
    private String crmNumero;

    @JsonAlias("crm_uf")
    private String crmUf;

    @JsonAlias("cro_numero")
    private String croNumero;

    @JsonAlias("cro_uf")
    private String croUf;

    private List<DadosAutorizacaoCboResponse> cbos;

    @JsonAlias("nome_completo")
    private String nomeCompleto;

    @JsonAlias("telefones_celulares")
    private List<DadosAutorizacaoTelefoneResponse> telefonesCelulares;

    @JsonAlias("telefones_comerciais")
    private List<DadosAutorizacaoTelefoneResponse> telefonesComerciais;

    private List<DadosAutorizacaoEnderecoResponse> enderecos;

    private String observacao;
}
