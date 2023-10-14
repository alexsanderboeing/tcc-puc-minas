package com.juntamedica.webservice.icarta.payload.xml;

import com.juntamedica.webservice.icarta.payload.xml.requestelements.ICartaCredentials;
import com.juntamedica.webservice.icarta.payload.xml.requestelements.ICartaTrackedEmails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "sendTrackedEmail", namespace = "http://webservice.icarta.pro/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendTrackedEmail", propOrder = {
        "credentials",
        "trackedEmails"
})
public class ICartaSOAPRequest {

    private ICartaCredentials credentials;
    private ICartaTrackedEmails trackedEmails;
}
