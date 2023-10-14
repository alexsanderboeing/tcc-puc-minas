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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "ConsultarResponse", namespace = "http://servico.cfm.org.br/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarResponse", propOrder = { "dadosMedico" })
public class CfmSOAPResponse {

    @XmlElement(name = "dadosMedico")
    protected CfmDadosMedicoSOAPResponse dadosMedico;
}
