/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;

import com.company.repo.SkillRepository;
import com.company.entity.Skill;
import com.company.service.inter.SkillServiceInter;
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
@Service(value = "skillService")
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillServiceInter {

    private final SkillRepository skillDao;

    @Override
    public List<Skill> getAll() {
        return skillDao.findAll();
    }

    @Override
    public Skill updateSkill(Skill u) {
        return skillDao.save(u);
    }

    @Override
    public Skill insertSkill(Skill skill) {
        return skillDao.save(skill);
    }

    @Override
    public void removeSkill(int id) {
         skillDao.deleteById(id);
    }

    @Override
    public Skill getByName(String name) {
        return skillDao.findSkillByName(name);
    }

    @Override
    public boolean existsSkillByName(String name) {
        return skillDao.existsSkillByName(name);
    }
    @Override
    public boolean isIdExists(Integer id) {
        return skillDao.existsSkillById(id);
    }
    @Override
    public Skill getById(int id) {
        return skillDao.getOne(id);

    }
}
