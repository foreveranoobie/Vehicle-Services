package com.test.it.controller;

import com.test.controller.VehicleServiceController;
import com.test.dto.VehicleServiceCreateDto;
import com.test.entity.VehicleService;
import com.test.entity.enums.ProvidedService;
import com.test.entity.enums.VehicleType;
import com.test.exception.ResourceNotFoundException;
import com.test.it.BaseIT;
import com.test.repository.VehicleServiceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

public class VehicleServiceControllerIT extends BaseIT {
    @Autowired
    private VehicleServiceController sut;

    @Autowired
    private VehicleServiceRepository vehicleServiceRepository;

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Sql(scripts = "/sql/clean-up-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldCreateVehicle() {
        //given
        Set<VehicleType> expectedVehicleTypes = new HashSet<>(List.of(VehicleType.CARS, VehicleType.EVs, VehicleType.BUSES, VehicleType.TRUCKS, VehicleType.MOTORCYCLES, VehicleType.MOTORCYCLES));
        Set<ProvidedService> expectedProvidedServices = new HashSet<>(List.of(ProvidedService.BASIC, ProvidedService.CHASSIS, ProvidedService.ELECTRICAL, ProvidedService.ENGINES));

        VehicleServiceCreateDto vehicleServiceCreateDto = new VehicleServiceCreateDto("name", "country", "city", "address", expectedVehicleTypes, expectedProvidedServices);

        //when
        int id = sut.createVehicleService(vehicleServiceCreateDto);

        //then
        VehicleService actualVehicleService = vehicleServiceRepository.findById(id).get();
        assertThat(actualVehicleService).extracting("id", "name", "country", "city", "address").containsExactly(id, "name", "country", "city", "address");
        assertThat(actualVehicleService.getVehicleTypes()).extracting("name").containsExactlyInAnyOrderElementsOf(expectedVehicleTypes);
        assertThat(actualVehicleService.getProvidedServices()).extracting("name").containsExactlyInAnyOrderElementsOf(expectedProvidedServices);
    }

    @Test
    public void shouldThrowValidationExceptionOnIdEqualsToZero() {
        //given
        int id = 0;

        //when
        Throwable throwable = catchThrowableOfType(() -> sut.getVehicleServiceById(id), ResourceNotFoundException.class);

        //then
        System.out.println();
    }

    @Test
    public void shouldThrowValidationExceptionOnNullVehicleServiceCreateDto() {
        //given

        //when
        sut.createVehicleService(null);

        //then

    }
}
