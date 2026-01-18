package com.app.restapi.converter;

import com.app.restapi.dto.GuardianDto;
import com.app.restapi.dto.SiblingDto;
import com.app.restapi.dto.StudentDto;
import com.app.restapi.jpa.entity.Guardian;
import com.app.restapi.jpa.entity.Sibling;
import com.app.restapi.jpa.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentConverter implements GenericConverter<Student, StudentDto> {

    private final AddressConverter addressConverter;
    private final GuardianConverter guardianConverter;
    private final SiblingConverter siblingConverter;

    public StudentConverter(AddressConverter addressConverter,
                            GuardianConverter guardianConverter,
                            SiblingConverter siblingConverter) {
        this.addressConverter = addressConverter;
        this.guardianConverter = guardianConverter;
        this.siblingConverter = siblingConverter;
    }

    @Override
    public Student toEntity(StudentDto dto) {
        if (dto == null) return null;
        Student entity = new Student()
                .setFirstName(dto.getFirstName())
                .setMiddleName(dto.getMiddleName())
                .setLastName(dto.getLastName())
                .setRegistrationNumber(dto.getRegistrationNumber())
                .setDob(dto.getDob())
                .setSameAsPermanentAddress(dto.isSameAsPermanentAddress());

        if (dto.getPermanentAddress() != null) {
            entity.setPermanentAddress(addressConverter.toEntity(dto.getPermanentAddress()));
        }
        if (dto.getResidentialAddress() != null) {
            entity.setResidentialAddress(addressConverter.toEntity(dto.getResidentialAddress()));
        }

        if (!dto.getGuardians().isEmpty()) {
            List<Guardian> guardiansList = new ArrayList<>();
            dto.getGuardians().forEach(guardianDto -> {
                guardiansList.add(guardianConverter.toEntity(guardianDto));
            });
            entity.setGuardians(guardiansList);
        }

        if (!dto.getSiblings().isEmpty()) {
            List<Sibling> siblingList = new ArrayList<>();
            dto.getSiblings().forEach(siblingDto -> {
                siblingList.add(siblingConverter.toEntity(siblingDto));
            });
            entity.setSiblings(siblingList);
        }

        return entity;
    }

    @Override
    public StudentDto toDto(Student entity) {
        if (entity == null) return null;
        StudentDto dto = new StudentDto()
                .setId(entity.getId())
                .setFirstName(entity.getFirstName())
                .setMiddleName(entity.getMiddleName())
                .setLastName(entity.getLastName())
                .setDob(entity.getDob())
                .setRegistrationNumber(entity.getRegistrationNumber())
                .setSameAsPermanentAddress(entity.isSameAsPermanentAddress());

        if (entity.getPermanentAddress() != null) {
            dto.setPermanentAddress(addressConverter.toDto(entity.getPermanentAddress()));
        }
        if (entity.getResidentialAddress() != null) {
            dto.setResidentialAddress(addressConverter.toDto(entity.getResidentialAddress()));
        }

        if (!entity.getGuardians().isEmpty()) {
            List<GuardianDto> guardians = new ArrayList<>();
            entity.getGuardians().forEach(guardian -> {
                guardians.add(guardianConverter.toDto(guardian));
            });
            dto.setGuardians(guardians);
        }

        return dto;
    }

    public List<StudentDto> toDtoList(List<Student> students) {
        List<StudentDto> ret = new ArrayList<>();

        for (Student student : students) {
            ret.add(toDto(student));
        }

        return ret;
    }
}
