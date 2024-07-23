package com.backend.service;

import com.backend.dto.CriterionDto;
import com.backend.dto.CustomFilterDto;
import com.backend.entity.Criterion;
import com.backend.entity.CustomFilter;
import com.backend.mapper.CustomFilterMapper;
import com.backend.repository.CriterionRepository;
import com.backend.repository.CustomFilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomFilterService {

    private final CustomFilterRepository customFilterRepository;
    private final CriterionRepository criterionRepository;
    private final CustomFilterMapper customFilterMapper;

    public List<CustomFilterDto> getAllFilters() {
        List<CustomFilter> filters = customFilterRepository.findAll();
        return customFilterMapper.toCustomFilterDtos(filters);
    }

    @Transactional
    public void saveCustomFilter(CustomFilterDto requestDto) {
        CustomFilter customFilter = new CustomFilter();
        customFilter.setName(requestDto.getName());

        for (CriterionDto criterionDto : requestDto.getCriteria()) {
            Criterion criterion = customFilterMapper.toCriterion(criterionDto);
            criterion.setFilter(customFilter);
            customFilter.getCriteria().add(criterion);
        }

        customFilterRepository.save(customFilter);
    }

    @Transactional
    public void updateCustomFilter(CustomFilterDto requestDto) {
        CustomFilter customFilter = customFilterRepository.findById(requestDto.getId())
                .orElseThrow(() -> new RuntimeException("CustomFilter not found"));

        customFilter.setName(requestDto.getName());

        updateCriteria(requestDto, customFilter);
        removeDeletedCriteria(requestDto, customFilter);

        criterionRepository.saveAll(customFilter.getCriteria());
        customFilterRepository.save(customFilter);
    }

    private void updateCriteria(CustomFilterDto requestDto, CustomFilter customFilter) {
        Map<Long, Criterion> existingCriteriaMap = customFilter.getCriteria().stream()
                .collect(Collectors.toMap(Criterion::getId, c -> c));

        for (CriterionDto criterionDto : requestDto.getCriteria()) {
            if (criterionDto.getId() != null) {
                Criterion existingCriterion = existingCriteriaMap.get(criterionDto.getId());
                if (existingCriterion != null) {
                    updateCriterionFields(criterionDto, existingCriterion);
                } else {
                    throw new RuntimeException("Criterion with ID " + criterionDto.getId() + " not found");
                }
            } else {
                Criterion newCriterion = customFilterMapper.toCriterion(criterionDto);
                newCriterion.setFilter(customFilter);
                customFilter.getCriteria().add(newCriterion);
            }
        }
    }

    private void updateCriterionFields(CriterionDto criterionDto, Criterion criterion) {
        criterion.setCriterionType(customFilterMapper.toCriterionType(criterionDto.getCriterionType()));
        criterion.setComparingConditionType(customFilterMapper.toComparingConditionType(criterionDto.getComparingConditionType()));
        criterion.setConditionValue(criterionDto.getConditionValue());
    }

    private void removeDeletedCriteria(CustomFilterDto requestDto, CustomFilter customFilter) {
        List<Criterion> customFilterWithoutNewCriteria = customFilter.getCriteria().stream()
                .filter(c -> c.getId() != null)
                .toList();

        List<CriterionDto> requestWithoutNewCriteria = requestDto.getCriteria().stream()
                .filter(c -> c.getId() != null)
                .toList();

        List<Criterion> criteriaToRemove = customFilterWithoutNewCriteria.stream()
                .filter(c -> requestWithoutNewCriteria.stream()
                        .noneMatch(r -> r.getId() != null && r.getId().equals(c.getId())))
                .collect(Collectors.toList());

        if (!criteriaToRemove.isEmpty()) {
            criterionRepository.deleteAll(criteriaToRemove);
            customFilter.getCriteria().removeAll(criteriaToRemove);
        }
    }

    @Transactional
    public void deleteCustomFilter(Long id) {
        CustomFilter customFilter = customFilterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomFilter not found"));

        List<Criterion> criteria = customFilter.getCriteria();
        if (criteria != null && !criteria.isEmpty()) {
            criterionRepository.deleteAll(criteria);
        }

        customFilterRepository.delete(customFilter);
    }
}
