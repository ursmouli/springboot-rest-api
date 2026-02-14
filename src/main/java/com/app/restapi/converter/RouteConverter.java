package com.app.restapi.converter;

import com.app.restapi.dto.RouteDto;
import com.app.restapi.jpa.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteConverter implements GenericConverter<Route, RouteDto> {

    @Autowired
    private VehicleConverter vehicleConverter;

    @Override
    public Route toEntity(RouteDto dto) {
        Route route = new Route();
        route.setId(dto.getId());
        route.setName(dto.getName());
        route.setDescription(dto.getDescription());

        if (dto.getVehicle() != null) {
            route.setVehicle(vehicleConverter.toEntity(dto.getVehicle()));
        }

        route.setPrice(dto.getPrice());
        route.setDistance(dto.getDistance());

        return route;
    }

    @Override
    public RouteDto toDto(Route entity) {
        RouteDto route = new RouteDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription());

        if (entity.getVehicle() != null) {
            route.setVehicle(vehicleConverter.toDto(entity.getVehicle()));
        }

        return route;
    }
}
