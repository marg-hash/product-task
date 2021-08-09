package com.product.productrestdemo;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.product.productrestdemo.model.Event;
import com.product.productrestdemo.model.RequestDetails;
 
@Configuration
public class ProductRestDemoConfig {
	@Bean
    public ItemReader<RequestDetails> detailsReader() {
        Jaxb2Marshaller detailsMarshaller = new Jaxb2Marshaller();
        detailsMarshaller.setClassesToBeBound(RequestDetails.class);
 
        return new StaxEventItemReaderBuilder<RequestDetails>()
                .name("detailsReader")
                .resource(new ClassPathResource("C:/Request.xml"))
                .addFragmentRootElements("RequestDetails")
                .unmarshaller(detailsMarshaller)
                .build();
    }

	@Bean
    public ItemReader<Event> eventReader() {
        Jaxb2Marshaller eventMarshaller = new Jaxb2Marshaller();
        eventMarshaller.setClassesToBeBound(Event.class);
 
        return new StaxEventItemReaderBuilder<Event>()
                .name("eventReader")
                .resource(new ClassPathResource("C:/Request.xml"))
                .addFragmentRootElements("Event")
                .unmarshaller(eventMarshaller)
                .build();
    }
}
