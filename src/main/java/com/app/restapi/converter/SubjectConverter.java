package com.app.restapi.converter;

import com.app.restapi.dto.SubjectDto;
import com.app.restapi.jpa.entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter implements GenericConverter<Subject, SubjectDto> {

    private final DepartmentConverter departmentConverter;

    public SubjectConverter(DepartmentConverter departmentConverter) {
        this.departmentConverter = departmentConverter;
    }

    @Override
    public Subject toEntity(SubjectDto dto) {
        return new Subject()
                .setId(dto.getId())
                .setCode(dto.getCode())
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setCredits(dto.getCredits());
    }

    @Override
    public SubjectDto toDto(Subject entity) {
        return new SubjectDto()
                .setId(entity.getId())
                .setCode(entity.getCode())
                .setName(entity.getName())
                .setDescription(entity.getDescription())
                .setCredits(entity.getCredits())
                .setDepartment(departmentConverter.toDto(entity.getDepartment()));
    }
}
