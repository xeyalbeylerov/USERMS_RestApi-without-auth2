/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.UserSkill;

import java.util.List;

/**
 * @author Xeyal
 */
public interface UserSkillServiceRestInter {

    List<UserSkill> getAllSkillByUserId(int id);

    UserSkill insertUserSkill(Integer userId, UserSkill u);

    UserSkill getUserSkillById(int id);

    UserSkill updateUserSkill(Integer userId, UserSkill u);

    void removeUserSkill(int id);
}
