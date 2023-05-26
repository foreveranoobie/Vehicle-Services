package com.test.service.impl;

import com.test.dto.VehicleServiceCreateDto;
import com.test.entity.VehicleService;
import com.test.mapper.VehicleServiceMapper;
import com.test.repository.VehicleServiceRepository;
import com.test.service.VehicleServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceServiceImpl implements VehicleServiceService {
    private VehicleServiceRepository vehicleServiceRepository;

    @Autowired
    public VehicleServiceServiceImpl(VehicleServiceRepository vehicleServiceRepository) {
        this.vehicleServiceRepository = vehicleServiceRepository;
    }


    @Override
    public Optional<VehicleService> findById(int id) {
        return vehicleServiceRepository.findById(id);
    }

    @Override
    public List<VehicleService> findAll() {
        List<VehicleService> vehicleServices = new ArrayList<>();
        vehicleServiceRepository.findAll().forEach(vehicleServices::add);
        return vehicleServices;
    }

    @Override
    public int save(VehicleServiceCreateDto vehicleServiceCreateDto) {
        VehicleService savedVehicleService = vehicleServiceRepository
                .save(VehicleServiceMapper.fromCreateDto(vehicleServiceCreateDto));
        return savedVehicleService.getId();
    }
}
