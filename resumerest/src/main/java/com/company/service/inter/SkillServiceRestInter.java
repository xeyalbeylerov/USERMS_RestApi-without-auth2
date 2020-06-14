/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.Skill;
import com.company.exceptions.skillExceptions.SkillAlreadyExistsException;
import com.company.exceptions.skillExceptions.SkillNotFoundException;

import java.util.List;

/**
 *
 * @author Xeyal
 */
public interface SkillServiceRestInter {

    public List<Skill> getAll();

    public Skill getById(int id) throws SkillNotFoundException;

    Skill updateSkill(Skill u) throws SkillNotFoundException, SkillAlreadyExistsException;

    void removeSkill(int id);

    public Skill getByName(String name);
    boolean existsSkillById(Integer id);
    public Skill insertSkill(Skill skl) throws SkillAlreadyExistsException;
}