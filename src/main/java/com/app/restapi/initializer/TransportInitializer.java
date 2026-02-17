package com.app.restapi.initializer;

import com.app.restapi.dto.VehicleDto;
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

    @Override
    public void run(String... args) throws Exception {
        // add vehicles
        VehicleDto vehicleDto = new VehicleDto()
                .setRegistrationNumber("VEH-REG-1")
                .setCapacity(30)
                .setDriverName("Driver 1");

        vehicleService.add(vehicleDto);

        // add route

        // assign route
    }
}
