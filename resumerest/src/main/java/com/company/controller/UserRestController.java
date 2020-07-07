package com.company.controller;


import com.company.exceptions.IdIsNullException;
import com.company.exceptions.userExceptions.UserNotFoundException;
import com.company.dto.ResponseDto;
import com.company.dto.UserDto;
import com.company.entity.User;
import com.company.service.inter.UserServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserServiceRestInter userService;


    //return all users
    //users?name=&surname=&age=
    @GetMapping("/users")
    public ResponseEntity<ResponseDto> getUsers(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "age", required = false) Integer age) {
        List<User> users = userService.getAll(name, surname, age);
        List<UserDto> userDtos = new ArrayList<>();
        //convert User to UserDto
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDtos.add(new UserDto(u));
        }
        return ResponseEntity.ok(ResponseDto.of(userDtos));
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
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") int id) {
        User user;
        try {
         user = userService.getById(id);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is no user found with this id"));
        }
        return ResponseEntity.ok(ResponseDto.of(new UserDto(user)));
    }

    //delete specify user by id and return deleted user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable("id") int id) {
        User user;
        try {
            user = userService.getById(id);
            userService.removeUser(id);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is no user found with this id"));
        }

        return ResponseEntity.ok(ResponseDto.of(new UserDto(user), "Successfully deleted"));
    }

    //take json user data and add user then return it
    @PostMapping("/users")
    public ResponseEntity<ResponseDto> addUser(@RequestBody UserDto userDto) {
        UserDto userDTO;
        try {
            userDTO = userService.addUser(userDto);
        } catch (Exception ex) {
            return ResponseEntity.ok(ResponseDto.of(400, "User already exists"));
        }
        return ResponseEntity.ok(ResponseDto.of(userDTO, "Successfully added"));

    }

    //take json user data and update user then return it
    @PutMapping("/users")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto) {
        UserDto userDTO;
        try {
            userDTO = userService.updateUser(userDto);
        } catch (UserNotFoundException | IdIsNullException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is no user found with this id"));
        }
        return ResponseEntity.ok(ResponseDto.of(userDTO, "User successfully updated"));
    }
//    @GetMapping("/users")
//    public ResponseEntity getUsers() {
//        return ResponseEntity.status(HttpStatus.OK).body("users");
////        return new ResponseEntity<String>("", HttpStatus.OK).getBody();
//    }
}
