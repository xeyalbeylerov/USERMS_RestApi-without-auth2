package com.company.service.impl;


import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.exceptions.IdIsNullException;
import com.company.exceptions.skillExceptions.SkillNotFoundException;
import com.company.exceptions.userExceptions.UserNotFoundException;
import com.company.exceptions.userSkillExceptions.UserSkillAlreadyExists;
import com.company.exceptions.userSkillExceptions.UserSkillNotFoundException;
import com.company.service.inter.UserServiceRestInter;
import com.company.service.inter.UserSkillServiceInter;
import com.company.service.inter.UserSkillServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public UserSkill insertUserSkill(Integer userId, UserSkill userSkill) throws UserNotFoundException, UserSkillAlreadyExists, SkillNotFoundException {
        //get User
        User u = null;
        try {
            u = userService.getById(userId);
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException();
        }

        //get skill id
        Integer id = userSkill.getSkill().getId();

        //check user have skill or not
        boolean isAlreadyExistsSkill = userSkillDao.existsUserSkillByUserIdAndSkillId(userId, id);
        if (isAlreadyExistsSkill) {
            throw new UserSkillAlreadyExists();
        }
        //check skill exists or not
        boolean isSkillExists=skillService.existsSkillById(id);
        if(!isSkillExists&&id!=null){
            throw new SkillNotFoundException();
        }


        userSkill.setUser(u);
        return userSkillDao.insertUserSkill(userSkill);
    }

    @Override
    public UserSkill getUserSkillById(int id) throws UserSkillNotFoundException {
        boolean isUserSkillExists=userSkillDao.isIdExists(id);
        if (!isUserSkillExists)throw new UserSkillNotFoundException();
        return userSkillDao.getUserSkillById(id);
    }

    @Override
    public UserSkill updateUserSkill(Integer userId, UserSkill userSkill) throws IdIsNullException, UserSkillNotFoundException, UserNotFoundException {
        //get User
        User user = null;
        try {
            user = userService.getById(userId);
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException();
        }
        userSkill.setUser(user);

        Integer id = userSkill.getId();
        //check id null
        if (id == null) throw new IdIsNullException();
        //check user is exists
        if (!userSkillDao.isIdExists(id)) throw new UserSkillNotFoundException();
        return userSkillDao.updateUserSkill(userSkill);
    }

    @Override
    public void removeUserSkill(int id) {
        userSkillDao.removeUserSkill(id);
    }
}
