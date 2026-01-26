package com.appt.restapi.jpa;

import com.app.restapi.RestApiApplication;
import com.app.restapi.jpa.entity.Department;
import com.app.restapi.jpa.entity.Employee;
import com.app.restapi.jpa.repo.DepartmentRepository;
import com.app.restapi.jpa.repo.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration(classes = RestApiApplication.class)
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void saveEmployeeToDepartment() {
        String departmentName = "Department";

        Employee employee = employeeRepository.save(new Employee()
                .setFirstName("John").setLastName("Doe")
                .setDob(LocalDate.now()).setGender("Male"));

        Department department = departmentRepository.save(new Department().setName(departmentName));

        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getDepartment().getName()).isEqualTo(departmentName);
    }
}
