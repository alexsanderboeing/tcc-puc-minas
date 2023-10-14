package com.juntamedica.usuario;

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
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
    @SequenceGenerator(name = "usuario", sequenceName = "s_usuario", allocationSize = 1)
    private Long id;

    @NotNull(message = "Active não pode ser nulo.")
    private Boolean active;

    @Size(max = 255, message = "O nome pode conter até 255 caracteres.")
    private String email;

    @Size(max = 255, message = "O nome pode conter até 255 caracteres.")
    private String name;

    @NotNull(message = "Username não pode ser nulo.")
    @Size(max = 255, message = "O username pode conter até 255 caracteres.")
    private String username;

    @NotNull(message = "Status não pode ser nulo.")
    @Size(max = 255, message = "O status pode conter até 255 caracteres.")
    private String status;

    @Size(max = 255, message = "O password pode conter até 255 caracteres.")
    private String password;

    @NotNull(message = "Expired password não pode ser nulo.")
    private Boolean expiredPassword;

    private LocalDateTime passwordExpirationDate;

    private LocalDateTime passwordRegistrationDate;

    @NotNull(message = "Autenticação duplo fator não pode ser nulo.")
    private Boolean autenticacaoDuploFator;
}
