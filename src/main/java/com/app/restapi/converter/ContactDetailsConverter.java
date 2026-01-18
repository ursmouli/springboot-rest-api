package com.app.restapi.converter;

import com.app.restapi.dto.ContactDetailsDto;
import com.app.restapi.jpa.entity.ContactDetails;
import org.springframework.stereotype.Component;

@Component
public class ContactDetailsConverter implements GenericConverter<ContactDetails, ContactDetailsDto> {

    @Override
    public ContactDetails toEntity(ContactDetailsDto dto) {
        if (dto == null) return null;
        return new ContactDetails()
                .setFirstName(dto.getFirstName())
                .setMiddleName(dto.getMiddleName())
                .setLastName(dto.getLastName())
                .setEmail(dto.getEmail())
                .setPassword(dto.getPassword())
                .setDob(dto.getDateOfBirth());
    }

    @Override
    public ContactDetailsDto toDto(ContactDetails entity) {
        if (entity == null) return null;
        return new ContactDetailsDto()
                .setFirstName(entity.getFirstName())
                .setMiddleName(entity.getMiddleName())
                .setLastName(entity.getLastName())
                .setDateOfBirth(entity.getDob())
                .setEmail(entity.getEmail());
    }
}
