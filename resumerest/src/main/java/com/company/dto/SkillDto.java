package com.company.dto;

import com.company.entity.Skill;
import lombok.Data;

@Data
public class SkillDto {
    private Integer id;
    private String name;


    public SkillDto(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
    }

}
