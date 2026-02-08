package com.app.restapi.service;

import com.app.restapi.converter.DepartmentConverter;
import com.app.restapi.dto.DepartmentDto;
import com.app.restapi.jpa.entity.Department;
import com.app.restapi.jpa.repo.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService {

    private static final Logger LOG = LoggerFactory.getLogger(DepartmentService.class);

    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;

    public DepartmentService(DepartmentRepository departmentRepository,
                             DepartmentConverter departmentConverter) {
        this.departmentRepository = departmentRepository;
        this.departmentConverter = departmentConverter;
    }


    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = departmentConverter.toEntity(departmentDto);
        return departmentConverter.toDto(departmentRepository.save(department));
    }

    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        if (departmentDto.getId() == null) {
            throw new RuntimeException("Department id is null");
        }

        Department department = departmentConverter.toEntity(departmentDto);

        departmentRepository.save(department);
        return departmentConverter.toDto(department);
    }

    public void deleteDepartment(Long id) {
        if (id  == null) {
            throw new RuntimeException("Department id is null");
        }

        Department department = departmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        departmentRepository.deleteById(id);

        LOG.info("Department with id {}, name {} has been deleted", department.getId(), department.getName());
    }

    public List<DepartmentDto> getDepartments() {
        return departmentConverter.toDtoList(departmentRepository.findAll());
    }

    public DepartmentDto getDepartment(String name) {
        Department department = departmentRepository
                .findByNameEqualsIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Department with name " + name + " not found"));
        return departmentConverter.toDto(department);
    }
}
