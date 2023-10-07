package com.test.mapper;

import com.test.dto.VehicleServiceCreateDto;
import com.test.entity.ProvidedServiceEntity;
import com.test.entity.VehicleService;
import com.test.entity.VehicleTypeEntity;
import com.test.repository.ProvidedServiceRepository;
import com.test.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class VehicleServiceMapper {
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private ProvidedServiceRepository providedServiceRepository;

    public VehicleService fromCreateDto(VehicleServiceCreateDto vehicleServiceCreateDto) {
        Set<VehicleTypeEntity> vehicleTypes = new HashSet<>(vehicleTypeRepository.findByNameIn(vehicleServiceCreateDto.getVehicleTypes()));
        Set<ProvidedServiceEntity> providedServices = new HashSet<>(providedServiceRepository.findByNameIn(vehicleServiceCreateDto.getProvidedServices()));
        return new VehicleService(vehicleServiceCreateDto.getName(),
                vehicleServiceCreateDto.getCountry(),
                vehicleServiceCreateDto.getCity(),
                vehicleServiceCreateDto.getAddress(),
                vehicleTypes,
                providedServices);
    }
}
