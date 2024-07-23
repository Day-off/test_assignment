package com.backend.dto;

import lombok.Data;

@Data
public class ComparingConditionTypeDto {
    private Long id;
    private String conditionName;
    private Long criterionTypeId;
}
