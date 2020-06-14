package com.company.dto;

import com.company.entity.UserSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSkillDTO {
    private Integer id;
    private Integer power;
    private SkillDTO skill;



    public UserSkillDTO(UserSkill userSkill) {
        this.id =userSkill.getId();
        this.power = userSkill.getPower();
        this.skill = new SkillDTO(userSkill.getSkill());
    }

}
