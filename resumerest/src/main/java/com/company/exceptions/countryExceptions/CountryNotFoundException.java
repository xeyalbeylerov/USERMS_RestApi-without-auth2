package com.company.exceptions.countryExceptions;

public class CountryNotFoundException extends Exception{
    public CountryNotFoundException() {
    }

    public CountryNotFoundException(String message) {
        super(message);
    }
}
