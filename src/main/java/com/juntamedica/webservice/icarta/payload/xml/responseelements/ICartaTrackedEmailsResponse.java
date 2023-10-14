package com.juntamedica.webservice.icarta.payload.xml.responseelements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "trackedEmails")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trackedEmails", propOrder = {
        "trackedEmailResponse"
})
public class ICartaTrackedEmailsResponse {

    private List<ICartaTackedEmailResponse> trackedEmailResponse = new ArrayList<>();
}
