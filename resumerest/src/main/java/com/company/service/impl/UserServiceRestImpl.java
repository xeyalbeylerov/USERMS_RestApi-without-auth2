/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.exceptions.IdIsNullException;
import com.company.exceptions.userExceptions.UserNotFoundException;
import com.company.dto.UserDto;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import com.company.service.inter.UserServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xeyal
 */


@Service(value = "userServiceRest")
@RequiredArgsConstructor
public class UserServiceRestImpl implements UserServiceRestInter {

    private final UserServiceInter userDao;

    @Override
    public List<User> getAll(String name, String surname) {
        return userDao.getAll(name, surname);
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        return userDao.getAll(name, surname);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean removeUser(int id) throws UserNotFoundException {
        if(!userDao.isIdExists(id))throw new UserNotFoundException();
        userDao.removeUser(id);
        return true;
    }


    @Override
    public UserDto updateUser(UserDto userDTO) throws IdIsNullException, UserNotFoundException {
        Integer id = userDTO.getId();
        //check id null
        if (id == null) throw new IdIsNullException();
        //check user is exists
        if (!userDao.isIdExists(id)) throw new UserNotFoundException();

        User user = userDao.getById(id);
        //update users parameter
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        String password = geHasher.hashToString(4, userDTO.getPassword().toCharArray());
        user.setPassword(password);
        userDao.updateUser(user);
        return userDTO;
    }


    @Override
    public User getById(int id) throws UserNotFoundException {
        if (!userDao.isIdExists(id)) throw new UserNotFoundException();
        User user = userDao.getById(id);
        return user;
    }


    @Autowired
    BCrypt.Hasher geHasher;

    @Override
    public UserDto addUser(UserDto userDto) throws Exception {

        boolean isExists = userDao.isEmailExists(userDto.getEmail());
        if (isExists == true) {
            throw new Exception();
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        String password = geHasher.hashToString(4, userDto.getPassword().toCharArray());
        user.setPassword(password);

        User userD = userDao.addUser(user);

        UserDto userDTO = new UserDto();
        userDTO.setId(userD.getId());
        userDTO.setName(userD.getName());
        userDTO.setSurname(userD.getSurname());
        userDTO.setEmail(userD.getEmail());
        userDTO.setPassword(userD.getPassword());
        return userDTO;
    }


}
