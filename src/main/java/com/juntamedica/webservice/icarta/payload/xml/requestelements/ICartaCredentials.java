package com.juntamedica.webservice.icarta.payload.xml.requestelements;

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
@XmlRootElement(name = "credentials")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "credentials", propOrder = {
        "appId",
        "appSecret"
})
public class ICartaCredentials {

    @XmlElement
    private String appId;

    @XmlElement
    private String appSecret;
}
