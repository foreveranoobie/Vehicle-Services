package com.test.service.impl;

import com.test.dto.VehicleServiceCreateDto;
import com.test.dto.VehicleServiceSearchDto;
import com.test.entity.VehicleService;
import com.test.mapper.VehicleServiceMapper;
import com.test.repository.VehicleServiceRepository;
import com.test.service.VehicleServiceService;
import com.test.service.filter.search.FilteringCriteriaSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceServiceImpl implements VehicleServiceService {
    private final VehicleServiceRepository vehicleServiceRepository;
    private final VehicleServiceMapper vehicleServiceMapper;
    private final FilteringCriteriaSearchService filteringCriteriaSearchService;

    @Autowired
    public VehicleServiceServiceImpl(VehicleServiceRepository vehicleServiceRepository, VehicleServiceMapper vehicleServiceMapper,
                                     FilteringCriteriaSearchService filteringCriteriaSearchService) {
        this.vehicleServiceRepository = vehicleServiceRepository;
        this.vehicleServiceMapper = vehicleServiceMapper;
        this.filteringCriteriaSearchService = filteringCriteriaSearchService;
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
    @Transactional
    public int save(VehicleServiceCreateDto vehicleServiceCreateDto) {
        VehicleService savedVehicleService = vehicleServiceRepository
                .save(vehicleServiceMapper.fromCreateDto(vehicleServiceCreateDto));
        return savedVehicleService.getId();
    }

    @Override
    public List<VehicleService> findAllByCriteria(VehicleServiceSearchDto dto) {
        Specification<VehicleService> specification = (root, query, criteriaBuilder)
                -> filteringCriteriaSearchService.getPredicateForCriteria(root, query, criteriaBuilder, dto.filteringCriterias());
        Pageable pageable = PageRequest.of(dto.pageNum(), dto.pageSize());
        return vehicleServiceRepository.findAll(specification, pageable).getContent();
    }

    @Override
    public Long countByCriteria(VehicleServiceSearchDto dto) {
        Specification<VehicleService> specification = (root, query, criteriaBuilder)
                -> filteringCriteriaSearchService.getPredicateForCriteria(root, query, criteriaBuilder, dto.filteringCriterias());
        return vehicleServiceRepository.count(specification);
    }

    @Override
    public Long countAll() {
        return vehicleServiceRepository.count();
    }
}
