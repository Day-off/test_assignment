package com.backend.repository;

import com.backend.entity.CriterionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterionTypeRepository extends JpaRepository<CriterionType, Long> {
}
