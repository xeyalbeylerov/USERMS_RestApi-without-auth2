package com.company.controller;

import com.company.dto.CountryDTO;
import com.company.dto.ResponseDTO;
import com.company.dto.SkillDTO;
import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.exceptions.countryExceptions.CountryAlreadyExistsException;
import com.company.exceptions.countryExceptions.CountryNotFoundException;
import com.company.exceptions.skillExceptions.SkillAlreadyExistsException;
import com.company.exceptions.skillExceptions.SkillNotFoundException;
import com.company.service.inter.CountryServiceRestInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryRestController {
    @Autowired
    @Qualifier("countryServiceRest")
    private CountryServiceRestInter countryService;

    //return all users
    //countries?name=&surname=&age=
    @GetMapping("/countries")
    public ResponseEntity<ResponseDTO> getCountries() {
        List<Country> countries = countryService.getAll();

        List<CountryDTO> countryDTOS = new ArrayList<>();
        //skill-i skillDto ya cevirir
        for (int i = 0; i < countries.size(); i++) {
            Country s = countries.get(i);
            countryDTOS.add(new CountryDTO(s));
        }
        return ResponseEntity.ok(ResponseDTO.of(countryDTOS));
    }


    //return specify country by id
    @GetMapping("/countries/{id}")
    public ResponseEntity<ResponseDTO> getCountry(@PathVariable("id") int id) {
        Country country = null;
        try {
            country = countryService.getById(id);
        } catch (CountryNotFoundException ex) {
            return ResponseEntity.ok(ResponseDTO.of(404, "There is no country found with this id"));
        }
        return ResponseEntity.ok(ResponseDTO.of(new CountryDTO(country)));
    }

    //    delete specify skill by id and return deleted skill
    @DeleteMapping("/countries/{id}")
    public ResponseEntity<ResponseDTO> deleteCountry(@PathVariable("id") int id) {
        Country country;
        try {
            country = countryService.getById(id);
            countryService.removeCountry(id);
        } catch (CountryNotFoundException ex) {
            return ResponseEntity.ok(ResponseDTO.of(404, "There is no country found with this id"));
        }

        return ResponseEntity.ok(ResponseDTO.of(new CountryDTO(country), "Successfully deleted"));
    }

    //    //take json country data and add country then return it
    @PostMapping("/countries")
    public ResponseEntity<ResponseDTO> addCountry(@RequestBody CountryDTO countryDTO) {

        Country country = new Country(null,countryDTO.getName(),countryDTO.getNationality());
        try {
            country = countryService.insertCountry(country);
        } catch (CountryAlreadyExistsException ex) {
            return ResponseEntity.ok(ResponseDTO.of(500, "Country already exists"));
        }
        return ResponseEntity.ok(ResponseDTO.of(new CountryDTO(country), "Successfully added"));

    }

    //
    //take json country data and update country then return it
    @PutMapping("/countries")
    public ResponseEntity<ResponseDTO> updateCountry(@RequestBody CountryDTO countryDTO) {

        Country country = new Country(countryDTO.getId(), countryDTO.getName(),countryDTO.getNationality());
        try {
            country = countryService.updateCountry(country);
        } catch (CountryNotFoundException ex) {
            return ResponseEntity.ok(ResponseDTO.of(500, "Country does not exists"));
        } catch (CountryAlreadyExistsException ex) {
            return ResponseEntity.ok(ResponseDTO.of(500, "Country already exists"));
        }
        return ResponseEntity.ok(ResponseDTO.of(new CountryDTO(country), "Successfully updated"));
    }

    //take path and json country name and update country then return it
    @PutMapping("/countries/{countryid}")
    public ResponseEntity<ResponseDTO> updateCountry(@PathVariable("countryid") int countryId, @RequestBody CountryDTO countryDTO) {

        Country country = new Country(countryId, countryDTO.getName(),countryDTO.getNationality());
        try {
            country = countryService.updateCountry(country);
        } catch (CountryNotFoundException ex) {
            return ResponseEntity.ok(ResponseDTO.of(500, "Country does not exists"));
        } catch (CountryAlreadyExistsException ex) {
            return ResponseEntity.ok(ResponseDTO.of(500, "Country already exists"));
        }
        return ResponseEntity.ok(ResponseDTO.of(new CountryDTO(country), "Successfully updated"));
    }

}
