package com.juntamedica.webservice.dadosautorizacao.payload;

import lombok.Data;

@Data
public class DadosAutorizacaoEnderecoResponse {

    private String cep;
    private String cidade;
    private String estado;
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento;
}
