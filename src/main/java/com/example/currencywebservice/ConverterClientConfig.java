package com.example.currencywebservice;

import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class ConverterClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.medium.article");
        return marshaller;
    }
    @Bean
    public ConverterClient converterClient(Jaxb2Marshaller marshaller) {
        ConverterClient client = new ConverterClient();
        client.setDefaultUri("http://currencyconverter.kowabunga.net/converter.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
