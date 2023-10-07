package com.test.it.service;

import com.test.dto.VehicleServiceSearchDto;
import com.test.entity.VehicleService;
import com.test.filter.search.CriteriaOperation;
import com.test.filter.search.FilteringCriteria;
import com.test.it.BaseIT;
import com.test.service.impl.VehicleServiceServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VehicleServiceServiceIT extends BaseIT {
    @Autowired
    private VehicleServiceServiceImpl sut;

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Sql(scripts =  "/sql/add-vehicle-services-for-filtering.sql")
    @Sql(scripts = "/sql/clean-up-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldFindAllServices_whenFindingAllByCriteria_givenAbsentFilteringCriteria(){
        //given
        VehicleServiceSearchDto givenFilteringDto = new VehicleServiceSearchDto(new ArrayList<>(), 0, 10);

        //when
        List<VehicleService> actualServices = sut.findAllByCriteria(givenFilteringDto);

        //then
        assertThat(actualServices).hasSize(6);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Sql(scripts =  "/sql/add-vehicle-services-for-filtering.sql")
    @Sql(scripts = "/sql/clean-up-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldFindService_whenFindingServiceEqualsToId_givenValidFilteringCriteria(){
        //given
        int expectedId = 2;
        List<FilteringCriteria> givenFilteringCriteria = new ArrayList<>();
        givenFilteringCriteria.add(new FilteringCriteria<>("id", CriteriaOperation.EQUALS, expectedId));
        VehicleServiceSearchDto givenFilteringDto = new VehicleServiceSearchDto(givenFilteringCriteria, 0, 10);

        //when
        List<VehicleService> actualServices = sut.findAllByCriteria(givenFilteringDto);

        //then
        assertThat(actualServices).hasSize(1);
        assertThat(actualServices.get(0).getId()).isEqualTo(expectedId);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Sql(scripts =  "/sql/add-vehicle-services-for-filtering.sql")
    @Sql(scripts = "/sql/clean-up-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldFindServices_whenFindingServiceInIds_givenValidFilteringCriteria(){
        //given
        Integer[] givenIds = {1, 2};
        List<FilteringCriteria> givenFilteringCriteria = new ArrayList<>();
        givenFilteringCriteria.add(new FilteringCriteria<>("id", CriteriaOperation.VALUE_IN, givenIds));
        VehicleServiceSearchDto givenFilteringDto = new VehicleServiceSearchDto(givenFilteringCriteria, 0, 10);

        //when
        List<VehicleService> actualServices = sut.findAllByCriteria(givenFilteringDto);

        //then
        assertThat(actualServices).hasSize(2);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Sql(scripts =  "/sql/add-vehicle-services-for-filtering.sql")
    @Sql(scripts = "/sql/clean-up-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldFindServices_whenFindingService_givenValidFilteringCriteriaWithEqualCountryAndCityIn(){
        //given
        String givenCountry = "Country2";
        String[] givenCities = {"City2", "City4"};
        List<FilteringCriteria> givenFilteringCriteria = new ArrayList<>();
        givenFilteringCriteria.add(new FilteringCriteria<>("country", CriteriaOperation.EQUALS, givenCountry));
        givenFilteringCriteria.add(new FilteringCriteria<>("city", CriteriaOperation.VALUE_IN, givenCities));
        VehicleServiceSearchDto givenFilteringDto = new VehicleServiceSearchDto(givenFilteringCriteria, 0, 10);

        //when
        List<VehicleService> actualServices = sut.findAllByCriteria(givenFilteringDto);

        //then
        assertThat(actualServices).hasSize(3);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Sql(scripts =  "/sql/add-vehicle-services-for-filtering.sql")
    @Sql(scripts = "/sql/clean-up-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldReturnNumberOfAllServices_whenCountingAll(){
        //given
        //when
        Long actualAmount = sut.countAll();

        //then
        assertThat(actualAmount).isEqualTo(6);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Sql(scripts =  "/sql/add-vehicle-services-for-filtering.sql")
    @Sql(scripts = "/sql/clean-up-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldReturnNumberOfAllServices_whenCountingByCriteria_givenFilteringCriteria(){
        //given
        String givenCountry = "Country2";
        String[] givenCities = {"City2", "City4"};
        List<FilteringCriteria> givenFilteringCriteria = new ArrayList<>();
        givenFilteringCriteria.add(new FilteringCriteria<>("country", CriteriaOperation.EQUALS, givenCountry));
        givenFilteringCriteria.add(new FilteringCriteria<>("city", CriteriaOperation.VALUE_IN, givenCities));
        VehicleServiceSearchDto givenFilteringDto = new VehicleServiceSearchDto(givenFilteringCriteria, 0, 10);


        //when
        Long actualAmount = sut.countByCriteria(givenFilteringDto);

        //then
        assertThat(actualAmount).isEqualTo(3);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Sql(scripts =  "/sql/add-vehicle-services-for-filtering.sql")
    @Sql(scripts = "/sql/clean-up-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldReturnNumberOfAllServices_whenCountingByCriteria_givenEmptyCriteria(){
        //given
        VehicleServiceSearchDto givenFilteringDto = new VehicleServiceSearchDto(new ArrayList<>(), 0, 10);

        //when
        Long actualAmount = sut.countByCriteria(givenFilteringDto);

        //then
        assertThat(actualAmount).isEqualTo(6);
    }
}
