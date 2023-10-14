package com.juntamedica.webservice.cfm;

import com.juntamedica.webservice.cfm.payload.CfmSOAPRequest;
import com.juntamedica.webservice.cfm.payload.CfmSOAPResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ServicoConsultaMedicos",
        url = "${cfm.soap.url}",
        configuration = CfmSOAPConfiguration.class)
public interface CfmClient {

    @PostMapping(value = "ServicoConsultaMedicos",
            consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    CfmSOAPResponse consultaInformacoes(@RequestBody CfmSOAPRequest request);
}
