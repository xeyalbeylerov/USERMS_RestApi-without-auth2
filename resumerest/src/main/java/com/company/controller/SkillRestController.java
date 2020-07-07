package com.company.controller;

import com.company.dto.ResponseDto;
import com.company.dto.SkillDto;
import com.company.entity.Skill;
import com.company.exceptions.skillExceptions.SkillAlreadyExistsException;
import com.company.exceptions.skillExceptions.SkillNotFoundException;
import com.company.service.inter.SkillServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SkillRestController {

    private final SkillServiceRestInter skillService;

    //return all users
    //users?name=&surname=&age=
    @GetMapping("/skills")
    public ResponseEntity<ResponseDto> getSkills() {
        List<Skill> skills = skillService.getAll();

        List<SkillDto> skillDtos = new ArrayList<>();
        //skill-i skillDto ya cevirir
        for (int i = 0; i < skills.size(); i++) {
            Skill s = skills.get(i);
            skillDtos.add(new SkillDto(s));
        }
        return ResponseEntity.ok(ResponseDto.of(skillDtos));
    }

    //return specify skill by id
    @GetMapping("/skills/{id}")
    public ResponseEntity<ResponseDto> getSkill(@PathVariable("id") int id) {
        Skill skill;
        try {
            skill = skillService.getById(id);
        } catch (SkillNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is no skill found with this id"));
        }
        return ResponseEntity.ok(ResponseDto.of(new SkillDto(skill)));
    }

    //    delete specify skill by id and return deleted skill
    @DeleteMapping("/skills/{id}")
    public ResponseEntity<ResponseDto> deleteSkill(@PathVariable("id") int id) {
        Skill skill;
        try {
            skill = skillService.getById(id);
            skillService.removeSkill(id);
        } catch (SkillNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is no skill found with this id"));
        }

        return ResponseEntity.ok(ResponseDto.of(new SkillDto(skill), "Successfully deleted"));
    }

    //    take json skill data and add skill then return it
    @PostMapping("/skills")
    public ResponseEntity<ResponseDto> addSkill(@RequestBody SkillDto skillDto) {

        Skill skill = new Skill(null, skillDto.getName());
        try {
            skill = skillService.insertSkill(skill);
        } catch (SkillAlreadyExistsException ex) {
            return ResponseEntity.ok(ResponseDto.of(400, "Skill already exists"));
        }
        return ResponseEntity.ok(ResponseDto.of(new SkillDto(skill), "Successfully added"));

    }

    //
    //take json skill data and update skill then return it
    @PutMapping("/skills")
    public ResponseEntity<ResponseDto> updateSkill(@RequestBody SkillDto skillDto) {
        Skill skill = new Skill(skillDto.getId(), skillDto.getName());
//        catch errors and return ResponseEntity
        return updateSkillLogic(skill);
    }

    //take path and json skill name and update skill then return it
    @PutMapping("/skills/{skillid}")
    public ResponseEntity<ResponseDto> updateSkill(@PathVariable("skillid") int skillId, @RequestBody SkillDto skillDto) {
        Skill skill = new Skill(skillId, skillDto.getName());
        //        catch errors and return ResponseEntity
        return updateSkillLogic(skill);
    }

    //    For updateSkill methods
    private ResponseEntity updateSkillLogic(Skill skill) {
        try {
            skill = skillService.updateSkill(skill);
        } catch (SkillNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "Skill does not exists"));
        } catch (SkillAlreadyExistsException ex) {
            return ResponseEntity.ok(ResponseDto.of(400, "Skill already exists"));
        }
        return ResponseEntity.ok(ResponseDto.of(new SkillDto(skill), "Successfully updated"));
    }

}
