package com.juntamedica.auditor.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditorRestricaoDesempatadorResponse {

    private Long id;
    private String nome;
}
