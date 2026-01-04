package com.app.restapi.converter;

import com.app.restapi.dto.AddressDto;
import com.app.restapi.jpa.entity.Address;
import com.app.restapi.jpa.entity.Country;
import com.app.restapi.jpa.entity.District;
import com.app.restapi.jpa.entity.State;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public Address toEntity(AddressDto dto) {
        return new Address()
                .setStreet(dto.getStreet())
                .setCity(dto.getCity())
                .setDistrict(toDistrictEntity(dto.getDistrict()))
                .setPostalCode(dto.getPostalCode())
                .setState(toStateEntity(dto.getState()))
                .setCountry(toCountryEntity(dto.getCountry()));
    }

    public AddressDto toDto(Address entity) {
        return new AddressDto()
                .setId(entity.getId())
                .setStreet(entity.getStreet())
                .setCity(entity.getCity())
                .setDistrict(toDistrictDto(entity.getDistrict()))
                .setPostalCode(entity.getPostalCode())
                .setState(toStateDto(entity.getState()))
                .setCountry(toCountryDto(entity.getCountry()));
    }

    public String toDistrictDto(District district) {
        return district.getName();
    }

    public District toDistrictEntity(String name) {
        return new District().setName(name);
    }

    public String toStateDto(State state) {
        return state.getName();
    }

    public State toStateEntity(String name) {
        return new State().setName(name);
    }

    public String toCountryDto(Country country){
        return country.getName();
    }

    public Country toCountryEntity(String name) {
        return new Country().setName(name);
    }
}
