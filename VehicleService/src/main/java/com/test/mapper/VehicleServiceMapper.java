package com.test.mapper;

import com.test.dto.VehicleServiceCreateDto;
import com.test.entity.VehicleService;

public class VehicleServiceMapper {
    public static VehicleService fromCreateDto(VehicleServiceCreateDto vehicleServiceCreateDto) {
        return new VehicleService(vehicleServiceCreateDto.getName(),
                vehicleServiceCreateDto.getCountry(),
                vehicleServiceCreateDto.getCity(),
                vehicleServiceCreateDto.getAddress());
    }
}
