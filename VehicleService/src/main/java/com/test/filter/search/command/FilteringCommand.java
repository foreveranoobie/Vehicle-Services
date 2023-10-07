package com.test.filter.search.command;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface FilteringCommand<V> {
    <E> Predicate getPredicateForFilteringOperation(Root<E> rootEntity, String fieldName, V fieldValue, CriteriaBuilder criteriaBuilder);
}
