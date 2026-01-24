package com.appt.restapi.jpa;

import com.app.restapi.RestApiApplication;
import com.app.restapi.converter.AddressConverter;
import com.app.restapi.converter.GuardianConverter;
import com.app.restapi.converter.SiblingConverter;
import com.app.restapi.jpa.entity.Student;
import com.app.restapi.jpa.repo.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//@ContextConfiguration(classes = RestApiApplication.class)
//@Import(AddressConverter.class)
@SpringBootTest(classes = {RestApiApplication.class})
@Transactional // Ensures DB changes are rolled back after each test
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private GuardianConverter guardianConverter;

    @Autowired
    private SiblingConverter siblingConverter;

    @BeforeEach
    public void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    public void shouldSaveAndFindStudent() {
        Student student = new Student().setFirstName("John")
                .setLastName("Doe")
                .setDob(LocalDate.now())
                .setGender("Male")
                .setSameAsPermanentAddress(true);

        Student savedStudent = studentRepository.save(student);

        Optional<Student> found = studentRepository.findById(savedStudent.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo("John");
    }
}
