package com.app.restapi.resource;

import com.app.restapi.dto.SubjectDto;
import com.app.restapi.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectResource {

    private final SubjectService subjectService;

    public SubjectResource(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/add")
    public ResponseEntity<SubjectDto> addSubject(@RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok(subjectService.addSubject(subjectDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectDto>> findAll() {
        return ResponseEntity.ok(subjectService.getSubjects());
    }
}
