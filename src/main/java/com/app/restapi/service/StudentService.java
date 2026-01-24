package com.app.restapi.service;

import java.time.Year;
import java.util.Set;

import com.app.restapi.converter.StudentConverter;
import com.app.restapi.dto.PaginationDto;
import com.app.restapi.dto.StudentDto;
import com.app.restapi.jpa.specifications.StudentSpecification;
import com.app.restapi.model.SortDirection;
import com.app.restapi.util.EntityUtil;
//import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.app.restapi.exceptions.StudentNotFoundException;
import com.app.restapi.jpa.repo.StudentRepository;
import com.app.restapi.jpa.entity.Student;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class StudentService {
	private static final Logger log = LoggerFactory.getLogger(StudentService.class);

	private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "firstName", "lastName", "createdDate");

	private final StudentRepository studentRepository;
	private final StudentConverter studentConverter;

	public StudentService(StudentRepository studentRepository, StudentConverter studentConverter) {
		this.studentRepository = studentRepository;
		this.studentConverter = studentConverter;
	}

	@Transactional
	public StudentDto saveStudent(StudentDto studentDto) {
//		studentDto.setRegistrationNumber(getRegistrationNumber());

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

		if (!student.getSiblings().isEmpty()) {
			student.getSiblings().forEach(sibling -> sibling.setStudent(student));
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

	@Transactional
	public Page<StudentDto> getAllStudent(PaginationDto pagination) {
		log.debug("pagination: {}", pagination);
		if (!ALLOWED_SORT_FIELDS.contains(pagination.getSortField())) {
			throw new IllegalArgumentException("Invalid sort field: " + pagination.getSortField());
		}

		EntityUtil.isFieldExist(Student.class, pagination.getSortField());

		Sort sort = Sort.by(
				SortDirection.toSpringSortDirection(pagination.getSortDirection()),
				pagination.getSortField()
		);

		Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize(), sort);

		// TODO verify if below can be used if lazy initialization doesn't work
		// all.getContent().forEach(student -> Hibernate.initialize(student.getGuardians()));

		Page<StudentDto> students;
		if (pagination.getSearchText() != null && !pagination.getSearchText().trim().isEmpty()) {
			Specification<Student> spec = StudentSpecification.hasSearchText(pagination.getSearchText());

			students = studentRepository.findAll(spec, pageable).map(studentConverter::toDto);
		} else {
			students = studentRepository.findAll(pageable).map(studentConverter::toDto);
		}

		return students;
	}

//	private String getRegistrationNumber() {
//		// Generate a unique registration number
//		String year = String.valueOf(Year.now().getValue());
//		long count = studentRepository.count() + 1;
//		return "STU" + year + String.format("%03d", count);
//	}
}
