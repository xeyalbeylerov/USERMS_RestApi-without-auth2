package com.company.service.impl;


import com.company.entity.Skill;
import com.company.exceptions.skillExceptions.SkillAlreadyExistsException;
import com.company.exceptions.skillExceptions.SkillNotFoundException;
import com.company.service.inter.SkillServiceInter;
import com.company.service.inter.SkillServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xeyal
 */

@Service(value = "skillServiceRest")
@RequiredArgsConstructor
public class SkillServiceRestImpl implements SkillServiceRestInter {

    private SkillServiceInter skillDao;

    @Override
    public List<Skill> getAll() {
        return skillDao.getAll();
    }

    @Override
    public Skill getById(int id) throws SkillNotFoundException {
        if (!skillDao.isIdExists(id)) throw new SkillNotFoundException();
        return skillDao.getById(id);
    }

    @Override
    public Skill updateSkill(Skill skill) throws SkillNotFoundException, SkillAlreadyExistsException {
        //check skill exists
        boolean isExists = skillDao.isIdExists(skill.getId());
        if (!isExists) throw new SkillNotFoundException();

        //check dublicate skill
        boolean isAlreadyExists = skillDao.existsSkillByName(skill.getName());
        if (isAlreadyExists) throw new SkillAlreadyExistsException();
        return skillDao.updateSkill(skill);
    }

    @Override
    public Skill insertSkill(Skill skill) throws SkillAlreadyExistsException {
        //check skill exists
        boolean isExists = skillDao.existsSkillByName(skill.getName());
        if (isExists) throw new SkillAlreadyExistsException();

        return skillDao.insertSkill(skill);
    }


    @Override
    public void removeSkill(int id) throws SkillNotFoundException {
        //check skill exists
        boolean isExists = skillDao.isIdExists(id);
        if (!isExists) throw new SkillNotFoundException();
        skillDao.removeSkill(id);
    }

    @Override
    public Skill getByName(String name) throws SkillNotFoundException {
        //check skill exists
        boolean isExists = skillDao.existsSkillByName(name);
        if (!isExists) throw new SkillNotFoundException();
        return skillDao.getByName(name);
    }

    @Override
    public boolean existsSkillById(Integer id) {
        return skillDao.isIdExists(id);
    }

}
