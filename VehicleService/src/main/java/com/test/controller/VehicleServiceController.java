package com.test.controller;

import com.test.dto.VehicleServiceCreateDto;
import com.test.entity.VehicleService;
import com.test.exception.ResourceNotFoundException;
import com.test.service.VehicleServiceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/vehicle-service")
public class VehicleServiceController {
    @Autowired
    private VehicleServiceService vehicleServiceService;

    @GetMapping("/{id}")
    public VehicleService getVehicleServiceById(@PathVariable @DecimalMin(value = "1.0") Integer id) {
        return vehicleServiceService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Vehicle service with id %d not found", id)));
    }

    @PostMapping
    public Integer createVehicleService(@RequestBody @NotNull @Valid VehicleServiceCreateDto createDto) {
        return vehicleServiceService.save(createDto);
    }

    @GetMapping
    public List<VehicleService> getAllVehicleServices() {
        return vehicleServiceService.findAll();
    }
}
