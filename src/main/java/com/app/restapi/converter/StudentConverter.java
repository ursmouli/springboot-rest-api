package com.app.restapi.converter;

import com.app.restapi.dto.GuardianDto;
import com.app.restapi.dto.StudentDto;
import com.app.restapi.jpa.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentConverter {

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private GuardianConverter guardianConverter;

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
