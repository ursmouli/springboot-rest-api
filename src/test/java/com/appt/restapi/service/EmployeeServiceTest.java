package com.appt.restapi.service;

import com.app.restapi.converter.EmployeeConverter;
import com.app.restapi.jpa.repo.DepartmentRepository;
import com.app.restapi.jpa.repo.EmployeeRepository;
import com.app.restapi.service.EmployeeService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private EmployeeConverter employeeConverter;

    @InjectMocks
    private EmployeeService employeeService;
}
