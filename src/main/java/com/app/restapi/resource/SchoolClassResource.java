package com.app.restapi.resource;

import com.app.restapi.converter.SchoolClassConverter;
import com.app.restapi.dto.SchoolClassDto;
import com.app.restapi.jpa.entity.SchoolClass;
import com.app.restapi.jpa.repo.SchoolClassRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class SchoolClassResource {

    private final SchoolClassRepository classRepository;
    private final SchoolClassConverter schoolClassConverter;

    public SchoolClassResource(SchoolClassRepository classRepository,
                               SchoolClassConverter schoolClassConverter) {
        this.classRepository = classRepository;
        this.schoolClassConverter = schoolClassConverter;
    }

    @PostMapping
    public ResponseEntity<SchoolClassDto> createClass(@RequestBody SchoolClass schoolClass) {
        SchoolClass savedClass = classRepository.save(schoolClass);
        return new ResponseEntity<>(schoolClassConverter.toDto(savedClass), HttpStatus.CREATED);
    }

    @GetMapping
    public List<SchoolClassDto> getAllClasses() {
        return schoolClassConverter.toDtoList(classRepository.findAll());
    }
}
