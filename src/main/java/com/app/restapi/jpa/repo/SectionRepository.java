package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
}
