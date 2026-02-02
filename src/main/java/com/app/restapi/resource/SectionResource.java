package com.app.restapi.resource;

import com.app.restapi.converter.SectionConverter;
import com.app.restapi.dto.PaginationDto;
import com.app.restapi.dto.SectionDto;
import com.app.restapi.jpa.entity.Employee;
import com.app.restapi.jpa.entity.SectionId;
import com.app.restapi.jpa.repo.EmployeeRepository;
import com.app.restapi.jpa.repo.SectionRepository;
import com.app.restapi.model.AppRole;
import com.app.restapi.service.SectionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sections")
public class SectionResource {

    private final SectionRepository sectionRepository;
    private final EmployeeRepository employeeRepository;
    private final SectionConverter sectionConverter;

    private final SectionService sectionService;

    public SectionResource(SectionRepository sectionRepository,
                           SectionConverter sectionConverter,
                           EmployeeRepository employeeRepository,
                           SectionService sectionService) {
        this.sectionRepository = sectionRepository;
        this.employeeRepository = employeeRepository;
        this.sectionConverter = sectionConverter;

        this.sectionService = sectionService;
    }

    @PostMapping("/add")
    public ResponseEntity<SectionDto> createSection(
            @RequestBody SectionDto section) {
        return ResponseEntity.ok(sectionService.createClassSection(section));
    }

    @PostMapping("/update")
    public ResponseEntity<SectionDto> updateSection(
            @RequestBody SectionDto section) {
        return ResponseEntity.ok(sectionService.createClassSection(section));
    }

    @PostMapping("/all")
    public ResponseEntity<Page<SectionDto>> getSections(@RequestBody PaginationDto pagination) {
        return ResponseEntity.ok(sectionService.fetchSections(pagination));
    }

    @PutMapping("/{classId}/assign-teacher/{employeeId}")
    public ResponseEntity<SectionDto> assignTeacher(
            @PathVariable Long classId,
            @PathVariable Long employeeId) {

        return sectionRepository.findById(new SectionId().setSchoolClass(classId).setClassTeacher(employeeId)).map(section -> {
            Employee teacher = employeeRepository.findById(employeeId)
                    .filter(e -> AppRole.TEACHER.equals(e.getRole()))
                    .orElseThrow(() -> new RuntimeException("Teacher not found or invalid role"));

            section.setClassTeacher(teacher);
            return ResponseEntity.ok(sectionConverter.toDto(sectionRepository.save(section)));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{classId}/{teacherId}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long classId, @PathVariable Long teacherId) {
        sectionService.deleteClassSection(classId, teacherId);
        return ResponseEntity.noContent().build();
    }

}
