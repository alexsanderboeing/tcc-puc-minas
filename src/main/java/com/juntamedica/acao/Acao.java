package com.juntamedica.acao;

import com.juntamedica.dominio.Dominio;
import com.juntamedica.utils.DateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Acao extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acao")
    @SequenceGenerator(name = "acao", sequenceName = "s_acao", allocationSize = 1)
    private Long id;

    @NotNull(message = "Nome não pode ser nulo.")
    @Size(max = 100, message = "Nome pode conter até 100 caracteres.")
    private String nome;

    @NotNull(message = "O nome do botão não pode ser nulo.")
    private String nomeBotao;

    @NotNull(message = "A cor do botão não pode ser nula.")
    private String corBotao;

    private Long ordemEmTela;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Modelo de tela não pode ser nulo.")
    private Dominio dmModeloTela;
}
