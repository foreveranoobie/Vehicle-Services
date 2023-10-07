package com.test.service.impl.filter.search;

import com.test.filter.search.CriteriaOperation;
import com.test.filter.search.FilteringCriteria;
import com.test.filter.search.command.FilteringCommand;
import com.test.filter.search.command.impl.EqualsFilteringCommand;
import com.test.filter.search.command.impl.ValueInFilteringCommand;
import com.test.service.filter.search.FilteringCriteriaSearchService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * {@inheritDoc}
 */
@Service
public class FilteringCriteriaSearchServiceImpl implements FilteringCriteriaSearchService {

    Map<CriteriaOperation, FilteringCommand> filteringCommandsForOperations;

    public FilteringCriteriaSearchServiceImpl(){
        filteringCommandsForOperations = new HashMap<>();
        filteringCommandsForOperations.put(CriteriaOperation.EQUALS, new EqualsFilteringCommand());
        filteringCommandsForOperations.put(CriteriaOperation.VALUE_IN, new ValueInFilteringCommand());
    }

    @Override
    public Predicate getPredicateForCriteria(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<FilteringCriteria> criteria) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (FilteringCriteria criterion : criteria) {
            predicates.add(filteringCommandsForOperations.get(criterion.getOperation()).getPredicateForFilteringOperation(root, criterion.getFieldName(), criterion.getValues(), builder));
        }
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
