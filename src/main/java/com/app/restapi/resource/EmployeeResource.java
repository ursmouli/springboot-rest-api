package com.app.restapi.resource;

import com.app.restapi.dto.EmployeeDto;
import com.app.restapi.dto.PaginationDto;
import com.app.restapi.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeResource {

    private static final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/all")
    public ResponseEntity<Page<EmployeeDto>> getAll(@RequestBody @Validated PaginationDto paginationDto) {
        log.debug("pagination: {}", paginationDto);
        return ResponseEntity.ok(employeeService.getEmployees(paginationDto));
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addStudent(@RequestBody EmployeeDto employee) {
        EmployeeDto savedStudent = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/find/{employeeNumber}")
    public ResponseEntity<EmployeeDto> getStudentByRollNumber(@PathVariable String employeeNumber) {
        EmployeeDto employee = employeeService.getEmployeeByNumber(employeeNumber);
        return ResponseEntity.ok(employee);
    }
}
