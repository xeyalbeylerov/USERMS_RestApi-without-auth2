package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.SkillDTO;
import com.company.entity.Skill;
import com.company.exceptions.skillExceptions.SkillAlreadyExistsException;
import com.company.exceptions.skillExceptions.SkillNotFoundException;
import com.company.service.inter.SkillServiceRestInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SkillRestController {
    @Autowired
    @Qualifier("skillServiceRest")
    private SkillServiceRestInter skillService;

    //return all users
    //users?name=&surname=&age=
    @GetMapping("/skills")
    public ResponseEntity<ResponseDTO> getSkills() {
        List<Skill> skills = skillService.getAll();

        List<SkillDTO> skillDTOS = new ArrayList<>();
        //skill-i skillDto ya cevirir
        for (int i = 0; i < skills.size(); i++) {
            Skill s = skills.get(i);
            skillDTOS.add(new SkillDTO(s));
        }
        return ResponseEntity.ok(ResponseDTO.of(skillDTOS));
    }


    //return specify skill by id
    @GetMapping("/skills/{id}")
    public ResponseEntity<ResponseDTO> getSkill(@PathVariable("id") int id) {
        Skill skill = null;
        try {
            skill = skillService.getById(id);
        } catch (SkillNotFoundException ex) {
            return ResponseEntity.ok(ResponseDTO.of(404, "There is no skill found with this id"));
        }
        return ResponseEntity.ok(ResponseDTO.of(new SkillDTO(skill)));
    }

    //    delete specify skill by id and return deleted skill
    @DeleteMapping("/skills/{id}")
    public ResponseEntity<ResponseDTO> deleteSkill(@PathVariable("id") int id) {
        Skill skill;
        try {
            skill = skillService.getById(id);
            skillService.removeSkill(id);
        } catch (SkillNotFoundException ex) {
            return ResponseEntity.ok(ResponseDTO.of(404, "There is no skill found with this id"));
        }

        return ResponseEntity.ok(ResponseDTO.of(new SkillDTO(skill), "Successfully deleted"));
    }

    //    //take json skill data and add skill then return it
    @PostMapping("/skills")
    public ResponseEntity<ResponseDTO> addSkill(@RequestBody SkillDTO skillDto) {

        Skill skill = new Skill(null, skillDto.getName());
        try {
            skill = skillService.insertSkill(skill);
        } catch (SkillAlreadyExistsException ex) {
            return ResponseEntity.ok(ResponseDTO.of(500, "Skill already exists"));
        }
        return ResponseEntity.ok(ResponseDTO.of(new SkillDTO(skill), "Successfully added"));

    }

    //
    //take json skill data and update skill then return it
    @PutMapping("/skills")
    public ResponseEntity<ResponseDTO> updateSkill(@RequestBody SkillDTO skillDto) {

        Skill skill = new Skill(skillDto.getId(), skillDto.getName());
        try {
            skill = skillService.updateSkill(skill);
        } catch (SkillNotFoundException ex) {
            return ResponseEntity.ok(ResponseDTO.of(500, "Skill does not exists"));
        } catch (SkillAlreadyExistsException ex) {
            return ResponseEntity.ok(ResponseDTO.of(500, "Skill already exists"));
        }
        return ResponseEntity.ok(ResponseDTO.of(new SkillDTO(skill), "Successfully updated"));
    }

    //take path and json skill name and update skill then return it
    @PutMapping("/skills/{skillid}")
    public ResponseEntity<ResponseDTO> updateSkill(@PathVariable("skillid") int skillId, @RequestBody SkillDTO skillDto) {

        Skill skill = new Skill(skillId, skillDto.getName());
        try {
            skill = skillService.updateSkill(skill);
        } catch (SkillNotFoundException ex) {
            return ResponseEntity.ok(ResponseDTO.of(500, "Skill does not exists"));
        } catch (SkillAlreadyExistsException ex) {
            return ResponseEntity.ok(ResponseDTO.of(500, "Skill already exists"));
        }
        return ResponseEntity.ok(ResponseDTO.of(new SkillDTO(skill), "Successfully updated"));
    }

}
