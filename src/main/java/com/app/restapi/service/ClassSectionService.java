package com.app.restapi.service;

import com.app.restapi.jpa.entity.Employee;
import com.app.restapi.jpa.entity.Section;
import com.app.restapi.jpa.repo.EmployeeRepository;
import com.app.restapi.jpa.repo.SectionRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassSectionService {

    private final EmployeeRepository employeeRepository;
    private final SectionRepository sectionRepository;

    public  ClassSectionService(EmployeeRepository employeeRepository, SectionRepository sectionRepository) {
        this.employeeRepository = employeeRepository;
        this.sectionRepository = sectionRepository;
    }

    public Section assignTeacherToSection(Long sectionId, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (!"TEACHER".equals(employee.getRole())) {
            throw new IllegalArgumentException("Selected employee is not a teacher!");
        }

        Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new RuntimeException("Section not found"));
        section.setClassTeacher(employee);

        return sectionRepository.save(section);
    }
}
