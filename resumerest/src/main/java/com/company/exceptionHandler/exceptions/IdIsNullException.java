package com.company.exceptionHandler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class IdIsNullException extends RuntimeException{
    public IdIsNullException() {
    }

    public IdIsNullException(String message) {
        super(message);
    }
}
