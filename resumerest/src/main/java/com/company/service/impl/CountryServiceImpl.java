package com.company.service.impl;//package com.company.service.impl;
//
//import com.company.entity.Country;
//import com.company.repo.CountryRepository;
//import com.company.service.inter.CountryServiceInter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * @author Xeyal
// */
//
//@Service(value = "countryServiceMvc")
//public class CountryServiceImpl implements CountryServiceInter {
//
//    @Autowired
//    @Qualifier("countryService")
//    private CountryServiceInter countryDao;
//
//    @Override
//    public List<Country> getAll() {
//        return countryDao.getAll();
//    }
//
//
//    @Override
//    public Country getById(int id) {
//        return countryDao.getById(id);
//    }
//
//    @Override
//    public Country updateCountry(Country country) {
//        return countryDao.updateCountry(country);
//    }
//
//    @Override
//    public Country insertCountry(Country country) {
//        return countryDao.insertCountry(country);
//    }
//
//    @Override
//    public void removeCountry(int id) {
//         countryDao.removeCountry(id);
//    }
//
//}