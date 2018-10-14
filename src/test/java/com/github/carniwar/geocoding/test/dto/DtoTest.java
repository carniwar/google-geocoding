package com.github.carniwar.geocoding.test.dto;

import com.github.carniwar.geocoding.dto.ClientResponse;
import com.github.carniwar.geocoding.dto.GeocodeData;
import com.github.carniwar.geocoding.test.GeocodingTestUnit;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Assertions;

@GeocodingTestUnit
public class DtoTest {

    @Test
    public void test_ClientResponse_ExpectsPojoMethodsAreAllWellImplemented() {
        Assertions.assertPojoMethodsFor(ClientResponse.class).areWellImplemented();
    }

    @Test
    public void test_GeocodeData_ExpectsPojoMethodsAreAllWellImplemented() {
        Assertions.assertPojoMethodsFor(GeocodeData.class).areWellImplemented();
    }

}
