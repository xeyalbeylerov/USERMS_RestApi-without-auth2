/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.dto.UserDto;
import com.company.entity.User;

import java.util.List;

/**
 * @author xeyal
 */
public interface UserServiceRestInter {

    List<User> getAll(String name, String surname);

    List<User> getAll(String name, String surname, Integer nationalityid);

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    User getById(int userId);

    UserDto addUser(UserDto u);

    UserDto updateUser(UserDto userEditDto);

    boolean removeUser(int i);

}
