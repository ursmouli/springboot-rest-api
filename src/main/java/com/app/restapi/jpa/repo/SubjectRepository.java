package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
