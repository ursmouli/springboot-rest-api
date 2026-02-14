package com.app.restapi.converter;

import com.app.restapi.dto.DepartmentDto;
import com.app.restapi.jpa.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter implements GenericConverter<Department, DepartmentDto> {
    @Override
    public Department toEntity(DepartmentDto dto) {
        return new Department()
                .setName(dto.getName())
                .setCode(dto.getCode())
                .setDescription(dto.getDescription());
    }

    @Override
    public DepartmentDto toDto(Department entity) {
        if (entity == null) return null;
        return new DepartmentDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setCode(entity.getCode())
                .setDescription(entity.getDescription());
    }
}
