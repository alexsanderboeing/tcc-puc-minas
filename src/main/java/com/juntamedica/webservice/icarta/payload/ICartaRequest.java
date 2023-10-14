package com.juntamedica.webservice.icarta.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ICartaRequest {

    private String appId;
    private String appSecret;
    private Long messageId;
    private String subject;
    private String emailFrom;
    private String nameFrom;
    private String emailTo;
    private String nameTo;
    private String content;
    private String nameAttachment;
    private String mimeTypeAttachment;
    private String base64Attachment;
}
