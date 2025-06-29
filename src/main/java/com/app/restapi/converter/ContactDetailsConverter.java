package com.app.restapi.converter;

import com.app.restapi.dto.ContactDetailsDto;
import com.app.restapi.jpa.entity.ContactDetails;
import org.springframework.stereotype.Component;

@Component
public class ContactDetailsConverter {

    public ContactDetails toEntity(ContactDetailsDto contactDetailsDto) {
        return new ContactDetails()
                .setFirstName(contactDetailsDto.getFirstName())
                .setMiddleName(contactDetailsDto.getMiddleName())
                .setLastName(contactDetailsDto.getLastName())
                .setEmail(contactDetailsDto.getEmail())
                .setPassword(contactDetailsDto.getPassword())
                .setDob(contactDetailsDto.getDateOfBirth());
    }

    public ContactDetailsDto toDto(ContactDetails contactDetails) {
        return new ContactDetailsDto()
                .setFirstName(contactDetails.getFirstName())
                .setMiddleName(contactDetails.getMiddleName())
                .setLastName(contactDetails.getLastName())
                .setDateOfBirth(contactDetails.getDob())
                .setEmail(contactDetails.getEmail());
    }
}
