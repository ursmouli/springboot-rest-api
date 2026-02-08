package com.app.restapi.resource;

import com.app.restapi.dto.DepartmentDto;
import com.app.restapi.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentResource {

    private final DepartmentService departmentService;
    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody DepartmentDto departmentDto) {
        departmentService.saveDepartment(departmentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }
}
