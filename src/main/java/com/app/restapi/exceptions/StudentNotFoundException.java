package com.app.restapi.exceptions;

public class StudentNotFoundException extends RuntimeException {

	public StudentNotFoundException(String message) {
        super(message);
    }
}
