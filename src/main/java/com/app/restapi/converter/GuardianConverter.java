package com.app.restapi.converter;

import com.app.restapi.dto.GuardianDto;
import com.app.restapi.jpa.entity.Guardian;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

@Component
public class GuardianConverter implements GenericConverter<Guardian, GuardianDto> {

    @Override
    public Guardian toEntity(GuardianDto dto) {
        if (dto == null) return null;
        return new Guardian()
                .setId(dto.getId())
                .setName(dto.getName())
                .setEmail(dto.getEmail())
                .setPhone(dto.getPhone())
                .setRelation(dto.getRelation());
    }

    @Override
    public GuardianDto toDto(Guardian entity) {
        if (entity == null) return null;
        return new GuardianDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .setPhone(entity.getPhone())
                .setRelation(entity.getRelation());
    }
}
