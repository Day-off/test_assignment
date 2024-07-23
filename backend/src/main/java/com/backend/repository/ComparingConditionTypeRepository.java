package com.backend.repository;

import com.backend.entity.ComparingConditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComparingConditionTypeRepository extends JpaRepository<ComparingConditionType, Long> {
}
