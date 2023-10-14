package com.juntamedica.webservice.cfm.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "dadosMedico")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosMedico", propOrder = {
        "codigoErro",
        "crm",
        "dataAtualizacao",
        "especialidade",
        "nome",
        "situacao",
        "tipoInscricao",
        "uf"
})
public class CfmDadosMedicoSOAPResponse {

    @XmlElement(nillable = true)
    private List<String> especialidade;

    private String codigoErro;
    private String crm;
    private String dataAtualizacao;
    private String nome;
    private String situacao;
    private String tipoInscricao;
    private String uf;
}
