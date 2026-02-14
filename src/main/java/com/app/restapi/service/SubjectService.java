package com.app.restapi.service;

import com.app.restapi.converter.SubjectConverter;
import com.app.restapi.dto.SubjectDto;
import com.app.restapi.jpa.entity.Department;
import com.app.restapi.jpa.entity.SchoolClass;
import com.app.restapi.jpa.entity.Subject;
import com.app.restapi.jpa.repo.DepartmentRepository;
import com.app.restapi.jpa.repo.SchoolClassRepository;
import com.app.restapi.jpa.repo.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SchoolClassRepository  schoolClassRepository;
    private final SubjectConverter subjectConverter;
    private final DepartmentRepository departmentRepository;

    public SubjectService(SubjectRepository subjectRepository,
                          SchoolClassRepository schoolClassRepository,
                          SubjectConverter subjectConverter,
                          DepartmentRepository departmentRepository) {
        this.subjectRepository = subjectRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.subjectConverter = subjectConverter;
        this.departmentRepository = departmentRepository;
    }

    public List<SubjectDto> getSubjects() {
        return subjectConverter.toDtoList(subjectRepository.findAll());
    }

    @Transactional
    public SubjectDto addSubject(SubjectDto subjectDto) {

        Subject subject = subjectConverter.toEntity(subjectDto);

        if (subjectDto.getName() == null || subjectDto.getName().isEmpty()) {
            throw new RuntimeException("Subject name is empty");
        }

        if (subjectDto.getDepartment() == null) {
            throw new RuntimeException("Department reference is null");
        }

        Department department = departmentRepository.findById(subjectDto.getDepartment().getId()).orElseThrow(() -> new EntityNotFoundException("Department not found"));

        subject.setDepartment(department);

        Subject savedSubject = subjectRepository.save(subject);

        return subjectConverter.toDto(savedSubject);
    }

    @Transactional
    public void assignSubjectToClass(Long classId, Long subjectId) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

//        schoolClass.addSubject(subject);
        // Saved automatically due to @Transactional
    }
}
