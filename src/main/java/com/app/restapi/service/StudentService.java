package com.app.restapi.service;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.restapi.exceptions.StudentNotFoundException;
import com.app.restapi.jpa.dao.StudentRepository;
import com.app.restapi.jpa.entity.Guardian;
import com.app.restapi.jpa.entity.Student;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student saveStudent(Student student) {
		student.setRegistrationNumber(getRegistrationNumber());

		// Set the bidirectional relationship for guardians
		for (Guardian guardian : student.getGuardians()) {
			guardian.setStudent(student);
		}
		
		// Ensure the permanent/residential address is properly linked
	    if (student.getPermanentAddress() != null) {
	        student.getPermanentAddress().setStudentPermanent(student);
	    }
	    if (student.getResidentialAddress() != null) {
	    	student.getResidentialAddress().setStudentResidential(student);
	    }

		return studentRepository.save(student);
	}

	public Student getStudentByRegistrationNumber(String registrationNumber) {
		return studentRepository.findByRegistrationNumber(registrationNumber)
				.orElseThrow(() -> new StudentNotFoundException(
						"Student with registration number " + registrationNumber + " not found."));
	}

	private String getRegistrationNumber() {
		// Generate a unique registration number
		String year = String.valueOf(Year.now().getValue());
		long count = studentRepository.count() + 1;
		return "STU" + year + String.format("%03d", count);
	}
}
