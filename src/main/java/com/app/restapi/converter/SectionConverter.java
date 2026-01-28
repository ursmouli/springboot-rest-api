package com.app.restapi.converter;

import com.app.restapi.dto.SectionDto;
import com.app.restapi.jpa.entity.Section;
import org.springframework.stereotype.Component;

@Component
public class SectionConverter implements GenericConverter<Section, SectionDto> {

    private final EmployeeConverter employeeConverter;
    private final SchoolClassConverter schoolClassConverter;

    public SectionConverter(EmployeeConverter employeeConverter,
                            SchoolClassConverter schoolClassConverter) {
        this.employeeConverter = employeeConverter;
        this.schoolClassConverter = schoolClassConverter;
    }

    @Override
    public Section toEntity(SectionDto dto) {
        return new Section()
                .setId(dto.getId())
                .setName(dto.getName())
                .setClassTeacher(employeeConverter.toEntity(dto.getClassTeacher()))
                .setSchoolClass(schoolClassConverter.toEntity(dto.getSchoolClass()));
    }

    @Override
    public SectionDto toDto(Section entity) {
        return new SectionDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setClassTeacher(employeeConverter.toDto(entity.getClassTeacher()))
                .setSchoolClass(schoolClassConverter.toDto(entity.getSchoolClass()));
    }
}
