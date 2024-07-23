package com.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comparing_condition_type")
@Data
public class ComparingConditionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "criterion_type_id", nullable = false)
    private CriterionType criterionType;

    private String conditionName;
}
