package com.app.restapi.service;

import java.time.Year;
import java.util.List;
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
@Transactional
public class StudentService {
	private static final Logger log = LoggerFactory.getLogger(StudentService.class);

	private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "firstName", "lastName", "createdDate");

	private final StudentRepository studentRepository;
	private final StudentConverter studentConverter;

	public StudentService(StudentRepository studentRepository, StudentConverter studentConverter) {
		this.studentRepository = studentRepository;
		this.studentConverter = studentConverter;
	}

	public StudentDto saveStudent(StudentDto studentDto) {
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

		log.info("Student save with Roll Number {}", dbStudent.getRollNumber());

		return studentConverter.toDto(dbStudent);
	}

	public StudentDto getStudentByRollNumber(String rollNumber) {
		Student student = studentRepository.findByRollNumber(rollNumber)
				.orElseThrow(() -> new StudentNotFoundException(
						"Student with roll number " + rollNumber + " not found."));

		return studentConverter.toDto(student);
	}

	public List<StudentDto> getAllStudents() {
		return studentConverter.toDtoList(studentRepository.findAll());
	}

	public Page<StudentDto> getStudents(PaginationDto pagination) {
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
		if (pagination.getSearchTerm() != null && !pagination.getSearchTerm().trim().isEmpty()) {
			Specification<Student> spec = StudentSpecification.hasSearchText(pagination.getSearchTerm());

			Page<Student> all = studentRepository.findAll(spec, pageable);
			if (all == null || all.getTotalElements() == 0) {
				students = Page.empty();
			} else {
				students = all.map(studentConverter::toDto);
			}
		} else {
			Page<Student> all = studentRepository.findAll(pageable);
			if (all.getTotalElements() == 0) {
				students = Page.empty();
			} else {
				students = all.map(studentConverter::toDto);
			}
		}

		return students;
	}
}
