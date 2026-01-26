package com.app.restapi.converter;

import com.app.restapi.dto.EmployeeDto;
import com.app.restapi.dto.GuardianDto;
import com.app.restapi.jpa.entity.Department;
import com.app.restapi.jpa.entity.Employee;
import com.app.restapi.jpa.entity.Guardian;
import com.app.restapi.jpa.entity.Sibling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeConverter implements GenericConverter<Employee, EmployeeDto> {

    private final AddressConverter addressConverter;
    private final GuardianConverter guardianConverter;
    private final SiblingConverter siblingConverter;

    public EmployeeConverter(AddressConverter addressConverter,
                            GuardianConverter guardianConverter,
                            SiblingConverter siblingConverter) {
        this.addressConverter = addressConverter;
        this.guardianConverter = guardianConverter;
        this.siblingConverter = siblingConverter;
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        if (dto == null) return null;

        Employee entity = new Employee()
                .setFirstName(dto.getFirstName())
                .setMiddleName(dto.getMiddleName())
                .setLastName(dto.getLastName())
                .setEmployeeNumber(dto.getEmployeeNumber())
                .setDob(dto.getDob())
                .setGender(dto.getGender())
                .setSameAsPermanentAddress(dto.isSameAsPermanentAddress())
                .setMartialStatus(dto.getMaritalStatus())
                .setRole(dto.getRole())
                .setPreviousEmployment(dto.getPreviousEmployment())
                .setDepartment(new Department().setName(dto.getDepartmentName()));

        if (dto.getPermanentAddress() != null) {
            entity.setPermanentAddress(addressConverter.toEntity(dto.getPermanentAddress()));
        }
        if (dto.getResidentialAddress() != null) {
            entity.setResidentialAddress(addressConverter.toEntity(dto.getResidentialAddress()));
        }

        if (!dto.getGuardians().isEmpty()) {
            List<Guardian> guardiansList = new ArrayList<>();
            dto.getGuardians().forEach(guardianDto -> guardiansList.add(guardianConverter.toEntity(guardianDto)));
            entity.setGuardians(guardiansList);
        }

        if (!dto.getSiblings().isEmpty()) {
            List<Sibling> siblingList = new ArrayList<>();
            dto.getSiblings().forEach(siblingDto -> siblingList.add(siblingConverter.toEntity(siblingDto)));
            entity.setSiblings(siblingList);
        }

        return entity;
    }

    @Override
    public EmployeeDto toDto(Employee entity) {
        if (entity == null) return null;

        EmployeeDto dto = new EmployeeDto()
                .setId(entity.getId())
                .setFirstName(entity.getFirstName())
                .setMiddleName(entity.getMiddleName())
                .setLastName(entity.getLastName())
                .setDob(entity.getDob())
                .setGender(entity.getGender())
                .setEmployeeNumber(entity.getEmployeeNumber())
                .setSameAsPermanentAddress(entity.isSameAsPermanentAddress())
                .setMaritalStatus(entity.getMartialStatus())
                .setPreviousEmployment(entity.getPreviousEmployment())
                .setRole(entity.getRole())
                .setDepartmentName(entity.getDepartment() != null ? entity.getDepartment().getName() : null);

        if (entity.getPermanentAddress() != null) {
            dto.setPermanentAddress(addressConverter.toDto(entity.getPermanentAddress()));
        }
        if (entity.getResidentialAddress() != null) {
            dto.setResidentialAddress(addressConverter.toDto(entity.getResidentialAddress()));
        }

        if (!entity.getGuardians().isEmpty()) {
            List<GuardianDto> guardians = new ArrayList<>();
            entity.getGuardians().forEach(guardian -> guardians.add(guardianConverter.toDto(guardian)));
            dto.setGuardians(guardians);
        }

        return dto;
    }
}
