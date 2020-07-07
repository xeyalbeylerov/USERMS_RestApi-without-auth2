package com.company.dto;

import com.company.entity.UserSkill;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSkillDto {
    private Integer id;
    private Integer power;
    private SkillDto skill;


    public UserSkillDto(UserSkill userSkill) {
        this.id = userSkill.getId();
        this.power = userSkill.getPower();
        this.skill = new SkillDto(userSkill.getSkill());
    }

}
