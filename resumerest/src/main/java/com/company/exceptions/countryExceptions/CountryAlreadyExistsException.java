package com.company.exceptions.countryExceptions;

public class CountryAlreadyExistsException extends Exception{
    public CountryAlreadyExistsException() {
    }

    public CountryAlreadyExistsException(String message) {
        super(message);
    }
}
