/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.Skill;

import java.util.List;

/**
 * @author Khayal Baylarov
 */
public interface SkillServiceInter {

    public List<Skill> getAll();

    Skill updateSkill(Skill u);

    void removeSkill(int id);

    public Skill getByName(String name);

    public Skill insertSkill(Skill skl);

    boolean existsSkillByName(String name);

    boolean isIdExists(Integer id);

    Skill getById(int id);
}