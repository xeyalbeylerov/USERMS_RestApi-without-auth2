/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.UserSkill;
import com.company.exceptions.IdIsNullException;
import com.company.exceptions.skillExceptions.SkillNotFoundException;
import com.company.exceptions.userExceptions.UserNotFoundException;
import com.company.exceptions.userSkillExceptions.UserSkillAlreadyExists;
import com.company.exceptions.userSkillExceptions.UserSkillNotFoundException;

import java.util.List;

/**
 * @author Xeyal
 */
public interface UserSkillServiceRestInter {

    List<UserSkill> getAllSkillByUserId(int id);

    UserSkill insertUserSkill(Integer userId,UserSkill u) throws UserNotFoundException, UserSkillAlreadyExists, SkillNotFoundException;
    UserSkill getUserSkillById(int id) throws UserSkillNotFoundException;
    UserSkill updateUserSkill(Integer userId,UserSkill u) throws IdIsNullException, UserSkillNotFoundException, UserNotFoundException;

    void removeUserSkill(int id);
}
