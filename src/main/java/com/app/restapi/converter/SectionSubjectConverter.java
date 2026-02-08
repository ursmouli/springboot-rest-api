package com.app.restapi.converter;

import com.app.restapi.dto.SectionSubjectDto;
import com.app.restapi.jpa.entity.SectionSubject;
import org.springframework.stereotype.Component;

@Component
public class SectionSubjectConverter implements GenericConverter<SectionSubject, SectionSubjectDto> {
//    private final SectionConverter sectionConverter;
    private final SubjectConverter subjectConverter;

    public SectionSubjectConverter(SubjectConverter subjectConverter) {
//        this.sectionConverter = sectionConverter;
        this.subjectConverter = subjectConverter;
    }

    @Override
    public SectionSubject toEntity(SectionSubjectDto dto) {
        if (dto == null) {
            return null;
        }
        return null;
    }

    @Override
    public SectionSubjectDto toDto(SectionSubject entity) {
        if (entity == null) {
            return null;
        }
        SectionSubjectDto dto = new SectionSubjectDto();
        dto.setId(entity.getId());
        dto.setSubject(subjectConverter.toDto(entity.getSubject()));
        return dto;
    }
}
