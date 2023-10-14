package com.juntamedica.usuario;

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
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsuarioResetToken extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_reset_token")
    @SequenceGenerator(name = "usuario_reset_token", sequenceName = "s_usuario_reset_token", allocationSize = 1)
    private Long id;

    @NotNull(message = "User não pode ser nulo.")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @NotNull(message = "Token não pode ser nulo.")
    @Size(max = 8000, message = "O token pode conter até 8000 caracteres.")
    private String token;

    @NotNull(message = "Status não pode ser nulo.")
    @Size(max = 255, message = "O status pode conter até 255 caracteres.")
    private String status;

    private LocalDateTime expirationDate;
}
