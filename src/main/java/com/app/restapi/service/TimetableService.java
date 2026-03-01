package com.app.restapi.service;

import com.app.restapi.dto.PeriodDetailsDto;
import com.app.restapi.dto.TimetableDayRowDto;
import com.app.restapi.dto.TimetableEntryDto;
import com.app.restapi.jpa.entity.Section;
import com.app.restapi.jpa.entity.TimetableEntry;
import com.app.restapi.jpa.repo.SectionRepository;
import com.app.restapi.jpa.repo.TimetableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class TimetableService {

    private static final Logger logger = LoggerFactory.getLogger(TimetableService.class);

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private TimetablePdfService timetablePdfService;

    public List<TimetableDayRowDto> getTimetableForSection(Long schoolClassId, Long classTeacherId) {
        // 1. Fetch all entries (e.g., 40 rows: 5 days * 8 periods)
        List<TimetableEntry> entries = timetableRepository.findBySectionIdSchoolClassIdAndSectionIdClassTeacherId(schoolClassId, classTeacherId);

        // 2. Group entries by Day (Monday, Tuesday, etc.)
        Map<DayOfWeek, List<TimetableEntry>> groupedByDay = groupByDay(entries);
        
        // 3. Map each group to a single TimetableRowDTO
        return getGroupedEntries(groupedByDay);
    }

    public byte[] timetableForSection(Long schoolClassId, Long classTeacherId) {
        List<TimetableEntry> entries = timetableRepository.findBySectionIdSchoolClassIdAndSectionIdClassTeacherId(schoolClassId, classTeacherId);

        Map<DayOfWeek, List<TimetableEntry>> groupedByDay = groupByDay(entries);

        List<TimetableDayRowDto> collect = getGroupedEntries(groupedByDay);

        return timetablePdfService.generatePdfFromTemplate(collect, collect.get(0).getSectionName());
    }

    public List<TimetableDayRowDto> saveAll(List<TimetableEntryDto> timetableEntryDtoList) {
        if (CollectionUtils.isEmpty(timetableEntryDtoList)) {
            throw new IllegalArgumentException("timetableDtoList must not be empty");
        }

        logger.debug("To save timetableEntryDtoList={}", timetableEntryDtoList);

        Section section = sectionRepository.findById(timetableEntryDtoList.get(0).getSectionId()).orElseThrow(EntityNotFoundException::new);

        List<TimetableEntry> timetableEntries = new ArrayList<>();

        for (TimetableEntryDto dto : timetableEntryDtoList) {
            TimetableEntry entry = new TimetableEntry()
                    .setDayOfWeek(DayOfWeek.valueOf(dto.getDayOfWeek()))
                    .setPeriodNumber(dto.getPeriodNumber())
                    .setSubjectName(dto.getSubjectName())
                    .setTeacherName(dto.getTeacherName());

            entry.setSection(section);

            timetableEntries.add(entry);
        }

        List<TimetableEntry> entries = timetableRepository.saveAll(timetableEntries);
        Map<DayOfWeek, List<TimetableEntry>> groupedByDay = groupByDay(entries);

        return getGroupedEntries(groupedByDay);
    }

    private List<TimetableDayRowDto> getGroupedEntries(Map<DayOfWeek, List<TimetableEntry>> groupedByDay) {
        return groupedByDay.entrySet().stream()
                .map(entry -> convertToRowDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
    private Map<DayOfWeek, List<TimetableEntry>> groupByDay(List<TimetableEntry> entries) {
        return entries.stream().collect(Collectors.groupingBy(TimetableEntry::getDayOfWeek));
    }
    private TimetableDayRowDto convertToRowDTO(DayOfWeek day, List<TimetableEntry> dayEntries) {
        TimetableDayRowDto row = new TimetableDayRowDto();
        row.setDayOfWeek(day.name());

        // Fill the specific period fields based on the periodNumber
        for (TimetableEntry entry : dayEntries) {
            // Set section name once (assuming all entries in list are same section)
            if (row.getSectionName() == null) {
                row.setSectionName(entry.getSection().getName());
            }

            // Create details for THIS specific period
            PeriodDetailsDto details = new PeriodDetailsDto()
                    .setSubjectName(entry.getSubjectName())
                    .setTeacherName(entry.getTeacherName())
                    .setClassroom(entry.getClassroom());

            // Put into the map using periodNumber as the key
            row.getPeriods().put(entry.getPeriodNumber(), details);
        }
        return row;
    }
}
