package com.app.restapi.service;

import com.app.restapi.converter.VehicleConverter;
import com.app.restapi.dto.VehicleDto;
import com.app.restapi.jpa.entity.Vehicle;
import com.app.restapi.jpa.repo.RouteRepository;
import com.app.restapi.jpa.repo.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleConverter vehicleConverter;

    @Autowired
    private RouteRepository routeRepository;

    public List<VehicleDto> getAllVehicles() {
        return vehicleConverter.toDtoList(vehicleRepository.findAll());
    }

    public VehicleDto add(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleConverter.toEntity(vehicleDto);
        return vehicleConverter.toDto(vehicleRepository.save(vehicle));
    }

    public VehicleDto update(Long id, VehicleDto vehicleDto) {
        Vehicle vehicle = getVehicleById(id);

        vehicle.setRegistrationNumber(vehicleDto.getRegistrationNumber());
        vehicle.setCapacity(vehicleDto.getCapacity());
        vehicle.setDriverName(vehicleDto.getDriverName());

        return vehicleConverter.toDto(vehicleRepository.save(vehicle));
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with ID: " + id));
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);

        if (vehicle.getRoute() != null) {
            throw new IllegalStateException("Cannot delete vehicle assigned to Route: " + vehicle.getRoute().getName());
        }

        vehicleRepository.delete(vehicle);
    }
}
