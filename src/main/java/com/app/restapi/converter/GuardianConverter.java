package com.app.restapi.converter;

import com.app.restapi.dto.GuardianDto;
import com.app.restapi.jpa.entity.Guardian;
import org.springframework.stereotype.Component;

@Component
public class GuardianConverter {

    public GuardianDto toDto(Guardian entity) {
        return new GuardianDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .setPhone(entity.getPhone())
                .setRelation(entity.getRelation());
    }
}
