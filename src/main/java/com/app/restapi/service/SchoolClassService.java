package com.app.restapi.service;

import com.app.restapi.converter.SchoolClassConverter;
import com.app.restapi.dto.PaginationDto;
import com.app.restapi.dto.SchoolClassDto;
import com.app.restapi.jpa.entity.Employee;
import com.app.restapi.jpa.entity.SchoolClass;
import com.app.restapi.jpa.entity.Section;
import com.app.restapi.jpa.repo.EmployeeRepository;
import com.app.restapi.jpa.repo.SchoolClassRepository;
import com.app.restapi.jpa.repo.SectionRepository;
import com.app.restapi.jpa.specifications.SchoolClassSpecification;
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

import java.util.List;
import java.util.Set;

@Service
public class SchoolClassService {

    private static final Logger log = LoggerFactory.getLogger(SchoolClassService.class);

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("name", "academicYear");

    private final EmployeeRepository employeeRepository;
    private final SectionRepository sectionRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final SchoolClassConverter schoolClassConverter;

    public  SchoolClassService(EmployeeRepository employeeRepository,
                                SectionRepository sectionRepository,
                                SchoolClassRepository schoolClassRepository,
                                SchoolClassConverter schoolClassConverter) {
        this.employeeRepository = employeeRepository;
        this.sectionRepository = sectionRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.schoolClassConverter = schoolClassConverter;
    }


    @Transactional
    public SchoolClassDto saveClass(SchoolClassDto schoolClass) {

        SchoolClass savedClass = schoolClassRepository.save(schoolClassConverter.toEntity(schoolClass));

        return schoolClassConverter.toDto(savedClass);
    }

    @Transactional
    public Page<SchoolClassDto> getAllClasses(PaginationDto  pagination) {

        log.debug("pagination: {}", pagination);
        if (!ALLOWED_SORT_FIELDS.contains(pagination.getSortField())) {
            throw new IllegalArgumentException("Invalid sort field: " + pagination.getSortField());
        }

        EntityUtil.isFieldExist(SchoolClass.class, pagination.getSortField());

        Sort sort = Sort.by(
                SortDirection.toSpringSortDirection(pagination.getSortDirection()),
                pagination.getSortField()
        );

        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize(), sort);

        Page<SchoolClassDto> schoolClasses;
        if (pagination.getSearchTerm() != null && !pagination.getSearchTerm().trim().isEmpty()) {
            Specification<SchoolClass> spec;

            if (pagination.getSearchTerm().toLowerCase().contains(":")) {
                spec = SchoolClassSpecification.hasSearchField(pagination.getSearchTerm());
            } else {
                spec = SchoolClassSpecification.hasSearchText(pagination.getSearchTerm());
            }

            Page<SchoolClass> all = schoolClassRepository.findAll(spec, pageable);
            if (all == null || all.getTotalElements() == 0) {
                schoolClasses = Page.empty();
            } else {
                schoolClasses = all.map(schoolClassConverter::toDto);
            }
        } else {
            Page<SchoolClass> all = schoolClassRepository.findAll(pageable);
            if (all.getTotalElements() == 0) {
                schoolClasses = Page.empty();
            } else {
                schoolClasses = all.map(schoolClassConverter::toDto);
            }
        }

        return schoolClasses;
    }

    public List<SchoolClassDto> getSchoolClasses() {
        return schoolClassConverter.toDtoList(schoolClassRepository.findAll());
    }
}
