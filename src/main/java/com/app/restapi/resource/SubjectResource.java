package com.app.restapi.resource;

import com.app.restapi.dto.SubjectDto;
import com.app.restapi.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
