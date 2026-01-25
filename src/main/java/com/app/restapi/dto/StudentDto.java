package com.app.restapi.dto;

public class StudentDto extends RegistrationDto<StudentInfo, StudentDto> {

    private String rollNumber;

    public String getRollNumber() {
        return rollNumber;
    }

    public StudentDto setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
        return this;
    }
}


class StudentInfo {
}