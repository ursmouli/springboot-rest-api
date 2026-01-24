package com.app.restapi.initializer;

import com.app.restapi.jpa.entity.*;
import com.app.restapi.jpa.repo.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@Order(3)
//@DependsOn("addressInitializer")
public class StudentInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final DistrictRepository districtRepository;
    private final TalukRepository talukRepository;

    public StudentInitializer(StudentRepository studentRepository,
                              CountryRepository countryRepository,
                              StateRepository stateRepository,
                              DistrictRepository districtRepository,
                              TalukRepository talukRepository) {
        this.studentRepository = studentRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.districtRepository = districtRepository;
        this.talukRepository = talukRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Country country = countryRepository.findByName("India")
                .orElseThrow(() -> new EntityNotFoundException("Country India not found"));

        State state = stateRepository.findByCountryId(country.getId())
                .stream()
                .filter(s -> s.getCode().equals("IN-KA"))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("State Karnataka not found"));

        District district = districtRepository.findByStateId(state.getId())
                .stream()
                .filter(d -> d.getCode().equals(state.getCode() + "-RAI"))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("District Raichur not found"));

        Taluk manvi = talukRepository.findByDistrictId(district.getId())
                .stream()
                .filter(t -> t.getName().equalsIgnoreCase("Manvi"))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Taluk Manvi not found"));

        Taluk sindhanur = talukRepository.findByDistrictId(district.getId())
                .stream()
                .filter(t -> t.getName().equalsIgnoreCase("Sindhanur"))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Taluk Manvi not found"));

        Student john = new Student()
                .setFirstName("John")
                .setLastName("Doe")
                .setDob(LocalDate.now())
                .setGender("M")
                .setSameAsPermanentAddress(true)
                .setPermanentAddress(new Address()
                        .setHouseNumber("01")
                        .setLandMark("Near post office")
                        .setAddressLine1("X Street")
                        .setPostalCode("584128")
                        .setCountry(country)
                        .setState(state)
                        .setDistrict(district)
                        .setTaluk(sindhanur));

        Student jane = new Student()
                .setFirstName("Jane")
                .setLastName("Doe")
                .setDob(LocalDate.now())
                .setGender("F")
                .setSameAsPermanentAddress(true)
                .setPermanentAddress(new Address()
                        .setHouseNumber("02")
                        .setLandMark("Near taluk office")
                        .setAddressLine1("Y Street")
                        .setPostalCode("584126")
                        .setCountry(country)
                        .setState(state)
                        .setDistrict(district)
                        .setTaluk(manvi));

        studentRepository.saveAll(List.of(john, jane));

        List<String> firstNames = List.of("Jane", "John", "Alice", "Bob", "Charlie", "Diana", "Edward", "Fiona");
        List<String> lastNames = List.of("Doe", "Smith", "Johnson", "Brown", "Taylor", "Miller", "Wilson");
        Random random = new Random();

        List<Student> students = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            String fName = firstNames.get(random.nextInt(firstNames.size()));
            String lName = lastNames.get(random.nextInt(lastNames.size()));

            Student student = new Student()
                    .setFirstName(fName)
                    .setLastName(lName)
                    .setDob(LocalDate.now().minusYears(15 + random.nextInt(10))) // Random age 15-25
                    .setGender(random.nextBoolean() ? "M" : "F")
                    .setSameAsPermanentAddress(true)
                    .setPermanentAddress(new Address()
                            .setHouseNumber(String.valueOf(i))
                            .setLandMark("Landmark " + i)
                            .setAddressLine1(i + " Main St")
                            .setPostalCode("56000" + i)
                            .setCountry(country)
                            .setState(state)
                            .setDistrict(district)
                            .setTaluk(sindhanur)
                    );

            students.add(student);
        }

        studentRepository.saveAll(students);
    }
}
