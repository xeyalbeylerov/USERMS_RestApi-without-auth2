package com.company.controller;


import com.company.exceptions.IdIsNullException;
import com.company.exceptions.userExceptions.UserNotFoundException;
import com.company.dto.ResponseDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.service.inter.UserServiceRestInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    @Qualifier("userServiceRest")
    private UserServiceRestInter userService;

    //return all users
    //users?name=&surname=&age=
    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> getUsers(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "age", required = false) Integer age) {
        List<User> users = userService.getAll(name, surname, age);
        List<UserDTO> userDTOS = new ArrayList<>();
        //user-i userDto ya cevirir
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }
        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
    }

//    @GetMapping("/foo")
//    public ResponseEntity<ResponseDTO> foo(
//            @RequestParam(name="name",required = false)String name,
//            @RequestParam(name="surname",required = false)String surname,
//            @RequestParam(name="age",required = false)Integer age)
//    {
//        List<User> users = userService.getAll(name, surname, age);
//        List<UserDTO> userDTOS=new ArrayList<>();
//        for(int i=0;i<users.size();i++){
//            User u=users.get(i);
//            userDTOS.add(new UserDTO(u));
//        }
//        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
//    }


    //return specify user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable("id") int id) {
        User user;
        try {
         user = userService.getById(id);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.ok(ResponseDTO.of(404, "There is no user found with this id"));
        }
        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user)));
    }

    //delete specify user by id and return deleted user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id) {
        User user;
        try {
            user = userService.getById(id);
            userService.removeUser(id);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.ok(ResponseDTO.of(404, "There is no user found with this id"));
        }

        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user), "Successfully deleted"));
    }

    //take json user data and add user then return it
    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDto) {
        UserDTO userDTO;
        try {
            userDTO = userService.addUser(userDto);
        } catch (Exception ex) {
            return ResponseEntity.ok(ResponseDTO.of(500, "User already exists"));
        }
        return ResponseEntity.ok(ResponseDTO.of(userDTO, "Successfully added"));

    }

    //take json user data and update user then return it
    @PutMapping("/users")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO userDto) {
        UserDTO userDTO = null;
        try {
            userDTO = userService.updateUser(userDto);
        } catch (UserNotFoundException | IdIsNullException ex) {
            return ResponseEntity.ok(ResponseDTO.of(404, "There is no user found with this id"));
        }
        return ResponseEntity.ok(ResponseDTO.of(userDTO, "User successfully updated"));
    }
//    @GetMapping("/users")
//    public ResponseEntity getUsers() {
//        return ResponseEntity.status(HttpStatus.OK).body("users");
////        return new ResponseEntity<String>("", HttpStatus.OK).getBody();
//    }
}
