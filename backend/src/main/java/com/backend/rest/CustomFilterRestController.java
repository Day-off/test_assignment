package com.backend.rest;

import com.backend.dto.CustomFilterDto;
import com.backend.service.CustomFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filters")
@CrossOrigin(origins = "http://localhost:5173/", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CustomFilterRestController {

    private final CustomFilterService customFilterService;

    @GetMapping
    public Page<CustomFilterDto> getAllFilters(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return customFilterService.getAllFilters(page, size);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> createCustomFilter(@RequestBody CustomFilterDto filterRequest) {
        customFilterService.saveCustomFilter(filterRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCustomFilter(@RequestBody CustomFilterDto requestDto) {
        customFilterService.updateCustomFilter(requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFilter(@PathVariable Long id) {
        customFilterService.deleteCustomFilter(id);
        return ResponseEntity.ok().build();
    }

}