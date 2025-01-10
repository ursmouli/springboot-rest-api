package com.app.restapi.resource;

import com.app.restapi.dto.StudentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.restapi.service.StudentService;

@RequestMapping("/api/student")
@RestController
public class StudentResource {
	
    private final StudentService studentService;

	public StudentResource(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/add")
	public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto student) {
		StudentDto savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
	}
	
	@GetMapping("/{registrationNumber}")
    public ResponseEntity<StudentDto> getStudentByRegistrationNumber(@PathVariable String registrationNumber) {
		StudentDto student = studentService.getStudentByRegistrationNumber(registrationNumber);
        return ResponseEntity.ok(student);
    }
}
