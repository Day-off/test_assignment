package com.backend.repository;

import com.backend.entity.CustomFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomFilterRepository extends JpaRepository<CustomFilter, Long> {
}
