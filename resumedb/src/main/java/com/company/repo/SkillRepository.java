package com.company.repo;

import com.company.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

    Skill findSkillByName(String skillName);

    boolean existsSkillById(Integer id);
    boolean existsSkillByName(String name);
}
