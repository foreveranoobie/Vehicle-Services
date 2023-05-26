package com.test.service;

import com.test.dto.VehicleServiceCreateDto;
import com.test.entity.VehicleService;

import java.util.List;
import java.util.Optional;

public interface VehicleServiceService {
    Optional<VehicleService> findById(int id);

    List<VehicleService> findAll();

    int save(VehicleServiceCreateDto vehicleServiceCreateDto);
}
