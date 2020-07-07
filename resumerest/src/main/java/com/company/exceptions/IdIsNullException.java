package com.company.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class IdIsNullException extends Exception{
    public IdIsNullException() {
    }

    public IdIsNullException(String message) {
        super(message);
    }
}
