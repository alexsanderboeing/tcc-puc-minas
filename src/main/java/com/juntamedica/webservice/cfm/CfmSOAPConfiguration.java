package com.juntamedica.webservice.cfm;

import feign.Util;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jaxb.JAXBContextFactory;
import feign.soap.SOAPEncoder;
import org.springframework.context.annotation.Bean;

public class CfmSOAPConfiguration {

    private static final JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder()
            .withMarshallerJAXBEncoding("ISO-8859-1")
            .build();

    @Bean
    public Encoder feignEncoder() {
        return new SOAPEncoder(jaxbFactory);
    }

    @Bean
    public Decoder feignDecoder() {
        return (response, type) -> {
            String bodyStr = Util.toString(response.body().asReader(Util.ISO_8859_1));
            return CfmSOAPDecoder.getObjectFromSOAP(bodyStr, (Class<?>) type);
        };
    }
}
