package com.backend.request;

import lombok.Data;

@Data
public class CriterionRequest {
    private Long id;
    private Long criterionTypeId;
    private Long comparingConditionTypeId;
    private String conditionValue;
}
