package com.backend.rest;


import com.backend.dto.ComparingConditionTypeDto;
import com.backend.dto.CriterionTypeDto;
import com.backend.service.CriterionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/criterion")
@CrossOrigin(origins = "http://localhost:5173/", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CriterionRestController {

    private final CriterionService criterionService;

    @GetMapping("/type")
    public List<CriterionTypeDto> getTypes() {
        return criterionService.getAllCriterionTypes();
    }

    @GetMapping("/condition-type")
    public List<ComparingConditionTypeDto> getConditionTypes() {
        return criterionService.getAllComparingConditionTypes();
    }
}
