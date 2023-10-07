package com.test.filter.search;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
//@AllArgsConstructor
public class FilteringCriteria<V> {
    private String fieldName;
    private CriteriaOperation operation;
    private V values;

    public FilteringCriteria(String fieldName, CriteriaOperation operation, V values){
        this.fieldName = fieldName;
        this.operation = operation;
        this.values = values;
    }
}
