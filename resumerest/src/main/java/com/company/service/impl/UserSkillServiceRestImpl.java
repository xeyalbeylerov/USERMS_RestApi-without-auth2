package com.company.service.impl;


import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.exceptionHandler.exceptions.EntityAlreadyExistsException;
import com.company.exceptionHandler.exceptions.EntityNotFoundException;
import com.company.exceptionHandler.exceptions.IdIsNullException;
import com.company.service.inter.UserServiceRestInter;
import com.company.service.inter.UserSkillServiceInter;
import com.company.service.inter.UserSkillServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xeyal
 */

@Service(value = "userSkillServiceRest")
@RequiredArgsConstructor
public class UserSkillServiceRestImpl implements UserSkillServiceRestInter {

    private final UserSkillServiceInter userSkillDao;
    private final UserServiceRestInter userService;
    private final SkillServiceRestImpl skillService;

    @Override
    public List<UserSkill> getAllSkillByUserId(int id) {
        return userSkillDao.getAllSkillByUserId(id);
    }

    @Override
    public UserSkill insertUserSkill(Integer userId, UserSkill userSkill){
        //get User
        User u = null;
            u = userService.getById(userId);
        //get skill id
        Integer id = userSkill.getSkill().getId();

        //check user have skill or not
        boolean isAlreadyExistsSkill = userSkillDao.existsUserSkillByUserIdAndSkillId(userId, id);
        if (isAlreadyExistsSkill) {
            throw new EntityAlreadyExistsException("User Skill already exists");
        }
        //check skill exists or not
        boolean isSkillExists=skillService.existsSkillById(id);
        if(!isSkillExists&&id!=null){
            throw new EntityNotFoundException("Skill not found");
        }


        userSkill.setUser(u);
        return userSkillDao.insertUserSkill(userSkill);
    }

    @Override
    public UserSkill getUserSkillById(int id){
        boolean isUserSkillExists=userSkillDao.isIdExists(id);
        if (!isUserSkillExists)throw new EntityNotFoundException("User Skill not found");
        return userSkillDao.getUserSkillById(id);
    }

    @Override
    public UserSkill updateUserSkill(Integer userId, UserSkill userSkill){
        //get User
        User user = null;
            user = userService.getById(userId);
        userSkill.setUser(user);

        Integer id = userSkill.getId();
        //check id null
        if (id == null) throw new IdIsNullException("User skill id is null");
        //check user is exists
        if (!userSkillDao.isIdExists(id)) throw new EntityNotFoundException("User Skill not found");
        return userSkillDao.updateUserSkill(userSkill);
    }

    @Override
    public void removeUserSkill(int id) {
        userSkillDao.removeUserSkill(id);
    }
}
