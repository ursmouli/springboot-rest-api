package com.app.restapi.service;

import com.app.restapi.dto.SubjectDto;
import com.app.restapi.jpa.entity.SchoolClass;
import com.app.restapi.jpa.entity.Subject;
import com.app.restapi.jpa.repo.SchoolClassRepository;
import com.app.restapi.jpa.repo.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SchoolClassRepository  schoolClassRepository;


    public SubjectService(SubjectRepository subjectRepository,
                          SchoolClassRepository schoolClassRepository) {
        this.subjectRepository = subjectRepository;
        this.schoolClassRepository = schoolClassRepository;
    }

    @Transactional
    public SubjectDto addSubject(SubjectDto subjectDto) {

        Subject subject = new Subject().setName(subjectDto.getName());

        Subject savedSubject = subjectRepository.save(subject);

        return new SubjectDto().setId(savedSubject.getId()).setName(savedSubject.getName());
    }

    @Transactional
    public void assignSubjectToClass(Long classId, Long subjectId) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

//        schoolClass.addSubject(subject);
        // Saved automatically due to @Transactional
    }
}
