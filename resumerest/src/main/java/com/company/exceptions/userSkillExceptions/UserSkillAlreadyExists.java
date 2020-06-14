package com.company.exceptions.userSkillExceptions;

public class UserSkillAlreadyExists extends Exception{
    public UserSkillAlreadyExists() {
    }

    public UserSkillAlreadyExists(String message) {
        super(message);
    }
}
