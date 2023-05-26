package com.test.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "vehicle_service")
public class VehicleService implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String country;
    @Column
    private String city;
    @Column
    private String address;

    public VehicleService() {
    }

    public VehicleService(String name, String country, String city, String address) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    VehicleService(int id, String name, String country, String city, String address) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleService vehicleService = (VehicleService) o;
        return getId() == vehicleService.getId() && Objects.equals(getName(), vehicleService.getName()) && Objects.equals(getCountry(), vehicleService.getCountry()) && Objects.equals(getCity(), vehicleService.getCity()) && Objects.equals(getAddress(), vehicleService.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCountry(), getCity(), getAddress());
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
