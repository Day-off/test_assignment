package com.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class CustomFilterDto {
    private Long id;
    private String name;
    private List<CriterionDto> criteria;
}
