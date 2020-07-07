package com.company.dto;

import com.company.entity.Country;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountryDto {
    private Integer id;
    private String name;
    private String nationality;

    public CountryDto(Country country) {
        this.id = country.getId();
        this.name = country.getName();
        this.nationality = country.getNationality();
    }
}
