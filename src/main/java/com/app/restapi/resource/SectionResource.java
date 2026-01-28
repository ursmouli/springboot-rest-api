package com.app.restapi.resource;

import com.app.restapi.converter.SectionConverter;
import com.app.restapi.dto.SectionDto;
import com.app.restapi.jpa.entity.Employee;
import com.app.restapi.jpa.entity.Section;
import com.app.restapi.jpa.repo.EmployeeRepository;
import com.app.restapi.jpa.repo.SchoolClassRepository;
import com.app.restapi.jpa.repo.SectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sections")
public class SectionResource {

    private final SectionRepository sectionRepository;
    private final EmployeeRepository employeeRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final SectionConverter sectionConverter;

    public SectionResource(SectionRepository sectionRepository,
                           SchoolClassRepository classRepository,
                           SectionConverter sectionConverter,
                           EmployeeRepository employeeRepository) {
        this.sectionRepository = sectionRepository;
        this.employeeRepository = employeeRepository;
        this.schoolClassRepository = classRepository;
        this.sectionConverter = sectionConverter;
    }

    @PostMapping("/class/{classId}")
    public ResponseEntity<SectionDto> createSection(
            @PathVariable Long classId,
            @RequestBody Section section) {

        return schoolClassRepository.findById(classId).map(schoolClass -> {
            section.setSchoolClass(schoolClass); // Link Section to Class
            Section savedSection = sectionRepository.save(section);
            return new ResponseEntity<>(sectionConverter.toDto(savedSection), HttpStatus.CREATED);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{sectionId}/assign-teacher/{employeeId}")
    public ResponseEntity<SectionDto> assignTeacher(
            @PathVariable Long sectionId,
            @PathVariable Long employeeId) {

        return sectionRepository.findById(sectionId).map(section -> {
            Employee teacher = employeeRepository.findById(employeeId)
                    .filter(e -> "TEACHER".equals(e.getRole()))
                    .orElseThrow(() -> new RuntimeException("Teacher not found or invalid role"));

            section.setClassTeacher(teacher);
            return ResponseEntity.ok(sectionConverter.toDto(sectionRepository.save(section)));
        }).orElse(ResponseEntity.notFound().build());
    }

}
