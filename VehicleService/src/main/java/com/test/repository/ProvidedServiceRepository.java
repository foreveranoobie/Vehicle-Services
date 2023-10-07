package com.test.repository;

import com.test.entity.ProvidedServiceEntity;
import com.test.entity.enums.ProvidedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProvidedServiceRepository extends JpaRepository<ProvidedServiceEntity, Integer> {
    Set<ProvidedServiceEntity> findByNameIn(Set<ProvidedService> names);
}
