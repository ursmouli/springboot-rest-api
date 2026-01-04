package com.app.restapi.resource;

import com.app.restapi.dto.LocationDto;
import com.app.restapi.jpa.entity.Country;
import com.app.restapi.jpa.entity.District;
import com.app.restapi.jpa.entity.State;
import com.app.restapi.jpa.entity.Taluk;
import com.app.restapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationResource {
    @Autowired
    private LocationService locationService;

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok(locationService.getAllCountries());
    }

    @PostMapping("/countries")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        return ResponseEntity.ok(locationService.saveCountry(country));
    }

    @GetMapping("/countries/{countryId}/states")
    public ResponseEntity<List<LocationDto>> getStatesByCountry(@PathVariable Long countryId) {
        return ResponseEntity.ok(locationService.getStatesByCountry(countryId));
    }

    @PostMapping("/countries/{countryId}/states")
    public ResponseEntity<State> createState(@PathVariable Long countryId, @RequestBody State state) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.saveState(countryId, state));
    }

    @GetMapping("/states/{stateId}/districts")
    public ResponseEntity<List<LocationDto>> getDistrictsByState(@PathVariable Long stateId) {
        return ResponseEntity.ok(locationService.getDistrictsByState(stateId));
    }

    @PostMapping("/states/{stateId}/districts")
    public ResponseEntity<District> createDistrict(@PathVariable Long stateId, @RequestBody District district) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.saveDistrict(stateId, district));
    }

    @GetMapping("/districts/{districtId}/taluks")
    public ResponseEntity<List<LocationDto>> getTaluksByDistrict(@PathVariable Long districtId) {
        return ResponseEntity.ok(locationService.getTaluksByDistrict(districtId));
    }

    @PostMapping("/districts/{districtId}/taluks")
    public ResponseEntity<Taluk> createTaluk(@PathVariable Long districtId, @RequestBody Taluk taluk) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.saveTaluk(districtId, taluk));
    }
}
