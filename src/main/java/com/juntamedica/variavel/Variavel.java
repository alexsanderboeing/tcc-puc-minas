package com.juntamedica.variavel;

import com.juntamedica.utils.DateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Variavel extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variavel")
    @SequenceGenerator(name = "variavel", sequenceName = "s_variavel", allocationSize = 1)
    private Long id;

    @NotNull(message = "O chave conteudo, não pode ser nulo.")
    @Size(max = 150, message = "O campo chave, pode conter até 150 caracteres.")
    private String chave;

    @NotNull(message = "O nome, não pode ser nulo.")
    @Size(max = 170, message = "O campo nome, pode conter até 170 caracteres.")
    private String nome;

    @NotNull(message = "O descricao, não pode ser nulo.")
    @Size(max = 300, message = "O campo descricao, pode conter até 300 caracteres.")
    private String descricao;
}
