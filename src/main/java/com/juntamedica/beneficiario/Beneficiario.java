package com.juntamedica.beneficiario;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.juntamedica.anexo.Anexo;
import com.juntamedica.contato.Contato;
import com.juntamedica.dominio.Dominio;
import com.juntamedica.endereco.Endereco;
import com.juntamedica.operadora.Operadora;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Beneficiario extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beneficiario")
    @SequenceGenerator(name = "beneficiario", sequenceName = "s_beneficiario", allocationSize = 1)
    private Long id;

    private Boolean preCadastro;

    private String codigo;

    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dominio dmSexoBeneficiario;

    @Column(name = "NASCIMENTO")
    private LocalDateTime dataNascimento;

    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    private Operadora operadora;

    @JsonManagedReference
    @OneToOne(mappedBy = "beneficiario", orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private Endereco endereco;

    @JsonManagedReference
    @OneToMany(mappedBy = "beneficiario", orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<Contato> contatoList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "beneficiario_anexo",
            joinColumns = @JoinColumn(name = "beneficiario_id"),
            inverseJoinColumns = @JoinColumn(name = "anexo_id"))
    private List<Anexo> anexoList;

    public void mergeContatoList(List<Contato> contatoList) {
        this.contatoList.clear();
        this.contatoList.addAll(contatoList);
    }

    public void mergeAnexoList(List<Anexo> anexoList) {
        if (this.anexoList == null) {
            this.anexoList = new ArrayList<>();
        }

        this.anexoList.clear();
        this.anexoList.addAll(anexoList);
    }
}
