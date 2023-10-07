package com.test.dto;

import com.test.filter.search.FilteringCriteria;

import java.util.List;

public record VehicleServiceSearchDto(List<FilteringCriteria> filteringCriterias, int pageNum, int pageSize) {

}
