package com.juntamedica.webservice.htmltopdf;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "htmltopdf", url = "${htmltopdf.url}", configuration = HtmlToPdfConfiguration.class)
public interface HtmlToPdfClient {

    @PostMapping(value = "api/converter/html-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    byte[] convert(@RequestPart("html") MultipartFile file);
}
