package com.test.it.controller;

import com.test.controller.VehicleServiceController;
import com.test.dto.VehicleServiceCreateDto;
import com.test.entity.VehicleService;
import com.test.exception.ResourceNotFoundException;
import com.test.it.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

public class VehicleServiceControllerIT extends BaseIT {
    @Autowired
    private VehicleServiceController sut;

    @Test
    @Sql(scripts = "/sql/clean-up-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldCreateVehicle() {
        //given
        VehicleServiceCreateDto vehicleServiceCreateDto = new VehicleServiceCreateDto("name", "country", "city", "address");

        //when
        int id = sut.createVehicleService(vehicleServiceCreateDto);

        //then
        VehicleService actualVehicleService = sut.getVehicleServiceById(id);
        assertThat(actualVehicleService).extracting("id", "name", "country", "city", "address")
                .containsExactly(id, "name", "country", "city", "address");
    }

    @Test
    public void shouldThrowValidationExceptionOnIdEqualsToZero(){
        //given
        int id = 0;

        //when
        Throwable throwable = catchThrowableOfType(() -> sut.getVehicleServiceById(id), ResourceNotFoundException.class);

        //then
        System.out.println("");
    }

    @Test
    public void shouldThrowValidationExceptionOnNullVehicleServiceCreateDto(){
        //given

        //when
        sut.createVehicleService(null);

        //then

    }
}
