package com.app.restapi.service;

import com.app.restapi.converter.RouteConverter;
import com.app.restapi.converter.StudentConverter;
import com.app.restapi.converter.VehicleConverter;
import com.app.restapi.dto.PickupPointDto;
import com.app.restapi.dto.RouteDto;
import com.app.restapi.dto.StudentDto;
import com.app.restapi.jpa.entity.PickupPoint;
import com.app.restapi.jpa.entity.Route;
import com.app.restapi.jpa.entity.Student;
import com.app.restapi.jpa.entity.Vehicle;
import com.app.restapi.jpa.repo.PickupPointRepository;
import com.app.restapi.jpa.repo.RouteRepository;
import com.app.restapi.jpa.repo.StudentRepository;
import com.app.restapi.jpa.repo.VehicleRepository;
import com.app.restapi.jpa.specifications.RouteSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);

    private final StudentRepository studentRepository;
    private final RouteRepository routeRepository;
    private final PickupPointRepository pickupPointRepository;
    private final VehicleRepository vehicleRepository;

    private final RouteConverter routeConverter;
    private final VehicleConverter vehicleConverter;
    private final StudentConverter studentConverter;

    public RouteService(
            StudentRepository studentRepository,
            RouteRepository routeRepository,
            PickupPointRepository pickupPointRepository,
            RouteConverter routeConverter,
            VehicleRepository vehicleRepository,
            VehicleConverter vehicleConverter,
            StudentConverter studentConverter
    ) {
        this.studentRepository = studentRepository;
        this.routeRepository = routeRepository;
        this.pickupPointRepository = pickupPointRepository;
        this.routeConverter = routeConverter;
        this.vehicleRepository = vehicleRepository;
        this.vehicleConverter = vehicleConverter;
        this.studentConverter = studentConverter;
    }

    public RouteDto getRouteById(Long id) {
        return routeConverter.toDto(getById(id));
    }

    public RouteDto fetchPickupPointsStudentsByRouteId(Long id) {
        Route route = getById(id);

        List<PickupPointDto> pickupPoints = new ArrayList<>();
        for (PickupPoint pickupPoint : route.getPickupPoints()) {
            List<StudentDto> students = studentConverter.toDtoList(pickupPoint.getStudents());
            pickupPoints.add(new PickupPointDto()
                    .setId(pickupPoint.getId())
                    .setSequenceOrder(pickupPoint.getSequenceOrder())
                    .setStopName(pickupPoint.getStopName())
                    .setExpectedArrivalTime(pickupPoint.getExpectedArrivalTime())
                    .setStudents(students));
        }

        RouteDto routeDto = routeConverter.toDto(route);
        routeDto.setPickupPoints(pickupPoints);

        return routeDto;
    }

    public RouteDto add(RouteDto routeDto) {
        Route route = routeConverter.toEntity(routeDto);

        if (route.getVehicle() != null) {
            Vehicle vehicle = vehicleRepository.findById(route.getVehicle().getId()).orElseThrow(EntityNotFoundException::new);
            route.setVehicle(vehicle);
        }

        return routeConverter.toDto(routeRepository.save(route));
    }

    public RouteDto update(Long id, RouteDto routeDto) {
        Route existingRoute = getById(id);

        existingRoute.setName(routeDto.getName());
        existingRoute.setDescription(routeDto.getDescription());
        existingRoute.setDistance(routeDto.getDistance());

        // If updating the vehicle assigned to this route
        if (routeDto.getVehicle() != null) {
            Vehicle vehicle = vehicleRepository.findByRegistrationNumberEqualsIgnoreCase(
                    routeDto.getVehicle().getRegistrationNumber());

            if (vehicle != null) {
                logger.info("Vehicle already exists");
                existingRoute.setVehicle(vehicle);
            } else {
                logger.info("Vehicle does not exist. Creating a new vehicle");
                vehicle = vehicleConverter.toEntity(routeDto.getVehicle());
                existingRoute.setVehicle(vehicle);
            }
        }

        return routeConverter.toDto(routeRepository.save(existingRoute));
    }

    public List<RouteDto> findAll() {
        return routeConverter.toDtoList(routeRepository.findAll());
    }

    public void deleteRouteById(Long id) {
        Route route = getById(id);

        boolean hasStudents = route.getPickupPoints().stream()
                .anyMatch(stop -> !stop.getStudents().isEmpty());

        if (hasStudents) {
            throw new IllegalStateException("Cannot delete route: Students are currently assigned to stops on this route.");
        }

        routeRepository.delete(route);
    }

    public StudentDto assignStudentToRoute(Long studentId, Long stopId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with id: " + studentId + " not found"));


        PickupPoint pickupPoint = pickupPointRepository.findById(stopId)
                .orElseThrow(() -> new EntityNotFoundException("Pickup point with id: " + stopId + " not found"));

        Route route = pickupPoint.getRoute();

        if (pickupPoint.getStudents().size() >= route.getVehicle().getCapacity()) {
            throw new RuntimeException("Capacity exceeded");
        }

        student.setPickupPoint(pickupPoint);

        return studentConverter.toDto(studentRepository.save(student));
    }

    public List<Route> searchRoutes(String name, Integer minCapacity, String driver) {
        // Combine all criteria using "where" and "and"
        Specification<Route> spec = Specification
                .where(RouteSpecification.hasRouteName(name))
                .and(RouteSpecification.hasMinCapacity(minCapacity))
                .and(RouteSpecification.hasDriver(driver));

        return routeRepository.findAll(spec);
    }

    private Route getById(Long id) {
        return routeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
