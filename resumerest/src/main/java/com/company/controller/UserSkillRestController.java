package com.company.controller;

import com.company.dto.ResponseDto;
import com.company.dto.SkillDto;
import com.company.dto.UserSkillDto;
import com.company.entity.Skill;
import com.company.entity.UserSkill;
import com.company.exceptions.IdIsNullException;
import com.company.exceptions.skillExceptions.SkillNotFoundException;
import com.company.exceptions.userExceptions.UserNotFoundException;
import com.company.exceptions.userSkillExceptions.UserSkillAlreadyExists;
import com.company.exceptions.userSkillExceptions.UserSkillNotFoundException;
import com.company.service.inter.UserSkillServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserSkillRestController {


    private final UserSkillServiceRestInter userSkillService;

    //convert UserSkill List to UserSkillDto List
    private List<UserSkillDto> userSkillsToUserSkillDtos(List<UserSkill> skills) {
        List<UserSkillDto> userSkills = new ArrayList<>();
        for (int i = 0; i < skills.size(); i++) {
            UserSkill s = skills.get(i);
            userSkills.add(new UserSkillDto(s));
        }
        return userSkills;
    }

//    @GetMapping("/users/{id}/skills")
//    public ResponseEntity<ResponseDTO> getUserSkills(
//            @PathVariable(name="id")Integer id,
//
//    {
//
//    }

    @GetMapping("/userskills/{id}")
    public ResponseEntity<ResponseDto> getUserSkills(@PathVariable("id") int id) {
        List<UserSkill> userSkills = userSkillService.getAllSkillByUserId(id);

        //converting UserSkill List to UserSkillDto List
        List<UserSkillDto> userSkillsDTO = userSkillsToUserSkillDtos(userSkills);
        return ResponseEntity.ok(ResponseDto.of(userSkillsDTO));
    }

    @DeleteMapping("/userskills/{id}")
    public ResponseEntity<ResponseDto> deleteUserSkills(@PathVariable("id") int id) {
        List<UserSkill> userSkills = userSkillService.getAllSkillByUserId(id);
        userSkillService.removeUserSkill(id);
        //converting UserSkill List to UserSkillDto List
        List<UserSkillDto> userSkillsDTO = userSkillsToUserSkillDtos(userSkills);
        return ResponseEntity.ok(ResponseDto.of(userSkillsDTO, "All skills of user successfully deleted"));
    }

    //reuqired {"power": 9,"skill": {"id":6}}
    //Or reuqired {"power": 9,"skill": {"name": "SkillName"}}
    @PostMapping("/userskills/{id}")
    public ResponseEntity<ResponseDto> addUserSkill(@PathVariable("id") int id, @RequestBody UserSkillDto userSkillDTO) {

        UserSkill userSkill = new UserSkill();
//        power is setting
        userSkill.setPower(userSkillDTO.getPower());


        //skillDto to Skill(only id)
        SkillDto skillDTO = userSkillDTO.getSkill();
        Skill s = new Skill();
        s.setId(skillDTO.getId());
        userSkill.setSkill(s);

        try {
            userSkillService.insertUserSkill(id, userSkill);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is no user found with this id"));
        } catch (UserSkillAlreadyExists ex) {
            return ResponseEntity.ok(ResponseDto.of(400, "User already have this skill"));
        } catch (SkillNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is ot any skill for this id"));
        }
        return ResponseEntity.ok(ResponseDto.of(userSkillDTO, "Successfully added"));
    }

//this this not workin, because UserSkill entity doesnt containing cascade persist.
//If you add cascade persist then addUserSkillById method does not work.
//    @PostMapping("/userskills/{id}/skillname/{skillname}/power/{power}")
//    public ResponseEntity<ResponseDTO> addUserSkillByName(@PathVariable("id") int id,
//                                                          @PathVariable("power") Integer skillPower,
//                                                          @PathVariable("skillname") String skillName) {
//
//        UserSkill userSkill = new UserSkill();
//        userSkill.setPower(skillPower);//power set olunur
//        Skill s = new Skill();
//        s.setName(skillName);
//        userSkill.setSkill(s);
//
//        try {
//            userSkillService.insertUserSkill(id, userSkill);
//        } catch (UserNotFoundException ex) {
//            return ResponseEntity.ok(ResponseDTO.of(404, "There is no user found with this id"));
//        } catch (UserSkillAlreadyExists ex) {
//            return ResponseEntity.ok(ResponseDTO.of(404, "User already have this skill"));
//        }catch (SkillNotFoundException ex) {
//            return ResponseEntity.ok(ResponseDTO.of(404, "There is ot any skill for this id"));
//        }
//
//        return ResponseEntity.ok(ResponseDTO.of(new UserSkillDTO(userSkill), "Successfully added"));
//    }


    @PostMapping("/userskills/{id}/skill/{skillid}/power/{power}")
    public ResponseEntity<ResponseDto> addUserSkillById(@PathVariable("id") int id,
                                                        @PathVariable("power") Integer skillPower,
                                                        @PathVariable("skillid") Integer skillId
    ) {

        UserSkill userSkill = new UserSkill();
        userSkill.setPower(skillPower);//power set olunur
        Skill s = new Skill();
        s.setId(skillId);
        userSkill.setSkill(s);
        UserSkill userSkillReturned;
        try {
            userSkillReturned = userSkillService.insertUserSkill(id, userSkill);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is no user found with this id"));
        } catch (UserSkillAlreadyExists ex) {
            return ResponseEntity.ok(ResponseDto.of(400, "User already have this skill"));
        } catch (SkillNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is not any skill for this id"));
        }

        return ResponseEntity.ok(ResponseDto.of(new UserSkillDto(userSkillReturned), "Successfully added"));
    }

    //reuqired {"id": 19,"power":2,"skill":{"id":2 }}
    @PutMapping("/userskills/{id}")
    public ResponseEntity<ResponseDto> updateUserSkill(@PathVariable("id") int id, @RequestBody UserSkillDto userSkillDTO) {

        UserSkill userSkill = new UserSkill();
        userSkill.setId(userSkillDTO.getId());
        userSkill.setPower(userSkillDTO.getPower());

        //skillDto to Skill
        SkillDto skillDTO = userSkillDTO.getSkill();
        Skill s = new Skill();
        s.setId(skillDTO.getId());
        s.setName(skillDTO.getName());
        userSkill.setSkill(s);

        try {
            userSkillService.updateUserSkill(id, userSkill);
        } catch (IdIsNullException e) {
            return ResponseEntity.ok(ResponseDto.of(500, "User skills id must be filled while update"));
        } catch (UserSkillNotFoundException e) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is no user skills found with this id"));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is no user found with this id"));
        }
        return ResponseEntity.ok(ResponseDto.of(userSkillDTO, "User skill Successfully updated"));
    }
}
