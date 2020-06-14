package com.company.exceptions.skillExceptions;

public class SkillAlreadyExistsException extends Exception{
    public SkillAlreadyExistsException() {
    }

    public SkillAlreadyExistsException(String message) {
        super(message);
    }
}
