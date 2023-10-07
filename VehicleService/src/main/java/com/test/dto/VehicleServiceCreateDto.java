package com.test.dto;

import com.test.entity.enums.ProvidedService;
import com.test.entity.enums.VehicleType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Data Transfer Object of @{@link com.test.entity.VehicleService}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleServiceCreateDto {
    @NotNull
    private String name;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String address;
    @NotNull
    private Set<VehicleType> vehicleTypes;
    @NotNull
    private Set<ProvidedService> providedServices;
}
