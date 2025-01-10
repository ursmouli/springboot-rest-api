package com.app.restapi.converter;

import com.app.restapi.dto.AddressDto;
import com.app.restapi.jpa.entity.Address;
import com.app.restapi.jpa.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public Address toEntity(AddressDto dto) {
        return new Address()
                .setStreet(dto.getStreet())
                .setCity(dto.getCity())
                .setDistrict(dto.getDistrict())
                .setPostalCode(dto.getPostalCode())
                .setState(dto.getState())
                .setCountry(dto.getCountry());
    }

    public AddressDto toDto(Address entity) {
        return new AddressDto()
                .setId(entity.getId())
                .setStreet(entity.getStreet())
                .setCity(entity.getCity())
                .setDistrict(entity.getDistrict())
                .setPostalCode(entity.getPostalCode())
                .setState(entity.getState())
                .setCountry(entity.getCountry());
    }
}
