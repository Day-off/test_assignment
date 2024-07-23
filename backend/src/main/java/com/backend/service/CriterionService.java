package com.backend.service;

import com.backend.dto.ComparingConditionTypeDto;
import com.backend.dto.CriterionTypeDto;
import com.backend.mapper.CustomFilterMapper;
import com.backend.repository.ComparingConditionTypeRepository;
import com.backend.repository.CriterionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CriterionService {

    private final CriterionTypeRepository typeRepository;
    private final ComparingConditionTypeRepository comparingConditionTypeRepository;

    private final CustomFilterMapper customFilterMapper;

    public List<CriterionTypeDto> getAllCriterionTypes() {
        return typeRepository.findAll().stream()
                .map(customFilterMapper::toCriterionTypeDto).toList();
    }

    public List<ComparingConditionTypeDto> getAllComparingConditionTypes() {
        return comparingConditionTypeRepository.findAll().stream()
                .map(customFilterMapper::toComparingConditionTypeDto).toList();
    }
}
