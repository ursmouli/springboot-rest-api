package com.app.restapi.converter;

import com.app.restapi.dto.SchoolClassDto;
import com.app.restapi.jpa.entity.SchoolClass;
import org.springframework.stereotype.Component;

@Component
public class SchoolClassConverter implements GenericConverter<SchoolClass, SchoolClassDto> {
    @Override
    public SchoolClass toEntity(SchoolClassDto dto) {
        return new SchoolClass().setId(dto.getId()).setName(dto.getName());
    }

    @Override
    public SchoolClassDto toDto(SchoolClass entity) {
        return new SchoolClassDto().setId(entity.getId()).setName(entity.getName());
    }
}
