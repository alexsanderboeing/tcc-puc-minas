package com.juntamedica.processo.payload;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProcessoFiltersRequest {

    private String filtroProcesso;
    private Long numeroProcesso;
    private Long numeroPedidoGuia;
    private Long beneficiarioId;
    private String codigoBeneficiario;
    private LocalDateTime periodoPrazoFinalInicio;
    private LocalDateTime periodoPrazoFinalFim;
    private LocalDateTime periodoConclusaoInicio;
    private LocalDateTime periodoConclusaoFim;
    private LocalDateTime periodoCriacaoInicio;
    private LocalDateTime periodoCriacaoFim;
    private Long operadoraSolicitanteId;
    private String etapaProcesso;
    private Long statusProcessoId;
    private Long profissionalAssistenteId;
    private String profissionalAssistenteNome;
    private Long desempatadorId;
    private String dmContagemSla;
    private Long administrativoOperadoraId;
    private Long administrativoFescExtId;
    private Boolean showRascunho;
}
