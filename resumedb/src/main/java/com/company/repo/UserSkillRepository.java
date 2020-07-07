package com.company.repo;

import com.company.entity.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill,Integer> {
    List<UserSkill> findByUser_id(Integer id);
    boolean existsUserSkillById(Integer id);
    boolean existsUserSkillByUser_IdAndSkillId(Integer userId,Integer skillId);
}
