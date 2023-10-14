package com.juntamedica.calendario;

import com.juntamedica.calendariodiahora.CalendarioDiaHora;
import com.juntamedica.calendarioferiado.CalendarioFeriado;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CALENDARIO")
public class Calendario extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calendario")
    @SequenceGenerator(name = "calendario", sequenceName = "s_calendario", allocationSize = 1)
    private Long id;

    @NotNull(message = "Nome não pode ser nulo.")
    @Size(max = 70, message = "O nome pode conter até 70 caracteres.")
    private String nome;

    private Boolean ativo;

    @OneToMany(mappedBy = "calendario", fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<CalendarioFeriado> calendarioFeriadoList;

    @OneToMany(mappedBy = "calendario", fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<CalendarioDiaHora> calendarioDiaHoraList;
}
