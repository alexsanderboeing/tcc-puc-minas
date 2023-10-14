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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Consultar", namespace = "http://servico.cfm.org.br/")
@XmlType(name = "Consultar", propOrder = {
        "crm",
        "uf",
        "chave"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CfmSOAPRequest {

    @XmlElement
    private Integer crm;

    @XmlElement
    private String uf;

    @XmlElement
    private String chave;
}
