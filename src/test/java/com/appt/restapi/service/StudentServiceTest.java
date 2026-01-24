package com.appt.restapi.service;

import com.app.restapi.converter.StudentConverter;
import com.app.restapi.dto.PaginationDto;
import com.app.restapi.dto.StudentDto;
import com.app.restapi.jpa.entity.Student;
import com.app.restapi.jpa.repo.StudentRepository;
import com.app.restapi.model.SortDirection;
import com.app.restapi.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentConverter studentConverter;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldReturnPageOfStudents() {
        // 1. Arrange
        PaginationDto dto = new PaginationDto();
        dto.setPage(0);
        dto.setSize(10);
        dto.setSortField("firstName");
        dto.setSortDirection(SortDirection.ASC);
        dto.setSearchTerm("Jane");

        // 2. Create a dummy page so findAll doesn't return null
        Page<Student> emptyPage = new PageImpl<>(Collections.emptyList());

        when(studentRepository.findAll(ArgumentMatchers.<Specification<Student>>any(), any(Pageable.class))).thenReturn(emptyPage);
        lenient().when(studentConverter.toDto(any())).thenReturn(new StudentDto());

        // 3. Act
        Page<StudentDto> result = studentService.getStudents(dto);

        // 4. Assert
        assertThat(result).isNotNull();
        verify(studentRepository).findAll(ArgumentMatchers.<Specification<Student>>any(), any(Pageable.class));
    }

    @AfterEach
    public void tearDown() {
        studentRepository.deleteAll();
    }
}
