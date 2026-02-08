package com.app.restapi.resource;

import com.app.restapi.dto.PaginationDto;
import com.app.restapi.dto.SectionDto;
import com.app.restapi.service.SectionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionResource {

    private final SectionService sectionService;

    public SectionResource(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SectionDto>> getAll() {
        return ResponseEntity.ok(sectionService.fetchAll());
    }

    @PostMapping("/all")
    public ResponseEntity<Page<SectionDto>> getSections(@RequestBody PaginationDto pagination) {
        return ResponseEntity.ok(sectionService.fetchSections(pagination));
    }

    @PostMapping("/add")
    public ResponseEntity<SectionDto> createSection(
            @RequestBody SectionDto section) {
        return ResponseEntity.ok(sectionService.createClassSection(section));
    }

    @PostMapping("/update")
    public ResponseEntity<SectionDto> updateSection(
            @RequestBody SectionDto section) {
        return ResponseEntity.ok(sectionService.updateClassSection(section));
    }

    @PostMapping("/add-section-subject")
    public ResponseEntity<SectionDto> assignSubjectToSection(@RequestBody SectionDto sectionDto) {
        return ResponseEntity.ok(sectionService.assignSubjectsToSection(sectionDto));
    }

    @PutMapping("/{schoolClassId}/assign-teacher/{previousClassTeacher}/{newClassTeacherId}")
    public ResponseEntity<SectionDto> assignTeacher(
            @PathVariable Long schoolClassId,
            @PathVariable Long previousClassTeacher,
            @PathVariable Long newClassTeacherId) {

        return ResponseEntity.ok(sectionService.assignTeacherToClassSection(schoolClassId, previousClassTeacher, newClassTeacherId));
    }

    @DeleteMapping("/delete/{classId}/{teacherId}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long classId, @PathVariable Long teacherId) {
        sectionService.deleteClassSection(classId, teacherId);
        return ResponseEntity.noContent().build();
    }

}
