package com.test.entity;

import com.test.entity.enums.ProvidedService;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "provided_service")
public class ProvidedServiceEntity {
    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private ProvidedService name;

    @ManyToMany(mappedBy = "providedServices")
    private List<VehicleService> vehicles;

    public ProvidedServiceEntity() {}

    public ProvidedServiceEntity(ProvidedService name, List<VehicleService> vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }

    public int getId() {
        return id;
    }

    public ProvidedService getName() {
        return name;
    }

    public List<VehicleService> getVehicles() {
        return vehicles;
    }
}
