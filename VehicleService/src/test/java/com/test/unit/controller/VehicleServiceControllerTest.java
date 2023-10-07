package com.test.unit.controller;

import com.test.controller.VehicleServiceController;
import com.test.exception.ResourceNotFoundException;
import com.test.service.VehicleServiceService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.catchThrowableOfType;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceControllerTest {
    @Mock
    private VehicleServiceService vehicleServiceService;

    @InjectMocks
    private VehicleServiceController sut;

    @Test
    public void shouldThrowValidationExceptionOnIdEqualsToZero() {
        //given
        int id = 0;

        //when
        Throwable throwable = catchThrowableOfType(() -> sut.getVehicleServiceById(id), ResourceNotFoundException.class);

        //then
        Assertions.assertThat(throwable).hasMessage("Vehicle service with id 0 not found");
    }
}
