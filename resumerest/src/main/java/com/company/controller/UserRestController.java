package com.company.controller;


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

    //convert User List to UserDto List
    private List<UserDto> usersToUserDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDtos.add(new UserDto(u));
        }
        return userDtos;
    }

    //return all users
    //users?name=&surname=&age=
    @GetMapping("/users")
    public ResponseEntity<ResponseDto> getUsers(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "age", required = false) Integer age) {
        List<User> users = userService.getAll(name, surname, age);
        //converting User to UserDto
        List<UserDto> userDtos = usersToUserDtos(users);
        return ResponseEntity.ok(ResponseDto.of(userDtos));
    }

    //return specify user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") int id) {
        User user;
        user = userService.getById(id);
        return ResponseEntity.ok(ResponseDto.of(new UserDto(user)));
    }

    //delete specify user by id and return deleted user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable("id") int id) {
        User user;
        user = userService.getById(id);
        userService.removeUser(id);
        return ResponseEntity.ok(ResponseDto.of(new UserDto(user), "Successfully deleted"));
    }

    //take json user data and add user then return it
    @PostMapping("/users")
    public ResponseEntity<ResponseDto> addUser(@RequestBody UserDto userDto) {
        UserDto userDTO;
        userDTO = userService.addUser(userDto);
        return ResponseEntity.ok(ResponseDto.of(userDTO, "Successfully added"));

    }

    //take json user data and update user then return it
    @PutMapping("/users")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto) {
        UserDto userDTO;
        userDTO = userService.updateUser(userDto);
        return ResponseEntity.ok(ResponseDto.of(userDTO, "User successfully updated"));
    }
}
