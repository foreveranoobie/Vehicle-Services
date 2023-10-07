package com.test.entity;

import com.test.entity.enums.VehicleType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vehicle_type")
public class VehicleTypeEntity {
    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private VehicleType name;

    @ManyToMany(mappedBy = "vehicleTypes")
    private List<VehicleService> vehicles;

    public VehicleTypeEntity() {}

    public VehicleTypeEntity(VehicleType name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public VehicleType getName() {
        return name;
    }

    public void setName(VehicleType name) {
        this.name = name;
    }
}
