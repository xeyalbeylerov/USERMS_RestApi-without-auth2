package com.company.dto;

import com.company.entity.User;
import com.company.entity.UserSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter @NoArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private List<UserSkillDTO> skills;


    public UserDTO(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.surname = u.getSurname();
        this.email=u.getEmail();
        List<UserSkillDTO> list = new ArrayList<>();

        List<UserSkill> userSkills = u.getSkills();
        for (int i = 0; i < userSkills.size(); i++) {
            UserSkill uSkill = userSkills.get(i);
            list.add(new UserSkillDTO(uSkill));
        }
        skills = list;

    }

    public UserDTO(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }


}
