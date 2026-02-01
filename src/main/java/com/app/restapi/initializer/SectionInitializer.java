package com.app.restapi.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(6)
@Component
public class SectionInitializer implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }
}
