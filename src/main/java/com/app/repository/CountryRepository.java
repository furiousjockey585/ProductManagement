package com.app.repository;

import com.app.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    void addCountry(Country country);
    void updateCountry(Country country);
    void deleteCountry(Integer id);
    Optional<Country> findOneById(Integer id);
    Optional<Country> findOneByName(String name);
    List<Country> findAll();

}
