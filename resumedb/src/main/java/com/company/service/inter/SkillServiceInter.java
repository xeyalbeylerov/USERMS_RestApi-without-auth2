/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.Skill;

import java.util.List;

/**
 *
 * @author Xeyal
 */
public interface SkillServiceInter {

    public List<Skill> getAll();

    public Skill getById(int id);

    Skill updateSkill(Skill u);
    boolean isIdExists(Integer id);
    void removeSkill(int id);
    boolean existsSkillByName(String name);
    public Skill getByName(String name);

    public Skill insertSkill(Skill skl);
}