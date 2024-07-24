package com.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CriterionDto {
    private Long id;
    private CriterionTypeDto criterionType;
    private ComparingConditionTypeDto comparingConditionType;
    private Long conditionValueNumber;
    private String conditionValueText;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date conditionValueDate;
}

