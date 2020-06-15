/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.Country;
import com.company.exceptions.countryExceptions.CountryAlreadyExistsException;
import com.company.exceptions.countryExceptions.CountryNotFoundException;

import java.util.List;

/**
 *
 * @author xeyal
 */
public interface CountryServiceRestInter {

    List<Country> getAll();
    Country getById(int id) throws CountryNotFoundException;
    Country updateCountry(Country u) throws CountryNotFoundException, CountryAlreadyExistsException;
    Country insertCountry(Country u) throws CountryAlreadyExistsException;
    void removeCountry(int id) throws CountryNotFoundException;
    
}
