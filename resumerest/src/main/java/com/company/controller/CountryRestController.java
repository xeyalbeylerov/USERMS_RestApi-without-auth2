package com.company.controller;

import com.company.dto.CountryDto;
import com.company.dto.ResponseDto;
import com.company.entity.Country;
import com.company.exceptions.countryExceptions.CountryAlreadyExistsException;
import com.company.exceptions.countryExceptions.CountryNotFoundException;
import com.company.service.inter.CountryServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryRestController {

    private final CountryServiceRestInter countryService;

    //return all users
    //countries?name=&surname=&age=
    @GetMapping("/countries")
    public ResponseEntity<ResponseDto> getCountries() {
        List<Country> countries = countryService.getAll();

        List<CountryDto> countryDtos = new ArrayList<>();
        //skill-i skillDto ya cevirir
        for (int i = 0; i < countries.size(); i++) {
            Country s = countries.get(i);
            countryDtos.add(new CountryDto(s));
        }
        return ResponseEntity.ok(ResponseDto.of(countryDtos));
    }


    //return specify country by id
    @GetMapping("/countries/{id}")
    public ResponseEntity<ResponseDto> getCountry(@PathVariable("id") int id) {
        Country country;
        try {
            country = countryService.getById(id);
        } catch (CountryNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is no country found with this id"));
        }
        return ResponseEntity.ok(ResponseDto.of(new CountryDto(country)));
    }

    //    delete specify skill by id and return deleted skill
    @DeleteMapping("/countries/{id}")
    public ResponseEntity<ResponseDto> deleteCountry(@PathVariable("id") int id) {
        Country country;
        try {
            country = countryService.getById(id);
            countryService.removeCountry(id);
        } catch (CountryNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "There is no country found with this id"));
        }

        return ResponseEntity.ok(ResponseDto.of(new CountryDto(country), "Successfully deleted"));
    }

    //    //take json country data and add country then return it
    @PostMapping("/countries")
    public ResponseEntity<ResponseDto> addCountry(@RequestBody CountryDto countryDTO) {

        Country country = new Country(null, countryDTO.getName(), countryDTO.getNationality());
        try {
            country = countryService.insertCountry(country);
        } catch (CountryAlreadyExistsException ex) {
            return ResponseEntity.ok(ResponseDto.of(400, "Country already exists"));
        }
        return ResponseEntity.ok(ResponseDto.of(new CountryDto(country), "Successfully added"));

    }

    //
    //take json country data and update country then return it
    @PutMapping("/countries")
    public ResponseEntity<ResponseDto> updateCountry(@RequestBody CountryDto countryDTO) {

        Country country = new Country(countryDTO.getId(), countryDTO.getName(), countryDTO.getNationality());
        try {
            country = countryService.updateCountry(country);
        } catch (CountryNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "Country does not exists"));
        } catch (CountryAlreadyExistsException ex) {
            return ResponseEntity.ok(ResponseDto.of(400, "Country already exists"));
        }
        return ResponseEntity.ok(ResponseDto.of(new CountryDto(country), "Successfully updated"));
    }

    //take path and json country name and update country then return it
    @PutMapping("/countries/{countryid}")
    public ResponseEntity<ResponseDto> updateCountry(@PathVariable("countryid") int countryId, @RequestBody CountryDto countryDTO) {

        Country country = new Country(countryId, countryDTO.getName(), countryDTO.getNationality());
        try {
            country = countryService.updateCountry(country);
        } catch (CountryNotFoundException ex) {
            return ResponseEntity.ok(ResponseDto.of(404, "Country does not exists"));
        } catch (CountryAlreadyExistsException ex) {
            return ResponseEntity.ok(ResponseDto.of(400, "Country already exists"));
        }
        return ResponseEntity.ok(ResponseDto.of(new CountryDto(country), "Successfully updated"));
    }

}
