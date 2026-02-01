package com.app.restapi.initializer;

import com.app.restapi.jpa.entity.SchoolClass;
import com.app.restapi.jpa.repo.SchoolClassRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(5)
@Component
public class SchoolClassInitializer implements CommandLineRunner {


    private final SchoolClassRepository schoolClassRepository;

    public SchoolClassInitializer(
            SchoolClassRepository schoolClassRepository) {

        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<SchoolClass> all = schoolClassRepository.findAll();

        for (int i = 0; i <= 10; i++) {
            all.add(new SchoolClass().setName("Class " + (i + 1)).setAcademicYear("2026-2027"));
        }

        schoolClassRepository.saveAll(all);

    }
}
