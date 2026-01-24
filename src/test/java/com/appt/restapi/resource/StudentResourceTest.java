package com.appt.restapi.resource;

import com.app.restapi.RestApiApplication;
import com.app.restapi.dto.PaginationDto;
import com.app.restapi.dto.StudentDto;
import com.app.restapi.jpa.entity.Student;
import com.app.restapi.model.SortDirection;
import com.app.restapi.resource.StudentResource;
import com.app.restapi.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = RestApiApplication.class)
@AutoConfigureMockMvc// This allows you to inject MockMvc
public class StudentResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getStudents() throws Exception {
        // 1. Arrange the Input DTO
        PaginationDto pagination = new PaginationDto();
        pagination.setPage(0);
        pagination.setSize(10);
        pagination.setSearchTerm("Jane");
        pagination.setSortField("firstName");
        pagination.setSortDirection(SortDirection.ASC);

        // Given
        StudentDto student = new StudentDto().setFirstName("Jane").setLastName("Doe");
        PageImpl<StudentDto> page = new PageImpl<>(Collections.singletonList(student));

        when(studentService.getStudents(any(PaginationDto.class))).thenReturn(page);

        // When & Then
        mockMvc.perform(post("/api/students/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pagination)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].firstName").value("Jane"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Disabled("Reason: Strict role based authentication not implemented yet")
    @Test
    void shouldReturn401WhenNotAuthenticated() throws Exception {
        // 1. Arrange the Input DTO
        PaginationDto pagination = new PaginationDto();
        pagination.setPage(0);
        pagination.setSize(10);
        pagination.setSearchTerm("Jane");
        pagination.setSortField("firstName");
        pagination.setSortDirection(SortDirection.ASC);

        mockMvc.perform(post("/api/students/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pagination)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn200Authenticated() throws Exception {
        // 1. Arrange the Input DTO
        PaginationDto pagination = new PaginationDto();
        pagination.setPage(0);
        pagination.setSize(10);
        pagination.setSearchTerm("Jane");
        pagination.setSortField("firstName");
        pagination.setSortDirection(SortDirection.ASC);

        mockMvc.perform(post("/api/students/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pagination)))
                .andExpect(status().isOk());
    }
}
