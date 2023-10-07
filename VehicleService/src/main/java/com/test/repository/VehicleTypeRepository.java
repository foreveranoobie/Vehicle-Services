package com.test.repository;

import com.test.entity.VehicleTypeEntity;
import com.test.entity.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleTypeEntity, Integer> {
    Set<VehicleTypeEntity> findByNameIn(Set<VehicleType> names);

    VehicleTypeEntity findByName(VehicleType name);
}
