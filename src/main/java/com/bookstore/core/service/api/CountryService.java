package com.bookstore.core.service.api;

import com.bookstore.db.model.entity.Country;

import java.util.List;

/**
 * Created by keshav.gupta on 07/07/16.
 */
public interface CountryService {

    Country findById( String code  ) ;
    List<Country> findAll();
    Country save(Country entity);
    Country update(Country entity);
    Country create(Country entity);
    void delete( String code );


}
