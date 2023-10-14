package com.juntamedica.webservice.icarta.payload.xml.requestelements;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "attachments")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attachments", propOrder = {
        "attachment"
})
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class ICartaTrackedEmailAttachments {

    @XmlElement
    private List<ICartaTrackedEmailAttachment> attachment;
}
