package com.backend.request;

import lombok.Data;

import java.util.List;

@Data
public class CustomFilterRequest {
    private Long id;
    private String name;
    private List<CriterionRequest> criteria;
}
