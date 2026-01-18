package com.app.restapi.converter;

import com.app.restapi.dto.AddressDto;
import com.app.restapi.jpa.entity.*;
import com.app.restapi.jpa.repo.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter implements GenericConverter<Address, AddressDto> {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private TalukRepository talukRepository;

    @Override
    public Address toEntity(AddressDto dto) {
        if (dto == null) return null;
        return new Address()
                .setHouseNumber(dto.getHouseNumber())
                .setStreet(dto.getStreet())
                .setLandMark(dto.getLandMark())
                .setPlace(dto.getPlace())
                .setCountry(toCountryEntity(dto.getCountryId()))
                .setState(toStateEntity(dto.getStateId()))
                .setDistrict(toDistrictEntity(dto.getDistrictId()))
                .setTaluk(toTalukEntity(dto.getTalukId()))
                .setPostalCode(dto.getPostalCode())
                .setAddressLine1(dto.getAddressLine1());
    }

    @Override
    public AddressDto toDto(Address entity) {
        if (entity == null) return null;
        return new AddressDto()
                .setId(entity.getId())
                .setHouseNumber(entity.getHouseNumber())
                .setStreet(entity.getStreet())
                .setLandMark(entity.getLandMark())
                .setPlace(entity.getPlace())
                .setPostalCode(entity.getPostalCode())
                .setCountryId(toCountryDto(entity.getCountry()))
                .setStateId(toStateDto(entity.getState()))
                .setDistrictId(toDistrictDto(entity.getDistrict()))
                .setTalukId(toTalukDto(entity.getTaluk()))
                .setAddressLine1(entity.getAddressLine1());
    }

    public Long toDistrictDto(District district) {
        return district.getId();
    }

    public Long toTalukDto(Taluk taluk) {
        return taluk.getId();
    }

    public District toDistrictEntity(Long districtId) {
        return districtRepository.findById(districtId).orElseThrow(() -> new EntityNotFoundException("District not found"));
    }

    public Taluk toTalukEntity(Long talukId) {
        return talukRepository.findById(talukId).orElseThrow(() -> new EntityNotFoundException("Taluk not found"));
    }

    public Long toStateDto(State state) {
        return state.getId();
    }

    public State toStateEntity(Long stateId) {
        return stateRepository.findById(stateId).orElseThrow(() -> new EntityNotFoundException("State not found"));
    }

    public Long toCountryDto(Country country){
        return country.getId();
    }

    public Country toCountryEntity(Long countryId) {
        return countryRepository.findById(countryId).orElseThrow(() -> new EntityNotFoundException("Country not found"));
    }
}
