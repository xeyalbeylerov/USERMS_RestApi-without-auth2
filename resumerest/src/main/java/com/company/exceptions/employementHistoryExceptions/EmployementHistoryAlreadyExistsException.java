package com.company.exceptions.employementHistoryExceptions;

public class EmployementHistoryAlreadyExistsException extends Exception{
    public EmployementHistoryAlreadyExistsException() {
    }

    public EmployementHistoryAlreadyExistsException(String message) {
        super(message);
    }
}
