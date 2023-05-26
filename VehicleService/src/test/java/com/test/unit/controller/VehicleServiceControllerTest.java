package com.test.unit.controller;

import com.test.controller.VehicleServiceController;
import com.test.exception.ResourceNotFoundException;
import com.test.service.VehicleServiceService;
import jakarta.validation.ValidationException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;

@RunWith(JUnit4.class)
public class VehicleServiceControllerTest {
    @Mock
    private VehicleServiceService vehicleServiceService;

    @InjectMocks
    private VehicleServiceController sut;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldThrowValidationExceptionOnIdEqualsToZero() {
        //given
        int id = 0;

        //when
        Throwable throwable = catchThrowableOfType(() -> sut.getVehicleServiceById(id), ResourceNotFoundException.class);

        //then
        System.out.println("");
    }
}
