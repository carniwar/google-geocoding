package com.github.carniwar.geocoding.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class ClientResponse implements Serializable {

    private boolean success = true;

    private String errorMessage;

    /*
     * Constructors
     */

    public ClientResponse() {
        super();
    }

    public ClientResponse(String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
    }

    /*
     * Getters && Setters
     */

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientResponse that = (ClientResponse) o;
        return success == that.success &&
                Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, errorMessage);
    }

    @Override
    public String toString() {
        return "ClientResponse{" +
                "success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
