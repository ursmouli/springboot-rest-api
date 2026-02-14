package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.Section;
import com.app.restapi.jpa.entity.SectionSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionSubjectRepository extends JpaRepository<Section, SectionSubjectId> {
}
