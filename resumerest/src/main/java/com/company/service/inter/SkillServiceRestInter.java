/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.Skill;

import java.util.List;

/**
 * @author Xeyal
 */
public interface SkillServiceRestInter {
    List<Skill> getAll();

    Skill getById(int id);

    Skill updateSkill(Skill u);

    void removeSkill(int id);

    Skill getByName(String name);

    boolean existsSkillById(Integer id);

    public Skill insertSkill(Skill skl);
}