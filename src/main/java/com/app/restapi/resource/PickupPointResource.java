package com.app.restapi.resource;

import com.app.restapi.dto.PickupPointDto;
import com.app.restapi.jpa.entity.PickupPoint;
import com.app.restapi.service.PickupPointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pickup-point")
public class PickupPointResource {

    private final PickupPointService pickupPointService;

    public PickupPointResource(PickupPointService pickupPointService) {
        this.pickupPointService = pickupPointService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PickupPointDto>> getPickupPoints() {
        return ResponseEntity.ok(pickupPointService.getPickupPoints());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PickupPointDto> getPickupPointById(@PathVariable Long id) {
        return ResponseEntity.ok(pickupPointService.getPickupPointById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<PickupPointDto> add(@RequestBody PickupPointDto pickupPoint) {
        return new ResponseEntity<>(pickupPointService.add(pickupPoint), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PickupPointDto> update(@RequestBody Long id, @RequestBody PickupPointDto pickupPoint) {
        return ResponseEntity.ok(pickupPointService.update(id, pickupPoint));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        pickupPointService.deletePickupPoint(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Pickup point deleted successfully");

        return ResponseEntity.ok(response);
    }
}
