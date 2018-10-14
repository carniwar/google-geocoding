package com.github.carniwar.geocoding.converter;

import com.github.carniwar.geocoding.dto.GeocodeData;
import org.apache.camel.language.XPath;
import org.springframework.stereotype.Component;

@Component
public class GeocodeDataConverter {

    private static final String GEOCODE_VALIDATION_ERROR = "Missing/Invalid parameter: address";

    public GeocodeData convert(@XPath("/GeocodeResponse/result/formatted_address/text()") String address,
                               @XPath("/GeocodeResponse/result/geometry/location/lat/text()") String latitude,
                               @XPath("/GeocodeResponse/result/geometry/location/lng/text()") String longitude) {

        return new GeocodeData(address, latitude, longitude);
    }

    public GeocodeData validationError() {
        return new GeocodeData(GEOCODE_VALIDATION_ERROR);
    }

}
