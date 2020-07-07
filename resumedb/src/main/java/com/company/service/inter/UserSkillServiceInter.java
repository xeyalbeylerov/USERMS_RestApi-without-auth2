/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.UserSkill;

import java.util.List;

/**
 * @author Khayal Baylarov
 */
public interface UserSkillServiceInter {

    List<UserSkill> getAllSkillByUserId(int id);

    UserSkill insertUserSkill(UserSkill u);

    UserSkill updateUserSkill(UserSkill u);

    void removeUserSkill(int id);

    boolean existsUserSkillByUserIdAndSkillId(Integer userId, Integer skillId);

    boolean isIdExists(Integer id);

    UserSkill getUserSkillById(int id);
}
