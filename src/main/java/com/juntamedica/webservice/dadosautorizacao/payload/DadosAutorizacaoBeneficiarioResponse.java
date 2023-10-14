package com.juntamedica.webservice.dadosautorizacao.payload;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import java.util.List;

@Data
public class DadosAutorizacaoBeneficiarioResponse {

    private String codigo;

    @JsonAlias("nome_completo")
    private String nome;

    private String sexo;
    private String nascimento;
    private List<DadosAutorizacaoEmailResponse> emails;
    private List<DadosAutorizacaoTelefoneResponse> telefones;
    private List<DadosAutorizacaoEnderecoResponse> enderecos;
    private String observacao;
}
