/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.exceptions.IdIsNullException;
import com.company.exceptions.userExceptions.UserNotFoundException;
import com.company.dto.UserDTO;
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

    User getById(int userId) throws UserNotFoundException;

    UserDTO addUser(UserDTO u) throws Exception;

    UserDTO updateUser(UserDTO userEditDto) throws IdIsNullException, UserNotFoundException;

    boolean removeUser(int i) throws UserNotFoundException;

}
