package com.juntamedica.token;

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
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token")
    @SequenceGenerator(name = "token", sequenceName = "s_token", allocationSize = 1)
    private Long id;

    @NotNull(message = "Active não pode ser nulo.")
    private Boolean active;

    @Size(max = 8000, message = "O token value pode conter até 8000 caracteres.")
    private String tokenValue;

    private LocalDateTime expirationDate;
}
