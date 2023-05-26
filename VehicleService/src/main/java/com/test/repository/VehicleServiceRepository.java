package com.test.repository;

import com.test.entity.VehicleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleServiceRepository extends CrudRepository<VehicleService, Integer> {
}
