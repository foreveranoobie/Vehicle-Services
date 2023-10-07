package com.test.filter.search.command.impl;

import com.test.filter.search.command.FilteringCommand;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ValueInFilteringCommand implements FilteringCommand<Object[]> {

    @Override
    public <E> Predicate getPredicateForFilteringOperation(Root<E> rootEntity, String fieldName, Object[] fieldValue, CriteriaBuilder criteriaBuilder) {
        return rootEntity.get(fieldName).in(fieldValue);
    }
}
