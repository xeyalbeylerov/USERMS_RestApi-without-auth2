package com.company.service.impl;


import com.company.entity.Skill;
import com.company.exceptionHandler.exceptions.EntityAlreadyExistsException;
import com.company.exceptionHandler.exceptions.EntityNotFoundException;
import com.company.service.inter.SkillServiceInter;
import com.company.service.inter.SkillServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xeyal
 */

@Service(value = "skillServiceRest")
@RequiredArgsConstructor
public class SkillServiceRestImpl implements SkillServiceRestInter {

    private final SkillServiceInter skillDao;

    @Override
    public List<Skill> getAll() {
        return skillDao.getAll();
    }

    @Override
    public Skill getById(int id){
        if (!skillDao.isIdExists(id)) throw new EntityNotFoundException("Skill not found");
        return skillDao.getById(id);
    }

    @Override
    public Skill updateSkill(Skill skill){
        //check skill exists
        boolean isExists = skillDao.isIdExists(skill.getId());
        if (!isExists) throw new EntityNotFoundException("Skill not found");

        //check dublicate skill
        boolean isAlreadyExists = skillDao.existsSkillByName(skill.getName());
        if (isAlreadyExists) throw new EntityAlreadyExistsException("Skill already exists");
        return skillDao.updateSkill(skill);
    }

    @Override
    public Skill insertSkill(Skill skill){
        //check skill exists
        boolean isExists = skillDao.existsSkillByName(skill.getName());
        if (isExists) throw new EntityAlreadyExistsException("Skill already exists");

        return skillDao.insertSkill(skill);
    }


    @Override
    public void removeSkill(int id){
        //check skill exists
        boolean isExists = skillDao.isIdExists(id);
        if (!isExists) throw new EntityNotFoundException("Skill not found");
        skillDao.removeSkill(id);
    }

    @Override
    public Skill getByName(String name){
        //check skill exists
        boolean isExists = skillDao.existsSkillByName(name);
        if (!isExists) throw new EntityNotFoundException("Skill not found");
        return skillDao.getByName(name);
    }

    @Override
    public boolean existsSkillById(Integer id) {
        return skillDao.isIdExists(id);
    }

}
