package com.company.repo;

import com.company.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface SkillRepository extends JpaRepository<Skill,Integer> {

    Skill findSkillByName(String skillName);

    boolean existsSkillById(Integer id);
    boolean existsSkillByName(String name);

}
