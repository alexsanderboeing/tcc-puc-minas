package com.juntamedica.cfm.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CfmRequest {

    private Integer crm;
    private String uf;
    private String chave;
}
