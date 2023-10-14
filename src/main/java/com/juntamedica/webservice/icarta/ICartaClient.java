package com.juntamedica.webservice.icarta;

import com.juntamedica.webservice.icarta.payload.xml.ICartaSOAPRequest;
import com.juntamedica.webservice.icarta.payload.xml.ICartaSOAPResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "icarta", url = "${icarta.soap.url}", configuration = ICartaSOAPConfiguration.class)
public interface ICartaClient {

    @PostMapping(value = "TrackedEmailWSService", consumes = "text/xml;charset=UTF-8",
            produces = "text/xml;charset=UTF-8")
    ICartaSOAPResponse sendTrackedEmail(@RequestBody ICartaSOAPRequest request);
}
