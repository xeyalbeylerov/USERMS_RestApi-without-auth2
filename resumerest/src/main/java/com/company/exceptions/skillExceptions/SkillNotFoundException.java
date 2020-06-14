package com.company.exceptions.skillExceptions;

public class SkillNotFoundException extends Exception{
    public SkillNotFoundException() {
    }

    public SkillNotFoundException(String message) {
        super(message);
    }
}
