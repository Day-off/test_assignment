package com.backend.dto;

import lombok.Data;

@Data
public class CriterionDto {
    private Long id;
    private CriterionTypeDto criterionType;
    private ComparingConditionTypeDto comparingConditionType;
    private String conditionValue;
}

