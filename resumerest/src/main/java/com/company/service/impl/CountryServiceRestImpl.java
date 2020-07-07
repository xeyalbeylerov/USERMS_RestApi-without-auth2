package com.company.service.impl;


import com.company.entity.Country;
import com.company.exceptions.countryExceptions.CountryAlreadyExistsException;
import com.company.exceptions.countryExceptions.CountryNotFoundException;
import com.company.service.inter.CountryServiceInter;
import com.company.service.inter.CountryServiceRestInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xeyal
 */

@Service(value = "countryServiceRest")
public class CountryServiceRestImpl implements CountryServiceRestInter {

    @Autowired
    @Qualifier("countryService")
    private CountryServiceInter countryDao;

    @Override
    public List<Country> getAll() {
        return countryDao.getAll();
    }


    @Override
    public Country getById(int id) throws CountryNotFoundException {
        if (!countryDao.isIdExists(id)) throw new CountryNotFoundException();


        return countryDao.getById(id);
    }

    @Override
    public Country updateCountry(Country country) throws CountryNotFoundException, CountryAlreadyExistsException {
        //check skill exists
        boolean isExists = countryDao.isIdExists(country.getId());
        if (!isExists) throw new CountryNotFoundException();

        //check dublicate skill
        boolean isAlreadyExists = countryDao.existsCountryByNameAndNationality(country.getName(),country.getNationality());
        if (isAlreadyExists) throw new CountryAlreadyExistsException();

        return countryDao.updateCountry(country);
    }

    @Override
    public Country insertCountry(Country country) throws CountryAlreadyExistsException {
        //check skill exists
        boolean isExists = countryDao.existsCountryByNameAndNationality(country.getName(),country.getNationality());
        if (isExists) throw new CountryAlreadyExistsException();

        return countryDao.insertCountry(country);
    }

    @Override
    public void removeCountry(int id) throws CountryNotFoundException {
        if (!countryDao.isIdExists(id)) throw new CountryNotFoundException();
         countryDao.removeCountry(id);
    }

}