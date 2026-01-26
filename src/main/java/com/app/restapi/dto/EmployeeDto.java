package com.app.restapi.dto;

public class EmployeeDto extends RegistrationDto<EmployeeInfo, EmployeeDto> {

    private String employeeNumber;
    private String maritalStatus;
    private String previousEmployment;
    private String role;
    private String departmentName;

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public EmployeeDto setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        return this;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public EmployeeDto setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public String getPreviousEmployment() {
        return previousEmployment;
    }

    public EmployeeDto setPreviousEmployment(String previousEmployment) {
        this.previousEmployment = previousEmployment;
        return this;
    }

    public String getRole() {
        return role;
    }

    public EmployeeDto setRole(String role) {
        this.role = role;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public EmployeeDto setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }
}

class EmployeeInfo {}
