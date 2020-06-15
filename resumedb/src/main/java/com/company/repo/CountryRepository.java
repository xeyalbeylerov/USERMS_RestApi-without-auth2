package com.company.repo;

import com.company.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer> {
    boolean existsCountryById(Integer id);
    boolean existsCountryByNameAndNationality(String countryName,String nationalityName);
}
