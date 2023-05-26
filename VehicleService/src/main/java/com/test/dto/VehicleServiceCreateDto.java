package com.test.dto;

import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object of @{@link com.test.entity.VehicleService}
 */
public class VehicleServiceCreateDto {
    @NotNull
    private String name;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String address;

    public VehicleServiceCreateDto(String name, String country, String city, String address) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
