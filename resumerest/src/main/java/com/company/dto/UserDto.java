package com.company.dto;

import com.company.entity.User;
import com.company.entity.UserSkill;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private int id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private List<UserSkillDto> skills;


    public UserDto(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.surname = u.getSurname();
        this.email = u.getEmail();
        List<UserSkillDto> list = new ArrayList<>();

        List<UserSkill> userSkills = u.getSkills();
        for (int i = 0; i < userSkills.size(); i++) {
            UserSkill uSkill = userSkills.get(i);
            list.add(new UserSkillDto(uSkill));
        }
        skills = list;

    }

    public UserDto(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }


}
