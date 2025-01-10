package com.app.restapi.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.app.restapi.converter.AddressConverter;
import com.app.restapi.converter.GuardianConverter;
import com.app.restapi.converter.StudentConverter;
import com.app.restapi.dto.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.restapi.exceptions.StudentNotFoundException;
import com.app.restapi.jpa.repo.StudentRepository;
import com.app.restapi.jpa.entity.Guardian;
import com.app.restapi.jpa.entity.Student;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
	private static final Logger log = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private AddressConverter addressConverter;

	@Autowired
	private StudentConverter studentConverter;

	@Autowired
	private GuardianConverter guardianConverter;

	@Transactional
	public StudentDto saveStudent(StudentDto studentDto) {
		studentDto.setRegistrationNumber(getRegistrationNumber());

		final Student student = studentConverter.toEntity(studentDto);

		// Ensure the permanent/residential address is properly linked
		if (student.getPermanentAddress() != null) {
			student.getPermanentAddress().setStudentPermanent(student);
		}
		if (student.getResidentialAddress() != null) {
			student.getResidentialAddress().setStudentResidential(student);
		}

		// Set the bidirectional relationship for guardians
		if (!student.getGuardians().isEmpty()) {
			student.getGuardians().forEach(guardian -> guardian.setStudent(student));
		}

		Student dbStudent = studentRepository.save(student);

		log.info("Student save with registrationNumber {}", dbStudent.getRegistrationNumber());

		return studentConverter.toDto(dbStudent);
	}

	@Transactional
	public StudentDto getStudentByRegistrationNumber(String registrationNumber) {
		Student student = studentRepository.findByRegistrationNumber(registrationNumber)
				.orElseThrow(() -> new StudentNotFoundException(
						"Student with registration number " + registrationNumber + " not found."));

		return studentConverter.toDto(student);
	}

	private String getRegistrationNumber() {
		// Generate a unique registration number
		String year = String.valueOf(Year.now().getValue());
		long count = studentRepository.count() + 1;
		return "STU" + year + String.format("%03d", count);
	}
}
