package com.juntamedica.anexo.payload;

import lombok.Data;
import java.util.List;

@Data
public class AnexoZipRequest {

    private List<Long> anexosIds;
}
