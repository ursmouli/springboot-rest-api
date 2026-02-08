package com.app.restapi.service;

import com.app.restapi.converter.SectionConverter;
import com.app.restapi.dto.*;
import com.app.restapi.jpa.entity.*;
import com.app.restapi.jpa.repo.EmployeeRepository;
import com.app.restapi.jpa.repo.SchoolClassRepository;
import com.app.restapi.jpa.repo.SectionRepository;
import com.app.restapi.jpa.repo.SubjectRepository;
import com.app.restapi.jpa.specifications.SectionSpecification;
import com.app.restapi.model.AppRole;
import com.app.restapi.model.SortDirection;
import com.app.restapi.util.EntityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SectionService {

    private static final Logger log = LoggerFactory.getLogger(SectionService.class);

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("name");

    private final SectionRepository sectionRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final SectionConverter sectionConverter;
    private final EmployeeRepository employeeRepository;
    private final SubjectRepository subjectRepository;

    public SectionService(SectionRepository sectionRepository,
                          SchoolClassRepository schoolClassRepository,
                          SectionConverter sectionConverter,
                          EmployeeRepository employeeRepository,
                          SubjectRepository subjectRepository) {
        this.sectionRepository = sectionRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.sectionConverter = sectionConverter;
        this.employeeRepository = employeeRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<SectionDto> fetchAll() {
        return sectionConverter.toDtoList(sectionRepository.findAll());
    }

    public SectionDto updateClassSection(SectionDto section) {
        Section entity = sectionConverter.toEntity(section);

        if (sectionRepository.existsBySchoolClassIdAndClassTeacherId(section.getSchoolClass().getId(), section.getClassTeacher().getId())) {
            throw new IllegalStateException("This teacher is already assigned to this class.");
        }

        entity.setSchoolClass(schoolClassRepository.getReferenceById(section.getSchoolClass().getId()));

        Employee employee = employeeRepository.findById(section.getClassTeacher().getId())
                .filter(e -> AppRole.TEACHER.equals(e.getRole())).
                orElseThrow(() -> new RuntimeException("Teacher not found or invalid role"));

        entity.setClassTeacher(employee);

        return sectionConverter.toDto(sectionRepository.save(entity));
    }

    public SectionDto createClassSection(SectionDto section) {

        Section entity = sectionConverter.toEntity(section);

        SchoolClass schoolClass = schoolClassRepository.getReferenceById(section.getSchoolClass().getId());
        entity.setSchoolClass(schoolClass);

        if (sectionRepository.existsBySchoolClassIdAndClassTeacherId(section.getSchoolClass().getId(), section.getClassTeacher().getId())) {
            throw new IllegalStateException("This teacher is already assigned to class " + section.getSchoolClass().getId());
        }

        Employee employee = employeeRepository.findById(section.getClassTeacher().getId())
                .filter(e -> AppRole.TEACHER.equals(e.getRole())).
                orElseThrow(() -> new RuntimeException("Teacher not found or invalid role"));

        entity.setClassTeacher(employee);

        entity.setId(new SectionId(schoolClass.getId(), section.getClassTeacher().getId()));

        return sectionConverter.toDto(sectionRepository.save(entity));
    }

    public SectionDto assignSubjectsToSection(SectionDto sectionDto) {

        // get section
        Section section = sectionRepository
                .findById(new SectionId(sectionDto.getSchoolClass().getId(), sectionDto.getClassTeacher().getId()))
                .orElseThrow(() -> new RuntimeException("Section not found"));

        // get subjects
        List<Long> subjectIds = new ArrayList<>();

        for (SubjectDto subjectDto : sectionDto.getSubjects()) {
            subjectIds.add(subjectDto.getId());
        }

        List<Subject> subjects = subjectRepository.findAllById(subjectIds);

        // save subjects to section
        SectionId sectionId = new SectionId(section.getSchoolClass().getId(), section.getClassTeacher().getId());

        for (Subject subject : subjects) {
            SectionSubject sectionSubject = new SectionSubject();
            sectionSubject.setId(new SectionSubjectId(sectionId, subject.getId()));
            sectionSubject.setSection(section);
            sectionSubject.setSubject(subject);

            section.getSectionSubjects().add(sectionSubject);
        }

        Section savedSection = sectionRepository.save(section);

        Set<SectionSubject> savedSectionSubjects = savedSection.getSectionSubjects();

        log.debug("Saved section subjects: {}", savedSectionSubjects);

        return sectionConverter.toDto(section);
    }

    public void deleteClassSection(Long classId, Long classTeacherId) {

        if (classId == null || classTeacherId == null) {
            throw new RuntimeException("ClassId and ClassTeacherId cannot be null");
        }

        // throw error if classId doesn't exist
        schoolClassRepository.findById(classId).orElseThrow(() -> new RuntimeException("Class not found"));

        // throw error classTeacherId doesn't exist
        employeeRepository.findById(classTeacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));

        sectionRepository.deleteBySchoolClassIdAndClassTeacherId(classId, classTeacherId);
    }

    public SectionDto assignTeacherToClassSection(Long schoolClassId, Long previousClassTeacher, Long newClassTeacherId) {
        if (schoolClassId == null || previousClassTeacher == null || newClassTeacherId == null) {
            throw new RuntimeException("SectionId and ClassTeacherId cannot be null");
        }

        return sectionRepository.findById(new SectionId(schoolClassId, previousClassTeacher)).map(section -> {
            Employee teacher = employeeRepository.findById(newClassTeacherId)
                    .filter(e -> AppRole.TEACHER.equals(e.getRole()))
                    .orElseThrow(() -> new RuntimeException("Teacher not found or invalid role"));

            section.setClassTeacher(teacher);
            return sectionConverter.toDto(sectionRepository.save(section));
        }).orElse(null);
    }

    public Page<SectionDto> fetchSections(PaginationDto pagination) {

        log.debug("pagination: {}", pagination);
        if (!ALLOWED_SORT_FIELDS.contains(pagination.getSortField())) {
            throw new IllegalArgumentException("Invalid sort field: " + pagination.getSortField());
        }

        EntityUtil.isFieldExist(Section.class, pagination.getSortField());

        Sort sort = Sort.by(
                SortDirection.toSpringSortDirection(pagination.getSortDirection()),
                pagination.getSortField()
        );

        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize(), sort);

        Page<SectionDto> sections;
        if (pagination.getSearchTerm() != null && !pagination.getSearchTerm().trim().isEmpty()) {
            Specification<Section> spec;

            if (pagination.getSearchTerm().toLowerCase().contains(":")) {
                spec = SectionSpecification.hasSearchField(pagination.getSearchTerm());
            } else {
                spec = SectionSpecification.hasSearchText(pagination.getSearchTerm());
            }

            Page<Section> all = sectionRepository.findAll(spec, pageable);
            if (all == null || all.getTotalElements() == 0) {
                sections = Page.empty();
            } else {
                sections = all.map(sectionConverter::toDto);
            }
        } else {
            Page<Section> all = sectionRepository.findAll(pageable);
            if (all.getTotalElements() == 0) {
                sections = Page.empty();
            } else {
                sections = all.map(sectionConverter::toDto);
            }
        }

        return sections;
    }
}
