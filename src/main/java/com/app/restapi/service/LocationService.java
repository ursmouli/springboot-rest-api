package com.app.restapi.service;

import com.app.restapi.dto.LocationDto;
import com.app.restapi.jpa.entity.Country;
import com.app.restapi.jpa.entity.District;
import com.app.restapi.jpa.entity.State;
import com.app.restapi.jpa.entity.Taluk;
import com.app.restapi.jpa.repo.CountryRepository;
import com.app.restapi.jpa.repo.DistrictRepository;
import com.app.restapi.jpa.repo.StateRepository;
import com.app.restapi.jpa.repo.TalukRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private TalukRepository talukRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public List<LocationDto> getStatesByCountry(Long countryId) {
        return stateRepository.findByCountryId(countryId).stream()
                .map(state -> new LocationDto(state.getId(), state.getName()))
                .collect(Collectors.toList());
    }

    public List<LocationDto> getDistrictsByState(Long stateId) {
        return districtRepository.findByStateId(stateId).stream()
                .map(district -> new LocationDto(district.getId(), district.getName()))
                .collect(Collectors.toList());
    }

    public List<LocationDto> getTaluksByDistrict(Long districtId) {
        return talukRepository.findByDistrictId(districtId).stream()
                .map(taluk -> new LocationDto(taluk.getId(), taluk.getName()))
                .collect(Collectors.toList());
    }

    public Taluk saveTaluk(Long districtId, Taluk taluk) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new EntityNotFoundException("District not found with id " + districtId));

        Long stateId = district.getState().getId();

        boolean exists = talukRepository.existsByNameIgnoreCaseAndDistrictStateId(taluk.getName(), stateId);

        if (exists) {
            throw new DuplicateKeyException("Taluk with name '" + taluk.getName() + "' already exists in this State" + district.getName() + "'.");
        }

        taluk.setDistrict(district);
        return talukRepository.save(taluk);
    }

    public State saveState(Long countryId, State state) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id " + countryId));

        boolean exists = stateRepository.existsByNameIgnoreCaseAndCountryId(state.getName(), countryId);

        if (exists) {
            throw new DuplicateKeyException("State with name '" + state.getName() + "' already exists in this Country '" +  country.getName() + "'.");
        }

        state.setCountry(country);
        return stateRepository.save(state);
    }

    public District saveDistrict(Long stateId, District district) {
        State state = stateRepository.findById(stateId).orElseThrow(() -> new EntityNotFoundException("State not found with id " + stateId));

        boolean exists = districtRepository.existsByNameIgnoreCaseAndStateId(district.getName(), stateId);

        if (exists) {
            throw new DuplicateKeyException("District with name '" + district.getName() + "' already exists in this State '" + state.getName() + "'.");
        }

        district.setState(state);
        return districtRepository.save(district);
    }

    public Country saveCountry(Country country) {
        boolean exists = countryRepository.existsByNameIgnoreCase(country.getName());

        if (exists) {
            throw new DuplicateKeyException("Country with name '" + country.getName() + "' already exists.");
        }

        return countryRepository.save(country);
    }
}