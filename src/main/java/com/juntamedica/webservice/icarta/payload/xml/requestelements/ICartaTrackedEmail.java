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
@XmlRootElement(name = "trackedEmail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trackedEmail", namespace = "request", propOrder = {
        "subject",
        "sender",
        "recipients",
        "content",
        "trackedResponse",
        "attachments"
})
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class ICartaTrackedEmail {

    @XmlAttribute(name = "message-id")
    private String messageId;

    @XmlElement
    private String subject;

    @XmlElement
    private String content;

    @XmlElement
    private String trackedResponse;

    private ICartaTrackedEmailSender sender;
    private ICartaTrackedEmailRecipients recipients;
    private ICartaTrackedEmailAttachments attachments;
}
