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
public interface UserSkillServiceInter {

    List<UserSkill> getAllSkillByUserId(int id);

    UserSkill getUserSkillById(int id);

    UserSkill insertUserSkill(UserSkill u);

    UserSkill updateUserSkill(UserSkill u);

    boolean isIdExists(Integer id);

    boolean existsUserSkillByUserIdAndSkillId(Integer userId, Integer skillId);

    void removeUserSkill(int id);
}
