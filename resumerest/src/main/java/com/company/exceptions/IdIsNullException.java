package com.company.exceptions;

public class IdIsNullException extends Exception{
    public IdIsNullException() {
    }

    public IdIsNullException(String message) {
        super(message);
    }
}
