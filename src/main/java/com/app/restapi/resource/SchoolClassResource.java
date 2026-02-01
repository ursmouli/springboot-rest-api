package com.app.restapi.resource;

import com.app.restapi.dto.PaginationDto;
import com.app.restapi.dto.SchoolClassDto;
import com.app.restapi.service.SchoolClassService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class SchoolClassResource {

    private final SchoolClassService schoolClassService;

    public SchoolClassResource(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @PostMapping("/add")
    public ResponseEntity<SchoolClassDto> createClass(@RequestBody SchoolClassDto schoolClass) {
        SchoolClassDto savedClass = schoolClassService.saveClass(schoolClass);
        return new ResponseEntity<>(savedClass, HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<Page<SchoolClassDto>> getAllClasses(@RequestBody @Validated PaginationDto paginationDto) {
        return ResponseEntity.ok(schoolClassService.getAllClasses(paginationDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SchoolClassDto>> getClasses() {
        return ResponseEntity.ok(schoolClassService.getSchoolClasses());
    }
}
