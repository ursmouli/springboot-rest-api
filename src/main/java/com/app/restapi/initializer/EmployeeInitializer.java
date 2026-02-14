package com.app.restapi.initializer;

import com.app.restapi.jpa.entity.*;
import com.app.restapi.jpa.repo.*;
import com.app.restapi.model.AppRole;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Order(4)
public class EmployeeInitializer implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final DistrictRepository districtRepository;
    private final TalukRepository talukRepository;

    private final DepartmentRepository departmentRepository;

    public EmployeeInitializer(EmployeeRepository employeeRepository,
                              CountryRepository countryRepository,
                              StateRepository stateRepository,
                              DistrictRepository districtRepository,
                              TalukRepository talukRepository,
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.districtRepository = districtRepository;
        this.talukRepository = talukRepository;
        this.departmentRepository = departmentRepository;
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

        List<String> firstNames = List.of("Jane", "John", "Alice", "Bob", "Charlie", "Diana", "Edward", "Fiona");
        List<String> lastNames = List.of("Doe", "Smith", "Johnson", "Brown", "Taylor", "Miller", "Wilson");
        List<Taluk> talukList = List.of(manvi, sindhanur);

        //List<String> roles = List.of("Teacher", "Administrator", "Developer",  "Operations", "Finance", "Manager");

        List<AppRole> roles = Arrays.stream(AppRole.values()).toList();

        Department engineering = new Department().setName("Engineering").setCode("ENGINEERING").setDescription("Engineering Department");
        Department hr = new Department().setName("HR").setCode("HR").setDescription("HR Department");
        Department finance = new Department().setName("Finance").setCode("FINANCE").setDescription("Finance Department");
        Department marketing = new Department().setName("Marketing").setCode("MARKETING").setDescription("Marketing Department");
        Department operations = new Department().setName("Operations").setCode("OPERATIONS").setDescription("Operations Department");

        List<Department> departments = departmentRepository.saveAll(List.of(
                engineering, hr, finance, marketing, operations));

        Random random = new Random();

        List<Employee> employees = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            String fName = firstNames.get(random.nextInt(firstNames.size()));
            String lName = lastNames.get(random.nextInt(lastNames.size()));

            Taluk taluk = talukList.get(random.nextInt(talukList.size()));

            AppRole role = roles.get(random.nextInt(roles.size()));

            Department department = departments.get(random.nextInt(departments.size()));

            Employee employee = new Employee()
                    .setFirstName(fName)
                    .setLastName(lName)
                    .setEmail(firstNames + "" + random.nextInt(3) + "@mail.com")
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
                            .setTaluk(taluk)
                    )
                    .setDepartment(department)
                    .setRole(role);

            employees.add(employee);
        }

        employeeRepository.saveAll(employees);
    }
}
