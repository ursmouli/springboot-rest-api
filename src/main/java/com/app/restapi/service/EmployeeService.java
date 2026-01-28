package com.app.restapi.service;

import ch.qos.logback.core.util.StringUtil;
import com.app.restapi.converter.EmployeeConverter;
import com.app.restapi.dto.EmployeeDto;
import com.app.restapi.dto.PaginationDto;
import com.app.restapi.jpa.entity.Department;
import com.app.restapi.jpa.entity.Employee;
import com.app.restapi.jpa.entity.Student;
import com.app.restapi.jpa.repo.DepartmentRepository;
import com.app.restapi.jpa.repo.EmployeeRepository;
import com.app.restapi.jpa.specifications.EmployeeSpecification;
import com.app.restapi.model.Roles;
import com.app.restapi.model.SortDirection;
import com.app.restapi.util.EntityUtil;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "firstName", "lastName", "createdDate");

    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;
    private final DepartmentRepository departmentRepository;

    public  EmployeeService(EmployeeRepository employeeRepository,
                            EmployeeConverter employeeConverter,
                            DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        final Employee employee = employeeConverter.toEntity(employeeDto);

        // Ensure the permanent/residential address is properly linked
        if (employee.getPermanentAddress() != null) {
            employee.getPermanentAddress().setEmployeePermanent(employee);
        }
        if (employee.getResidentialAddress() != null) {
            employee.getResidentialAddress().setEmployeeResidential(employee);
        }

        // Set the bidirectional relationship for guardians
        if (!employee.getGuardians().isEmpty()) {
            employee.getGuardians().forEach(guardian -> guardian.setEmployee(employee));
        }

        if (!employee.getSiblings().isEmpty()) {
            employee.getSiblings().forEach(sibling -> sibling.setEmployee(employee));
        }

        Employee dbEmployee = employeeRepository.save(employee);

        log.info("Employee save with Number {}", dbEmployee.getEmployeeNumber());

        return employeeConverter.toDto(dbEmployee);
    }

    @Transactional
    public EmployeeDto saveEmployeeToDepartment(EmployeeDto employeeDto) {

        if (StringUtil.isNullOrEmpty(employeeDto.getDepartmentName())) {
            throw new RuntimeException("Department name is required");
        }

        Employee employee = employeeRepository.findByEmployeeNumber(employeeDto.getEmployeeNumber()).orElseThrow(EntityNotFoundException::new);

        Optional<Department> dbDepartment = departmentRepository.findByNameEqualsIgnoreCase(employeeDto.getDepartmentName().toLowerCase());

        if (dbDepartment.isPresent()) {
            log.info("Department {} already exists adding employee {} to the department",
                    employeeDto.getDepartmentName(), employeeDto.getEmployeeNumber());

            employee.setDepartment(dbDepartment.get());
        } else {
            log.info("Department {} doesn't exists. Creating and adding employee {} to the department",
                    employeeDto.getDepartmentName(), employeeDto.getEmployeeNumber());

            Department newDepartment = departmentRepository.save(new Department().setName(employeeDto.getDepartmentName()));

            employee.setDepartment(newDepartment);
        }

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeConverter.toDto(savedEmployee);
    }

    @Transactional
    public EmployeeDto getEmployeeByNumber(String employeeNumber) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Employee with number " + employeeNumber + " not found."));

        return employeeConverter.toDto(employee);
    }

    @Transactional
    public Page<EmployeeDto> getEmployees(PaginationDto pagination) {
        log.debug("pagination: {}", pagination);
        if (!ALLOWED_SORT_FIELDS.contains(pagination.getSortField())) {
            throw new IllegalArgumentException("Invalid sort field: " + pagination.getSortField());
        }

        EntityUtil.isFieldExist(Student.class, pagination.getSortField());

        Sort sort = Sort.by(
                SortDirection.toSpringSortDirection(pagination.getSortDirection()),
                pagination.getSortField()
        );

        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize(), sort);

        Page<EmployeeDto> employees;
        if (pagination.getSearchTerm() != null && !pagination.getSearchTerm().trim().isEmpty()) {
            Specification<Employee> spec;

            if (pagination.getSearchTerm().toLowerCase().contains(":")) {
                spec = EmployeeSpecification.hasSearchField(pagination.getSearchTerm());
            } else {
                spec = EmployeeSpecification.hasSearchText(pagination.getSearchTerm());
            }

            Page<Employee> all = employeeRepository.findAll(spec, pageable);
            if (all == null || all.getTotalElements() == 0) {
                employees = Page.empty();
            } else {
                employees = all.map(employeeConverter::toDto);
            }
        } else {
            Page<Employee> all = employeeRepository.findAll(pageable);
            if (all.getTotalElements() == 0) {
                employees = Page.empty();
            } else {
                employees = all.map(employeeConverter::toDto);
            }
        }

        return employees;
    }

    public List<EmployeeDto> getTeachers() {
        // TODO replace string input with Enum
        return employeeConverter.toDtoList(employeeRepository.findByRole("TEACHER"));
    }
}
