package com.app.restapi.resource;

import com.app.restapi.dto.TimetableDayRowDto;
import com.app.restapi.dto.TimetableEntryDto;
import com.app.restapi.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
public class TimetableResource {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/bySection/{schoolClassId}/{classTeacherId}")
    public ResponseEntity<List<TimetableDayRowDto>> getTimetableBySectionId(@PathVariable Long schoolClassId, @PathVariable Long classTeacherId) {
        List<TimetableDayRowDto> timetableForSection = timetableService.getTimetableForSection(schoolClassId, classTeacherId);
        return new ResponseEntity<>(timetableForSection, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<List<TimetableDayRowDto>> add(@RequestBody List<TimetableEntryDto> timetableEntryDto) {
        List<TimetableDayRowDto> timetable = timetableService.saveAll(timetableEntryDto);
        return new ResponseEntity<>(timetable, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public void update() {}

    public void delete() {}

    @GetMapping("/download/{schoolClassId}/{classTeacherId}")
    public ResponseEntity<byte[]> download(@PathVariable Long schoolClassId, @PathVariable Long classTeacherId) {

        byte[] pdfContent = timetableService.timetableForSection(schoolClassId, classTeacherId);
        String sectionName = "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("timetable_" + sectionName + ".pdf").build());

        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }

}
