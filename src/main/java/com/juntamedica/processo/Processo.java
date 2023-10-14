package com.juntamedica.processo;

import com.juntamedica.anexo.Anexo;
import com.juntamedica.auditor.Auditor;
import com.juntamedica.beneficiario.Beneficiario;
import com.juntamedica.consultor.Consultor;
import com.juntamedica.contrato.Contrato;
import com.juntamedica.desempatador.Desempatador;
import com.juntamedica.dominio.Dominio;
import com.juntamedica.fluxoprocesso.FluxoProcesso;
import com.juntamedica.operadora.Operadora;
import com.juntamedica.operadoralayout.OperadoraLayout;
import com.juntamedica.processoareaatuacao.ProcessoAreaAtuacao;
import com.juntamedica.processoitem.ProcessoItem;
import com.juntamedica.profissionalassistente.ProfissionalAssistente;
import com.juntamedica.statusetapa.StatusEtapa;
import com.juntamedica.usuario.Usuario;
import com.juntamedica.utils.DateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Processo extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "processo")
    @SequenceGenerator(name = "processo", sequenceName = "s_processo", allocationSize = 1)
    private Long id;

    private Boolean modoRascunho;
    private Boolean prioridade;
    private Boolean podeMatOutroFab;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataReprovacaoOperadora;

    @Column(columnDefinition = "CLOB")
    private String justificativaOperadora;
    private String justificativaCancelamento;
    private Long avaliacaoTrabalho;
    private Long avaliacaoDesempatador;
    private Boolean documentacaoCompleta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrativo_fesc_ext_id")
    private Usuario administrativoFesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditor_qualidade_ext_id")
    private Usuario auditorQualidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrativo_operadora_id")
    private Usuario administrativoOperadora;

    private String numeroPedidoGuia;
    private LocalDateTime conclusaoProcesso;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime prazoInicialAns;
    private LocalDateTime prazoFinalAns;
    private Boolean procedimentoRealizado;
    private Boolean possuiLiminar;
    private Boolean possuiNip;
    private Boolean planoRegulamentado;
    private String indicacaoClinica;
    private Boolean prazoGuiaLegivel;
    private LocalDateTime dataParecer;
    private Boolean parecerVisivelOperadora;

    @Pattern(regexp = "^(\\d*)$", message = "Número do protocolo pode conter apenas números")
    @Size(max = 20, message = "Número do protocolo pode conter até 20 caracteres")
    private String numeroProtocolo;

    @Column(columnDefinition = "CLOB")
    private String parecerDesempatador;

    @Column(columnDefinition = "CLOB")
    private String parecerDesempatadorRascunho;
    private String parecerCaraterSolicitacao;
    private Boolean necessarioMatFabDistrib;
    private String resumoAoBeneficiario;
    private String resumoParecerDesempatador;
    private String itensExclusivos;
    private LocalDateTime dataEstimadaConclusao;
    private LocalDateTime dataEnvioDesempatador;

    @ManyToOne(fetch = FetchType.LAZY)
    private Beneficiario beneficiario;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProfissionalAssistente profissionalAssistente;

    @ManyToOne(fetch = FetchType.LAZY)
    private Auditor auditor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Consultor consultor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Desempatador desempatador;

    @ManyToOne(fetch = FetchType.LAZY)
    private Operadora operadoraContratante;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dominio dmCaraterAtendimento;

    private LocalDateTime inicioProcesso;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contrato contrato;

    @ManyToOne(fetch = FetchType.LAZY)
    private FluxoProcesso fluxoProcesso;

    @ManyToOne(fetch = FetchType.LAZY)
    private StatusEtapa statusEtapa;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dominio dmCancelamentoMotivado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dominio dmMotivoCancelProcesso;

    @ManyToOne(fetch = FetchType.LAZY)
    private Operadora operadoraOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    private Operadora operadoraSolicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dominio dmReprovarMotivado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dominio dmMotivoReprovarProcesso;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dominio dmCobrarProcesso;

    @OneToMany(mappedBy = "processo", orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<ProcessoItem> processoItemList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "processo_anexo",
            joinColumns = @JoinColumn(name = "processo_id"),
            inverseJoinColumns = @JoinColumn(name = "anexo_id"))
    private List<Anexo> anexoList;

    @OneToMany(mappedBy = "processo", orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<ProcessoAreaAtuacao> areaAtuacaoList;

    @ManyToOne(fetch = FetchType.LAZY)
    private OperadoraLayout operadoraLayout;

    public void mergeProcessoAnexoList(List<Anexo> processoAnexoList) {
        if (this.anexoList == null) {
            this.anexoList = new ArrayList<>();
        }

        this.anexoList.clear();
        this.anexoList.addAll(processoAnexoList);
    }

    public void mergeProcessoItemList(List<ProcessoItem> processoItemList) {
        if (this.processoItemList == null) {
            this.processoItemList = new ArrayList<>();
        }

        this.processoItemList.clear();
        this.processoItemList.addAll(processoItemList);
    }

    public void mergeProcessoAreaAtuacaoList(List<ProcessoAreaAtuacao> areaAtuacaoList) {
        if (this.areaAtuacaoList == null) {
            this.areaAtuacaoList = new ArrayList<>();
        }

        this.areaAtuacaoList.clear();
        this.areaAtuacaoList.addAll(areaAtuacaoList);
    }
}
