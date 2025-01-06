package com.app.restapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.restapi.jpa.entity.Student;
import com.app.restapi.service.StudentService;

@RequestMapping("/api/student")
@RestController
public class StudentResource {
	
	@Autowired
    private StudentService studentService;

	@PostMapping("/register")
	public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
		Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
	}
	
	@GetMapping("/{registrationNumber}")
    public ResponseEntity<Student> getStudentByRegistrationNumber(@PathVariable String registrationNumber) {
		Student student = studentService.getStudentByRegistrationNumber(registrationNumber);
        return ResponseEntity.ok(student);
    }
}
