package com.juntamedica.auditor;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.juntamedica.auditorconselho.AuditorConselho;
import com.juntamedica.auditorespecialidade.AuditorEspecialidade;
import com.juntamedica.operadora.Operadora;
import com.juntamedica.utils.DateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUDITOR")
public class Auditor extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auditor")
    @SequenceGenerator(name = "auditor", sequenceName = "s_auditor", allocationSize = 1)
    private Long id;

    private String nome;
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    private Operadora operadora;

    private Boolean preCadastro;

    @JsonManagedReference
    @OneToMany(mappedBy = "auditor", fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<AuditorConselho> conselhoList;

    @JsonManagedReference
    @OneToMany(mappedBy = "auditor", fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<AuditorEspecialidade> especialidadeList;

    public void mergeConselhoList(List<AuditorConselho> auditorConselhoList) {
        this.conselhoList.clear();
        this.conselhoList.addAll(auditorConselhoList);
    }

    public void mergeEspecialidadeList(List<AuditorEspecialidade> auditorEspecialidadeList) {
        if (this.especialidadeList != null && !this.especialidadeList.isEmpty()) {
            auditorEspecialidadeList = auditorEspecialidadeList.stream().map(especialidade -> {
                Optional<AuditorEspecialidade> auditorEspecialidade = this.especialidadeList.stream()
                        .filter(item -> item.getEspecialidade().getId()
                                .compareTo(especialidade.getEspecialidade().getId()) == 0).findFirst();

                if (!auditorEspecialidade.isEmpty()) {
                    especialidade.setId(auditorEspecialidade.get().getId());
                }

                return especialidade;
            }).collect(Collectors.toList());
        }

        this.especialidadeList.clear();
        this.especialidadeList.addAll(auditorEspecialidadeList);
    }
}
