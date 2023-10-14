package com.juntamedica.webservice.icarta;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jaxb.JAXBContextFactory;
import feign.soap.SOAPDecoder;
import feign.soap.SOAPEncoder;
import org.springframework.context.annotation.Bean;

public class ICartaSOAPConfiguration {

    private static final JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder()
            .withMarshallerJAXBEncoding("UTF-8")
            .withMarshallerFormattedOutput(true)
            .withMarshallerFragment(true)
            .withMarshallerSchemaLocation("http://schemas.xmlsoap.org/soap/envelope/")
            .build();

    @Bean
    public Encoder feignEncoder() {
        return new SOAPEncoder(jaxbFactory);
    }

    @Bean
    public Decoder feignDecoder() {
        return new SOAPDecoder(jaxbFactory);
    }
}
