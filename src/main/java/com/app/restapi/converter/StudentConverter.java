package com.app.restapi.converter;

import com.app.restapi.dto.GuardianDto;
import com.app.restapi.dto.StudentDto;
import com.app.restapi.jpa.entity.Guardian;
import com.app.restapi.jpa.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentConverter {

    private final AddressConverter addressConverter;
    private final GuardianConverter guardianConverter;

    public StudentConverter(AddressConverter addressConverter, GuardianConverter guardianConverter) {
        this.addressConverter = addressConverter;
        this.guardianConverter = guardianConverter;
    }


    public Student toEntity(StudentDto dto) {
        Student entity = new Student()
                .setId(dto.getId())
                .setFirstName(dto.getFirstName())
                .setMiddleName(dto.getMiddleName())
                .setLastName(dto.getLastName())
                .setRegistrationNumber(dto.getRegistrationNumber())
                .setDob(dto.getDob());

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

        return entity;
    }

    public StudentDto toDto(Student entity) {
        StudentDto dto = new StudentDto()
                .setId(entity.getId())
                .setFirstName(entity.getFirstName())
                .setMiddleName(entity.getMiddleName())
                .setLastName(entity.getLastName())
                .setDob(entity.getDob())
                .setRegistrationNumber(entity.getRegistrationNumber());

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
}
