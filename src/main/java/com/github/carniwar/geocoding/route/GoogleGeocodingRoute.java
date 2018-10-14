package com.github.carniwar.geocoding.route;

import com.github.carniwar.geocoding.converter.GeocodeDataConverter;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.validation.PredicateValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleGeocodingRoute extends RouteBuilder {

    public static final String ROUTE_ENDPOINT = "jetty:http://0.0.0.0:8081/google/api/geocode";
    private static final String GOOGLE_ENDPOINT = "https://maps.googleapis.com/maps/api/geocode/xml";

    @Value("${google.api.key}")
    private String key;

    @Override
    public void configure() throws Exception {
        onException(PredicateValidationException.class)
                .handled(true)
                .logStackTrace(true)
                .bean(GeocodeDataConverter.class, "validationError")
                .marshal().json(JsonLibrary.Jackson)
        ;

        from(ROUTE_ENDPOINT)
            .validate(header("address").isNotNull())
            .validate(header("address").isNotEqualTo(""))

            .removeHeaders("CamelHttp*")
            .setHeader(Exchange.HTTP_QUERY, simple("address=${header.address}&key="+key))
            .to("jetty:"+GOOGLE_ENDPOINT)

            .bean(GeocodeDataConverter.class, "convert")
            .marshal().json(JsonLibrary.Jackson)
        ;
    }

}
