package com.github.carniwar.geocoding.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GeocodeData extends ClientResponse {

    private String address;

    private String latitude;

    private String longitude;

    /*
     * Constructors
     */

    public GeocodeData() {
        super();
    }

    public GeocodeData(String errorMessage) {
        super(errorMessage);
    }

    public GeocodeData(String address, String latitude, String longitude) {
        this();
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /*
     * Getters && Setters
     */

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeocodeData that = (GeocodeData) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(isSuccess(), that.isSuccess()) &&
                Objects.equals(getErrorMessage(), that.getErrorMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, latitude, longitude, isSuccess(), getErrorMessage());
    }

    @Override
    public String toString() {
        return "GeocodeData{" +
                "address='" + address + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", success='" + isSuccess() + '\'' +
                ", errorMessage='" + getErrorMessage() + '\'' +
                '}';
    }
}
