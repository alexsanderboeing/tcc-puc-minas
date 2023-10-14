package com.juntamedica.webservice.dadosautorizacao.payload;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import java.util.List;

@Data
public class DadosAutorizacaoResponse {

    @JsonAlias("procedimento_ja_realizado")
    private String procedimentoJaRealizado;

    @JsonAlias("carater_atendimento")
    private String caraterAtendimento;

    @JsonAlias("plano_nao_regulamentado")
    private String planoNaoRegulamentado;

    @JsonAlias("possui_nip")
    private String possuiNip;

    @JsonAlias("possui_liminar")
    private String possuiLiminar;

    @JsonAlias("indicacao_clinica")
    private String indicacaoClinica;

    @JsonAlias("justificativa_operadora")
    private String justificativaOperadora;

    private List<DadosAutorizacaoItensResponse> itens;

    private List<DadosAutorizacaoBeneficiarioResponse> beneficiario;

    @JsonAlias("profissional_assistente")
    private List<DadosAutorizacaoProfissionalAssistenteResponse> profissionalAssistente;

    private List<DadosAutorizacaoAuditorResponse> auditor;
}
