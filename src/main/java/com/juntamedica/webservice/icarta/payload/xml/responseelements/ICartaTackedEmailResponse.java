package com.juntamedica.webservice.icarta.payload.xml.responseelements;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "trackedEmailResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trackedEmails", namespace = "response", propOrder = {
        "messageId",
        "statusEmail",
        "description"
})
public class ICartaTackedEmailResponse {

    @XmlElement
    private String messageId;

    @XmlElement
    private String statusEmail;

    @XmlElement
    private String description;

}
