package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.TimetableEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimetableRepository extends JpaRepository<TimetableEntry, Long> {
    // Find by the composite ID fields of the Section
    List<TimetableEntry> findBySectionIdSchoolClassIdAndSectionIdClassTeacherId(
            Long schoolClassId,
            Long classTeacherId
    );

    // Optimized for the PDF: Find by ID and Sort by Day/Period
    List<TimetableEntry> findBySectionIdSchoolClassIdAndSectionIdClassTeacherIdOrderByDayOfWeekAscPeriodNumberAsc(
            Long schoolClassId,
            Long classTeacherId
    );
}
