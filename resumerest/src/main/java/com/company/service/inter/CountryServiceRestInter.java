/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.Country;
import com.company.exceptionHandler.exceptions.EntityAlreadyExistsException;
import com.company.exceptionHandler.exceptions.EntityNotFoundException;

import java.util.List;

/**
 *
 * @author xeyal
 */
public interface CountryServiceRestInter {

    List<Country> getAll();
    Country getById(int id);
    Country updateCountry(Country u);
    Country insertCountry(Country u);
    void removeCountry(int id);
    
}
