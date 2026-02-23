package com.app.restapi.converter;

import com.app.restapi.dto.VehicleDto;
import com.app.restapi.jpa.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleConverter implements GenericConverter<Vehicle, VehicleDto> {
    @Override
    public Vehicle toEntity(VehicleDto dto) {
        return new Vehicle()
                .setId(dto.getId())
                .setCapacity(dto.getCapacity())
                .setDriverName(dto.getDriverName())
                .setRegistrationNumber(dto.getRegistrationNumber());
    }

    @Override
    public VehicleDto toDto(Vehicle entity) {
        return new VehicleDto()
                .setId(entity.getId())
                .setCapacity(entity.getCapacity())
                .setDriverName(entity.getDriverName())
                .setRegistrationNumber(entity.getRegistrationNumber());
    }
}
