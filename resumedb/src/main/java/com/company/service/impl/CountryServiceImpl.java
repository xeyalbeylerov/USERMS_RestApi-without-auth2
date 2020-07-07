package com.company.service.impl;

import com.company.entity.Country;
import com.company.repo.CountryRepository;
import com.company.service.inter.CountryServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Khayal Baylarov
 */
@Transactional
@Service(value = "countryService")
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryServiceInter {

    private final CountryRepository countryDao;

    @Override
    public List<Country> getAll() {
        return countryDao.findAll();
    }

    @Override
    public Country getById(int id) {
        return countryDao.getOne(id);
    }

    @Override
    public Country updateCountry(Country country) {
        return countryDao.save(country);
    }

    @Override
    public Country insertCountry(Country country) {
        return countryDao.save(country);
    }

    @Override
    public void removeCountry(int id) {
        countryDao.deleteById(id);
    }

    @Override
    public boolean isIdExists(Integer id) {
        return countryDao.existsCountryById(id);
    }

    @Override
    public boolean existsCountryByNameAndNationality(String countryName, String nationalityName) {
        return countryDao.existsCountryByNameAndNationality(countryName, nationalityName);
    }
}