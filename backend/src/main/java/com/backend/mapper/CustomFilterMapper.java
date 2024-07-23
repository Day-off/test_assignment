package com.backend.mapper;

import com.backend.dto.CriterionDto;
import com.backend.dto.CriterionTypeDto;
import com.backend.dto.ComparingConditionTypeDto;
import com.backend.dto.CustomFilterDto;
import com.backend.entity.Criterion;
import com.backend.entity.CriterionType;
import com.backend.entity.ComparingConditionType;
import com.backend.entity.CustomFilter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomFilterMapper {

    List<CustomFilterDto> toCustomFilterDtos(List<CustomFilter> customFilters);

    CriterionTypeDto toCriterionTypeDto(CriterionType criterionType);

    Criterion toCriterion(CriterionDto criterionDto);

    CriterionType toCriterionType(CriterionTypeDto criterionType);

    @Mappings({
            @Mapping(source = "criterionType.id", target = "criterionTypeId")
    })
    ComparingConditionTypeDto toComparingConditionTypeDto(ComparingConditionType comparingConditionType);

    @Mappings({
            @Mapping(source = "criterionTypeId", target = "criterionType.id")
    })
    ComparingConditionType toComparingConditionType(ComparingConditionTypeDto comparingConditionType);
}
