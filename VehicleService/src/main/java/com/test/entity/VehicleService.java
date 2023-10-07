package com.test.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "vehicle_service")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vehicle_service_vehicle_type",
            joinColumns = @JoinColumn(name = "vehicle_service_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_type_id"))
    private Set<VehicleTypeEntity> vehicleTypes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vehicle_service_provided_services",
            joinColumns = @JoinColumn(name = "vehicle_service_id"),
            inverseJoinColumns = @JoinColumn(name = "provided_service_id"))
    private Set<ProvidedServiceEntity> providedServices;

    public VehicleService(String name, String country, String city, String address, Set<VehicleTypeEntity> vehicleTypes,
                          Set<ProvidedServiceEntity> providedServices) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.vehicleTypes = vehicleTypes;
        this.providedServices = providedServices;
    }
}
