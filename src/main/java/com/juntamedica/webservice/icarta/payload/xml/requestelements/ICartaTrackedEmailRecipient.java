package com.juntamedica.webservice.icarta.payload.xml.requestelements;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "recipient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipient", propOrder = {
        "type",
        "name",
        "email"
})
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class ICartaTrackedEmailRecipient {

    @XmlAttribute
    private String type;

    @XmlElement
    private String name;

    @XmlElement
    private String email;
}
