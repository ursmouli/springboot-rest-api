package com.app.restapi.service;

import com.app.restapi.converter.PickupPointConverter;
import com.app.restapi.converter.StudentConverter;
import com.app.restapi.dto.PickupPointDto;
import com.app.restapi.dto.StudentDto;
import com.app.restapi.jpa.entity.PickupPoint;
import com.app.restapi.jpa.entity.Route;
import com.app.restapi.jpa.entity.Student;
import com.app.restapi.jpa.repo.PickupPointRepository;
import com.app.restapi.jpa.repo.RouteRepository;
import com.app.restapi.jpa.repo.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PickupPointService {

    @Autowired
    private PickupPointRepository pickupPointRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private PickupPointConverter pickupPointConverter;

    public PickupPointDto add(PickupPointDto dto) {

        Route route = routeRepository.findById(dto.getRoute().getId()).orElseThrow(EntityNotFoundException::new);

        List<Student> students = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dto.getStudents())) {
            students.add(studentRepository.findById(dto.getStudents().get(0).getId()).orElseThrow(EntityNotFoundException::new));
        }

        PickupPoint pickupPoint = pickupPointConverter.toEntity(dto);
        pickupPoint.setRoute(route);
        pickupPoint.setStudents(students);

        return pickupPointConverter.toDto(pickupPointRepository.save(pickupPoint));
    }

    public PickupPointDto update(Long id, PickupPointDto dto) {
        PickupPoint existingStop = getById(id);

        existingStop.setStopName(dto.getStopName());
        existingStop.setExpectedArrivalTime(dto.getExpectedArrivalTime());
        existingStop.setSequenceOrder(dto.getSequenceOrder());

        if (dto.getRoute() != null) {
            Route route = routeRepository.findById(dto.getRoute().getId()).orElseThrow(EntityNotFoundException::new);
            existingStop.setRoute(route);
        }

        List<Student> students = new ArrayList<>();
        if (CollectionUtils.isEmpty(dto.getStudents())) {
            dto.getStudents().forEach(student -> {
                students.add(studentRepository.findById(student.getId()).orElseThrow(EntityNotFoundException::new));
            });
        }

        if (!CollectionUtils.isEmpty(students)) {
            existingStop.setStudents(students);
        }

        return pickupPointConverter.toDto(pickupPointRepository.save(existingStop));
    }

    public List<PickupPointDto> getPickupPoints() {
        return pickupPointConverter.toDtoList(pickupPointRepository.findAll());
    }

    public PickupPointDto getPickupPointById(Long id) {
        return pickupPointConverter.toDto(getById(id));
    }

    public void deletePickupPoint(Long id) {
        PickupPoint pickupPoint = getById(id);

        if (!pickupPoint.getStudents().isEmpty()) {
            throw new IllegalStateException("Cannot delete pickup point with name: " + pickupPoint.getStopName() + ". Stop has students.");
        }

        pickupPointRepository.deleteById(id);
    }

    public List<PickupPointDto> fetchRoutePickupPoints(Long routeId) {

        List<PickupPoint> byRouteId = pickupPointRepository.findByRouteIdOrderBySequenceOrderAsc(routeId);

        if (!CollectionUtils.isEmpty(byRouteId)) {
            return pickupPointConverter.toDtoList(byRouteId);
        }

        return List.of();
    }

    public void deleteStudentFromPickupPoint(Long studentId, Long stopId) {
        PickupPoint pickupPoint = getById(stopId);

        Student student = pickupPoint
                .getStudents()
                .stream()
                .filter(s -> s.getId().equals(studentId)).findFirst().orElseThrow(EntityNotFoundException::new);

        pickupPoint.removeStudent(student);
    }

    private PickupPoint getById(Long id) {
        return pickupPointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pickup Point with id: " + id + " not found!"));
    }
}
