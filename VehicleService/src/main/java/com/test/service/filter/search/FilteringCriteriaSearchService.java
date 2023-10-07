package com.test.service.filter.search;

import com.test.filter.search.FilteringCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

/**
 * Service responsible for building Predicate for filtering-out entities
 */
public interface FilteringCriteriaSearchService {
    Predicate getPredicateForCriteria(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<FilteringCriteria> criteria);
}
