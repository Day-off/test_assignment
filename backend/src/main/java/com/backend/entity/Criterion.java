package com.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "criterion")
@Data
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "custom_filter_id", nullable = false)
    private CustomFilter filter;

    @ManyToOne
    @JoinColumn(name = "criterion_type_id", nullable = false)
    private CriterionType criterionType;

    @ManyToOne
    @JoinColumn(name = "comparing_condition_type_id", nullable = false)
    private ComparingConditionType comparingConditionType;

    private String conditionValue;
}
