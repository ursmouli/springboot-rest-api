package com.app.restapi.initializer;

import com.app.restapi.dto.PickupPointDto;
import com.app.restapi.dto.RouteDto;
import com.app.restapi.dto.VehicleDto;
import com.app.restapi.service.PickupPointService;
import com.app.restapi.service.RouteService;
import com.app.restapi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 9)
public class TransportInitializer implements CommandLineRunner {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private PickupPointService pickupPointService;

    @Override
    public void run(String... args) throws Exception {
        // add vehicles
        VehicleDto vehicleDto1 = new VehicleDto()
                .setRegistrationNumber("VEH-REG-1")
                .setCapacity(30)
                .setDriverName("Driver 1");

        vehicleDto1 = vehicleService.add(vehicleDto1);

        VehicleDto vehicleDto2 = new VehicleDto()
                .setRegistrationNumber("VEH-REG-1")
                .setCapacity(30)
                .setDriverName("Driver 1");

        vehicleDto2 = vehicleService.add(vehicleDto2);

        // add route
        RouteDto routeDto1 = new RouteDto()
                .setName("Route 1")
                .setDescription("Route 1")
                .setDistance(30d)
                .setPrice(100d)
                .setVehicle(vehicleDto1);

        routeDto1 = routeService.add(routeDto1);

        RouteDto routeDto2 = new RouteDto()
                .setName("Route 2")
                .setDescription("Route 2")
                .setDistance(30d)
                .setPrice(100d)
                .setVehicle(vehicleDto2);

        routeDto2 = routeService.add(routeDto2);

        // add pickup point for route 1
        PickupPointDto pickupPoint1Route1 = new PickupPointDto()
                .setRoute(routeDto1)
                .setStopName("R1 Stop 1")
                .setSequenceOrder(1);

        pickupPoint1Route1 = pickupPointService.add(pickupPoint1Route1);

        PickupPointDto pickupPoint2Route1 = new PickupPointDto()
                .setRoute(routeDto1)
                .setStopName("R1 Stop 2")
                .setSequenceOrder(2);

        pickupPoint2Route1 = pickupPointService.add(pickupPoint2Route1);

        // add pickup point for route 1
        PickupPointDto pickupPoint1Route2 = new PickupPointDto()
                .setRoute(routeDto2)
                .setStopName("R2 Stop 1")
                .setSequenceOrder(1);

        pickupPoint1Route2 = pickupPointService.add(pickupPoint1Route2);

        PickupPointDto pickupPoint2Route2 = new PickupPointDto()
                .setRoute(routeDto2)
                .setStopName("R2 Stop 2")
                .setSequenceOrder(2);

        pickupPoint2Route2 = pickupPointService.add(pickupPoint2Route2);

        // assign route to student
        routeService.assignStudentToRoute(1L, pickupPoint1Route1.getId());
        routeService.assignStudentToRoute(2L, pickupPoint2Route1.getId());
        routeService.assignStudentToRoute(3L, pickupPoint1Route2.getId());
        routeService.assignStudentToRoute(4L, pickupPoint2Route2.getId());
    }
}
