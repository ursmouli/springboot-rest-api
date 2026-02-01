package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.Section;
import com.app.restapi.jpa.entity.SectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SectionRepository extends JpaRepository<Section, SectionId>, JpaSpecificationExecutor<Section> {

    boolean existsBySchoolClassIdAndClassTeacherId(Long classId, Long teacherId);
}
