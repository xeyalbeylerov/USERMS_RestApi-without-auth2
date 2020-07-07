/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;

import com.company.repo.UserSkillRepository;
import com.company.entity.UserSkill;
import com.company.service.inter.UserSkillServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Khayal Baylarov
 */
@Transactional
@Service(value = "userSkillService")
@RequiredArgsConstructor
public class UserSkillServiceImpl implements UserSkillServiceInter {

    private final UserSkillRepository userSkillDao;

    @Override
    public List<UserSkill> getAllSkillByUserId(int id) {
        return userSkillDao.findByUser_id(id);
    }

    @Override
    public UserSkill insertUserSkill(UserSkill u) {
        return userSkillDao.save(u);
    }

    @Override
    public UserSkill updateUserSkill(UserSkill u) {
        return userSkillDao.save(u);
    }

    @Override
    public void removeUserSkill(int id) {
       userSkillDao.deleteById(id);
    }
    @Override
    public boolean existsUserSkillByUserIdAndSkillId(Integer userId, Integer skillId) {
        return userSkillDao.existsUserSkillByUser_IdAndSkillId(userId, skillId);
    }
    @Override
    public boolean isIdExists(Integer id) {
        return userSkillDao.existsUserSkillById(id);
    }
    @Override
    public UserSkill getUserSkillById(int id) {
        return userSkillDao.getOne(id);
    }
}
