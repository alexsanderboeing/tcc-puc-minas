package com.juntamedica.webservice.icarta.factory;

import com.juntamedica.webservice.icarta.payload.ICartaRequest;
import com.juntamedica.webservice.icarta.payload.ICartaResponse;
import com.juntamedica.webservice.icarta.payload.xml.ICartaSOAPRequest;
import com.juntamedica.webservice.icarta.payload.xml.ICartaSOAPResponse;
import com.juntamedica.webservice.icarta.payload.xml.requestelements.ICartaCredentials;
import com.juntamedica.webservice.icarta.payload.xml.requestelements.ICartaTrackedEmail;
import com.juntamedica.webservice.icarta.payload.xml.requestelements.ICartaTrackedEmailAttachment;
import com.juntamedica.webservice.icarta.payload.xml.requestelements.ICartaTrackedEmailAttachments;
import com.juntamedica.webservice.icarta.payload.xml.requestelements.ICartaTrackedEmailRecipient;
import com.juntamedica.webservice.icarta.payload.xml.requestelements.ICartaTrackedEmailRecipients;
import com.juntamedica.webservice.icarta.payload.xml.requestelements.ICartaTrackedEmailSender;
import com.juntamedica.webservice.icarta.payload.xml.requestelements.ICartaTrackedEmails;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class ICartaFactory {

    public ICartaSOAPRequest buildRequestXML(ICartaRequest iCartaRequest) {
        ICartaTrackedEmail trackedEmail = ICartaTrackedEmail.builder()
                .messageId(iCartaRequest.getMessageId().toString())
                .subject(iCartaRequest.getSubject())
                .content(iCartaRequest.getContent())
                .trackedResponse("NO")
                .sender(
                        ICartaTrackedEmailSender.builder()
                                .name(iCartaRequest.getNameFrom())
                                .email(iCartaRequest.getEmailFrom())
                                .build())
                .build();

        ICartaTrackedEmailRecipient recipient = ICartaTrackedEmailRecipient.builder()
                .type("BCC")
                .name(iCartaRequest.getNameTo())
                .email(iCartaRequest.getEmailTo())
                .build();

        trackedEmail.setRecipients(
                ICartaTrackedEmailRecipients.builder()
                        .recipient(Arrays.asList(recipient))
                        .build());

        if (iCartaRequest.getBase64Attachment() != null && !iCartaRequest.getBase64Attachment().isEmpty()) {
            ICartaTrackedEmailAttachment attachment = ICartaTrackedEmailAttachment.builder()
                    .fileName(iCartaRequest.getNameAttachment())
                    .contentType(iCartaRequest.getMimeTypeAttachment())
                    .content(iCartaRequest.getBase64Attachment())
                    .build();

            ICartaTrackedEmailAttachments attachments = ICartaTrackedEmailAttachments.builder()
                    .attachment(Arrays.asList(attachment))
                    .build();

            trackedEmail.setAttachments(attachments);
        }

        return ICartaSOAPRequest.builder()
                .credentials(ICartaCredentials.builder()
                        .appId(iCartaRequest.getAppId())
                        .appSecret(iCartaRequest.getAppSecret())
                        .build())
                .trackedEmails(
                        ICartaTrackedEmails.builder()
                                .trackedEmail(Arrays.asList(trackedEmail))
                                .build())
                .build();
    }

    public ICartaResponse buildResponse(ICartaSOAPResponse response) {
        return ICartaResponse.builder()
                .status(response.getStatus())
                .descricao(response.getDescription())
                .build();
    }
}
