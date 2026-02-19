package com.app.restapi.resource;

import com.app.restapi.dto.RouteDto;
import com.app.restapi.dto.StudentDto;
import com.app.restapi.service.RouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/route")
public class RouteResource {

    private final RouteService routeService;

    public RouteResource(
            RouteService routeService
    ) {
        this.routeService = routeService;
    }

    @PutMapping("/assign-student-route/{studentId}/{stopId}")
    public ResponseEntity<StudentDto> assignStudentToRoute(
            @PathVariable Long studentId,
            @PathVariable Long stopId
    ) {
        return ResponseEntity.ok(routeService.assignStudentToRoute(studentId, stopId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RouteDto>> getAllRoutes() {
        return ResponseEntity.ok(routeService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getRouteById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<RouteDto> add(@RequestBody RouteDto route) {
        return new ResponseEntity<>(routeService.add(route), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RouteDto> update(@PathVariable Long id, @RequestBody RouteDto routeDetails) {
        return ResponseEntity.ok(routeService.update(id, routeDetails));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        routeService.deleteRouteById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Route deleted successfully");

        return ResponseEntity.ok(response);
    }
}
