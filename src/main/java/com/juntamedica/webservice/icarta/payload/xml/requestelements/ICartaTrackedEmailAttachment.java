package com.juntamedica.webservice.icarta.payload.xml.requestelements;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.juntamedica.utils.Base64Adapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "attachment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attachment", propOrder = {
        "fileName",
        "contentType",
        "content"
})
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class ICartaTrackedEmailAttachment {

    @XmlElement
    private String fileName;

    @XmlElement
    private String contentType;

    @XmlElement
    private String content;
}
