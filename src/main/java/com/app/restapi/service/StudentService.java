package com.app.restapi.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.app.restapi.converter.AddressConverter;
import com.app.restapi.converter.StudentConverter;
import com.app.restapi.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.restapi.exceptions.StudentNotFoundException;
import com.app.restapi.jpa.repo.StudentRepository;
import com.app.restapi.jpa.entity.Guardian;
import com.app.restapi.jpa.entity.Student;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private AddressConverter addressConverter;

	@Autowired
	private StudentConverter studentConverter;

	@Transactional
	public StudentDto saveStudent(StudentDto studentDto) {
		studentDto.setRegistrationNumber(getRegistrationNumber());

		final Student student = new Student()
				.setFirstName(studentDto.getFirstName())
				.setMiddleName(studentDto.getMiddleName())
				.setLastName(studentDto.getLastName())
				.setRegistrationNumber(studentDto.getRegistrationNumber())
				.setDob(studentDto.getDob());

		// Ensure the permanent/residential address is properly linked
		if (studentDto.getPermanentAddress() != null) {
			student.setPermanentAddress(addressConverter.toEntity(studentDto.getPermanentAddress()).setStudentPermanent(student));
		}
		if (studentDto.getResidentialAddress() != null) {
			student.setResidentialAddress(addressConverter.toEntity(studentDto.getResidentialAddress()).setStudentResidential(student));
		}

		// Set the bidirectional relationship for guardians
		if (!studentDto.getGuardians().isEmpty()) {
			List<Guardian> guardiansList = new ArrayList<>();
			studentDto.getGuardians().forEach(guardianDto -> {
				guardiansList.add(
						new Guardian(
								guardianDto.getName(),
								guardianDto.getEmail(),
								guardianDto.getPhone(),
								guardianDto.getRelation()).setStudent(student));
			});
			student.setGuardians(guardiansList);
		}
		Student dbStudent = studentRepository.save(student);

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
