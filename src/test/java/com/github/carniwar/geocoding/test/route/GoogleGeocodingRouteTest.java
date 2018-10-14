package com.github.carniwar.geocoding.test.route;

import com.github.carniwar.geocoding.Application;
import com.github.carniwar.geocoding.route.GoogleGeocodingRoute;
import junit.framework.TestCase;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class GoogleGeocodingRouteTest extends TestCase {

    @EndpointInject(uri = GoogleGeocodingRoute.ROUTE_ENDPOINT)
    protected ProducerTemplate template;

    @Test
    public void test_GoogleGeocodingRoute_ExpectsFailNoAddress() {
        String response = template.requestBody(createEndpoint(null), "", String.class);

        Assert.notNull(response);
        Assert.hasText(response, "\"success\":false");
        Assert.hasText(response, "\"errorMessage\":\"Missing/Invalid parameter: address\"");
    }

    @Test
    public void test_GoogleGeocodingRoute_ExpectsFailEmptyAddress() {
        String response = template.requestBody(createEndpoint(""), "", String.class);

        Assert.notNull(response);
        Assert.hasText(response, "\"success\":false");
        Assert.hasText(response, "\"errorMessage\":\"Missing/Invalid parameter: address\"");
    }

    @Test
    public void test_GoogleGeocodingRoute_ExpectsSuccessManyAddresses() {
        String response = template.requestBody(createEndpoint("mcdonalds"), "", String.class);

        Assert.notNull(response);
        Assert.hasText(response, "\"success\":true");
        Assert.hasText(response, "\"latitude\":\"37.0332889\"");
        Assert.hasText(response, "\"longitude\":\"-95.6194559\"");
    }

    @Test
    public void test_GoogleGeocodingRoute_ExpectsSuccessSingleAddresses() {
        String response = template.requestBody(createEndpoint("amsterdam"), "", String.class);

        Assert.notNull(response);
        Assert.hasText(response, "\"success\":true");
        Assert.hasText(response, "\"latitude\":\"52.3679843\"");
        Assert.hasText(response, "\"longitude\":\"4.9035614\"\"");
    }

    @Test
    public void test_GoogleGeocodingRoute_ExpectsSuccessSingleAddressesSpecialChar() {
        String response = template.requestBody(createEndpoint("Amsterdã"), "", String.class);

        Assert.notNull(response);
        Assert.hasText(response, "\"success\":true");
        Assert.hasText(response, "\"latitude\":\"52.3679843\"");
        Assert.hasText(response, "\"longitude\":\"4.9035614\"\"");
    }

    @Test
    public void test_GoogleGeocodingRoute_ExpectsSuccessSingleAddressesSpace() {
        String response = template.requestBody(createEndpoint("backbase amsterdam"), "", String.class);

        Assert.notNull(response);
        Assert.hasText(response, "\"success\":true");
        Assert.hasText(response, "\"latitude\":\"52.3712457\"");
        Assert.hasText(response, "\"longitude\":\"4.9278071\"\"");
    }

    @Test
    public void test_GoogleGeocodingRoute_ExpectsSuccessNoResult() {
        String response = template.requestBody(createEndpoint("asdflkjhasdflkjahsdfljahsdf"), "", String.class);

        Assert.notNull(response);
        Assert.hasText(response, "\"success\":true");
        Assert.doesNotContain(response, "latitude");
        Assert.doesNotContain(response, "longitude");
    }

    /*
     * Private Methods
     */

    private String createEndpoint(String address) {
        return address==null ? GoogleGeocodingRoute.ROUTE_ENDPOINT : String.format("%s?address=%s", GoogleGeocodingRoute.ROUTE_ENDPOINT, address);
    }

}
