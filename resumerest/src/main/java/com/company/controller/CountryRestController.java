package com.company.controller;

import com.company.dto.CountryDto;
import com.company.dto.ResponseDto;
import com.company.entity.Country;
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

    //convert Country List to CountryDto List
    private List<CountryDto> countryToCountryDtos(List<Country> countries) {
        List<CountryDto> countryDtos = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
            Country u = countries.get(i);
            countryDtos.add(new CountryDto(u));
        }
        return countryDtos;
    }

    //return all users
    //countries?name=&surname=&age=
    @GetMapping("/countries")
    public ResponseEntity<ResponseDto> getCountries() {
        List<Country> countries = countryService.getAll();
        //converting Country List to CountryDto List
        List<CountryDto> countryDtos = countryToCountryDtos(countries);
        return ResponseEntity.ok(ResponseDto.of(countryDtos));
    }


    //return specify country by id
    @GetMapping("/countries/{id}")
    public ResponseEntity<ResponseDto> getCountry(@PathVariable("id") int id) {
        Country country;
        country = countryService.getById(id);
        return ResponseEntity.ok(ResponseDto.of(new CountryDto(country)));
    }

    //    delete specify skill by id and return deleted skill
    @DeleteMapping("/countries/{id}")
    public ResponseEntity<ResponseDto> deleteCountry(@PathVariable("id") int id) {
        Country country;
            country = countryService.getById(id);
            countryService.removeCountry(id);
        return ResponseEntity.ok(ResponseDto.of(new CountryDto(country), "Successfully deleted"));
    }

    //    //take json country data and add country then return it
    @PostMapping("/countries")
    public ResponseEntity<ResponseDto> addCountry(@RequestBody CountryDto countryDTO) {

        Country country = new Country(null, countryDTO.getName(), countryDTO.getNationality());
            country = countryService.insertCountry(country);
        return ResponseEntity.ok(ResponseDto.of(new CountryDto(country), "Successfully added"));

    }

    //
    //take json country data and update country then return it
    @PutMapping("/countries")
    public ResponseEntity<ResponseDto> updateCountry(@RequestBody CountryDto countryDTO) {

        Country country = new Country(countryDTO.getId(), countryDTO.getName(), countryDTO.getNationality());
        country = countryService.updateCountry(country);
        return ResponseEntity.ok(ResponseDto.of(new CountryDto(country), "Successfully updated"));
    }

    //take path and json country name and update country then return it
    @PutMapping("/countries/{countryid}")
    public ResponseEntity<ResponseDto> updateCountry(@PathVariable("countryid") int countryId, @RequestBody CountryDto countryDTO) {

        Country country = new Country(countryId, countryDTO.getName(), countryDTO.getNationality());
        country = countryService.updateCountry(country);
        return ResponseEntity.ok(ResponseDto.of(new CountryDto(country), "Successfully updated"));
    }

}
