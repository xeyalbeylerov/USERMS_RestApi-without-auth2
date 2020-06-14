package com.company.dto;

import com.company.entity.Skill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SkillDTO {
    private Integer id;
    private String name;



    public SkillDTO(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
    }

}
