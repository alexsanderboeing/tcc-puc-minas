package com.juntamedica.auditor.payload;

import lombok.Data;

@Data
public class AuditorComboRequest {

    private String term;
    private Long selectedAuditorId;
    private Boolean preCadastro;
}
