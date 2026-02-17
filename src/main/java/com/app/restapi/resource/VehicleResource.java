package com.app.restapi.resource;

import com.app.restapi.dto.VehicleDto;
import com.app.restapi.jpa.entity.Vehicle;
import com.app.restapi.service.VehicleService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleResource {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDto>> getAll() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<VehicleDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<VehicleDto> create(@RequestBody VehicleDto vehicle) {
        return new ResponseEntity<>(vehicleService.add(vehicle), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VehicleDto> update(@PathVariable Long id, @RequestBody VehicleDto details) {
        return ResponseEntity.ok(vehicleService.update(id, details));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
