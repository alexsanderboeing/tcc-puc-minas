package com.juntamedica.webservice.icarta.payload.xml;

import com.juntamedica.webservice.icarta.payload.xml.responseelements.ICartaTrackedEmailsResponse;
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
@XmlRootElement(name = "sendTrackedEmailResponse", namespace = "http://webservice.icarta.pro/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendTrackedEmailResponse", propOrder = {
        "status",
        "description",
        "trackedEmails"
})
public class ICartaSOAPResponse {

    @XmlElement
    private String status;

    @XmlElement
    private String description;

    private ICartaTrackedEmailsResponse trackedEmails;
}
