package com.app.restapi.service;

import java.time.Year;
import com.app.restapi.converter.StudentConverter;
import com.app.restapi.dto.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.restapi.exceptions.StudentNotFoundException;
import com.app.restapi.jpa.repo.StudentRepository;
import com.app.restapi.jpa.entity.Student;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
	private static final Logger log = LoggerFactory.getLogger(StudentService.class);

	private final StudentRepository studentRepository;
	private final StudentConverter studentConverter;

	public StudentService(StudentRepository studentRepository, StudentConverter studentConverter) {
		this.studentRepository = studentRepository;
		this.studentConverter = studentConverter;
	}

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
