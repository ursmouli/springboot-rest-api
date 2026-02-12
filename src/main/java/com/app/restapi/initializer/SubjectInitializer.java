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

        DepartmentDto coD = departmentService.getDepartment("Computers");
        DepartmentDto eD = departmentService.getDepartment("English");
        DepartmentDto mD = departmentService.getDepartment("Mathematics");
        DepartmentDto pD = departmentService.getDepartment("Physics");
        DepartmentDto chD = departmentService.getDepartment("Chemistry");
        DepartmentDto hD = departmentService.getDepartment("History");
        DepartmentDto gD = departmentService.getDepartment("Geography");

        SubjectDto english = new SubjectDto()
                .setCode("ENGL101")
                .setName("English Literature")
                .setCredits(100)
                .setDescription("Study of English literature")
                .setDepartment(eD);
        SubjectDto computer = new SubjectDto()
                .setCode("CS101")
                .setName("Computer Scienc")
                .setCredits(100)
                .setDescription("Introduction to programming")
                .setDepartment(coD);
        SubjectDto maths = new SubjectDto()
                .setCode("MATH101")
                .setName("Mathematics")
                .setCredits(100)
                .setDescription("Introduction to basic mathematical concepts")
                .setDepartment(mD);
        SubjectDto physics = new SubjectDto()
                .setCode("PHYS101")
                .setName("Physics")
                .setCredits(100)
                .setDescription("Fundamental principles of physics")
                .setDepartment(pD);
        SubjectDto chemistry = new SubjectDto()
                .setCode("PHYS101")
                .setName("Physics")
                .setCredits(100)
                .setDescription("Fundamental principles of physics")
                .setDepartment(chD);
        SubjectDto geography = new SubjectDto()
                .setCode("GEOG101")
                .setName("Geography")
                .setCredits(100)
                .setDescription("Physical and human geography")
                .setDepartment(gD);
        SubjectDto history = new SubjectDto()
                .setCode("HIST101")
                .setName("History")
                .setCredits(100)
                .setDescription("World history and civilizations")
                .setDepartment(hD);

        subjectService.addSubject(english);
        subjectService.addSubject(computer);
        subjectService.addSubject(maths);

        subjectService.addSubject(physics);
        subjectService.addSubject(chemistry);
        subjectService.addSubject(geography);
        subjectService.addSubject(history);
    }
}
