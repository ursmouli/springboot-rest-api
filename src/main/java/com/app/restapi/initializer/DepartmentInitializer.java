package com.app.restapi.initializer;

import com.app.restapi.dto.DepartmentDto;
import com.app.restapi.service.DepartmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(7)
public class DepartmentInitializer implements CommandLineRunner {

    private final DepartmentService departmentService;

    public DepartmentInitializer(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void run(String... args) throws Exception {

        DepartmentDto english = new DepartmentDto().setName("English").setCode("ENG").setDescription("English");
        DepartmentDto science = new DepartmentDto().setName("Science").setCode("SC").setDescription("Science");
        DepartmentDto computers = new DepartmentDto().setName("Computers").setCode("COMP").setDescription("Computers");
        DepartmentDto mathematics = new DepartmentDto().setName("Mathematics").setCode("MATH").setDescription("Mathematics");
        DepartmentDto social = new DepartmentDto().setName("Social").setCode("SO").setDescription("Social");

        departmentService.saveDepartment(english);
        departmentService.saveDepartment(science);
        departmentService.saveDepartment(computers);
        departmentService.saveDepartment(mathematics);
        departmentService.saveDepartment(social);
    }
}
