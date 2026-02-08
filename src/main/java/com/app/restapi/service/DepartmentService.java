package com.app.restapi.service;

import com.app.restapi.converter.DepartmentConverter;
import com.app.restapi.dto.DepartmentDto;
import com.app.restapi.jpa.entity.Department;
import com.app.restapi.jpa.repo.DepartmentRepository;
import com.app.restapi.jpa.repo.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private DepartmentConverter departmentConverter;

    public DepartmentService(DepartmentRepository departmentRepository,
                             EmployeeRepository employeeRepository,
                             DepartmentConverter departmentConverter) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.departmentConverter = departmentConverter;
    }

    @Transactional
    public void saveDepartment(DepartmentDto departmentDto) {
        Department department = departmentConverter.toEntity(departmentDto);
        departmentRepository.save(department);
    }

    @Transactional
    public List<DepartmentDto> getDepartments() {
        return departmentConverter.toDtoList(departmentRepository.findAll());
    }

    @Transactional
    public DepartmentDto getDepartment(String name) {
        Department department = departmentRepository
                .findByNameEqualsIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Department with name " + name + " not found"));
        return departmentConverter.toDto(department);
    }
}
