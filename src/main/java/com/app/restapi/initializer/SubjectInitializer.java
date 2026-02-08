package com.app.restapi.initializer;

import com.app.restapi.dto.DepartmentDto;
import com.app.restapi.dto.SubjectDto;
import com.app.restapi.service.DepartmentService;
import com.app.restapi.service.SubjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(8)
public class SubjectInitializer implements CommandLineRunner {

    private final SubjectService subjectService;
    private final DepartmentService departmentService;
    public SubjectInitializer(SubjectService subjectService,
                              DepartmentService departmentService) {
        this.subjectService = subjectService;
        this.departmentService = departmentService;
    }

    @Override
    public void run(String... args) throws Exception {

        DepartmentDto cD = departmentService.getDepartment("Computers");
        DepartmentDto eD = departmentService.getDepartment("English");
        DepartmentDto mD = departmentService.getDepartment("Mathematics");

        SubjectDto english = new SubjectDto()
                .setCode("ENG")
                .setName("English")
                .setCredits(100)
                .setDescription("English")
                .setDepartment(eD);
        SubjectDto computer = new SubjectDto()
                .setCode("COMP")
                .setName("Computers")
                .setCredits(100)
                .setDescription("Computers")
                .setDepartment(cD);
        SubjectDto maths = new SubjectDto()
                .setCode("MAT")
                .setName("Mathematics")
                .setCredits(100)
                .setDescription("Mathematics")
                .setDepartment(mD);

        subjectService.addSubject(english);
        subjectService.addSubject(computer);
        subjectService.addSubject(maths);
    }
}
