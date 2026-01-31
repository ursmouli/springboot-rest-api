package com.app.restapi.resource;

import com.app.restapi.dto.PaginationDto;
import com.app.restapi.dto.SchoolClassDto;
import com.app.restapi.service.ClassSectionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes")
public class SchoolClassResource {

    private final ClassSectionService  classSectionService;

    public SchoolClassResource(ClassSectionService classSectionService) {
        this.classSectionService = classSectionService;
    }

    @PostMapping("/add")
    public ResponseEntity<SchoolClassDto> createClass(@RequestBody SchoolClassDto schoolClass) {
        SchoolClassDto savedClass = classSectionService.saveClass(schoolClass);
        return new ResponseEntity<>(savedClass, HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<Page<SchoolClassDto>> getAllClasses(@RequestBody @Validated PaginationDto paginationDto) {
        return ResponseEntity.ok(classSectionService.getAllClasses(paginationDto));
    }
}
