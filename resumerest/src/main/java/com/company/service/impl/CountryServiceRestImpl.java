package com.company.service.impl;


import com.company.entity.Country;
import com.company.exceptionHandler.exceptions.EntityAlreadyExistsException;
import com.company.exceptionHandler.exceptions.EntityNotFoundException;
import com.company.service.inter.CountryServiceInter;
import com.company.service.inter.CountryServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xeyal
 */

@Service(value = "countryServiceRest")
@RequiredArgsConstructor
public class CountryServiceRestImpl implements CountryServiceRestInter {

    private final CountryServiceInter countryDao;

    @Override
    public List<Country> getAll() {
        return countryDao.getAll();
    }


    @Override
    public Country getById(int id){
        if (!countryDao.isIdExists(id)) throw new EntityNotFoundException("Country not found");
        return countryDao.getById(id);
    }

    @Override
    public Country updateCountry(Country country){
        //check skill exists
        boolean isExists = countryDao.isIdExists(country.getId());
        if (!isExists) throw new EntityNotFoundException("Country not found");

        //check dublicate skill
        boolean isAlreadyExists = countryDao.existsCountryByNameAndNationality(country.getName(),country.getNationality());
        if (isAlreadyExists) throw new EntityAlreadyExistsException("Country already exists");

        return countryDao.updateCountry(country);
    }

    @Override
    public Country insertCountry(Country country){
        //check skill exists
        boolean isExists = countryDao.existsCountryByNameAndNationality(country.getName(),country.getNationality());
        if (isExists) throw new EntityAlreadyExistsException("Country already exists");

        return countryDao.insertCountry(country);
    }

    @Override
    public void removeCountry(int id){
        if (!countryDao.isIdExists(id)) throw new EntityNotFoundException("Country not found");
         countryDao.removeCountry(id);
    }

}