package com.app.restapi.converter;

import com.app.restapi.dto.PickupPointDto;
import com.app.restapi.jpa.entity.PickupPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class PickupPointConverter implements GenericConverter<PickupPoint, PickupPointDto> {

    @Autowired
    private StudentConverter studentConverter;
    @Autowired
    private RouteConverter routeConverter;


    @Override
    public PickupPoint toEntity(PickupPointDto dto) {
        PickupPoint pickupPoint = new PickupPoint()
                .setId(dto.getId())
                .setSequenceOrder(dto.getSequenceOrder())
                .setStopName(dto.getStopName())
                .setExpectedArrivalTime(dto.getExpectedArrivalTime());

        if (dto.getRoute() != null) {
            pickupPoint.setRoute(routeConverter.toEntity(dto.getRoute()));
        }

        if (!CollectionUtils.isEmpty(dto.getStudents())) {
            pickupPoint.setStudents(studentConverter.toEntityList(dto.getStudents()));
        }

        return pickupPoint;
    }

    @Override
    public PickupPointDto toDto(PickupPoint entity) {
        PickupPointDto dto = new PickupPointDto()
                .setId(entity.getId())
                .setStopName(entity.getStopName())
                .setExpectedArrivalTime(entity.getExpectedArrivalTime())
                .setSequenceOrder(entity.getSequenceOrder());

        if (entity.getRoute() != null) {
            dto.setRoute(routeConverter.toDto(entity.getRoute()));
        }

        if (!CollectionUtils.isEmpty(entity.getStudents())) {
            dto.setStudents(studentConverter.toDtoList(entity.getStudents()));
        }

        return dto;
    }
}
