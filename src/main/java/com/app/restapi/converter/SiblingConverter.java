package com.app.restapi.converter;

import com.app.restapi.dto.SiblingDto;
import com.app.restapi.jpa.entity.Sibling;
import org.springframework.stereotype.Component;

@Component
public class SiblingConverter implements GenericConverter<Sibling, SiblingDto> {
    public SiblingConverter() {}


    @Override
    public Sibling toEntity(SiblingDto dto) {
        if (dto == null) return null;
        return new Sibling()
                .setId(dto.getId())
                .setName(dto.getName())
                .setInstitutionName(dto.getInstitutionName())
                .setInstitutionPlace(dto.getInstitutionPlace());
    }

    @Override
    public SiblingDto toDto(Sibling entity) {
        if (entity == null) return null;
        return new SiblingDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDob(entity.getDob())
                .setInstitutionName(entity.getInstitutionName())
                .setInstitutionPlace(entity.getInstitutionPlace());
    }
}
