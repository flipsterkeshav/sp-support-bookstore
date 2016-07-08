package com.bookstore.core.service.api;

import com.bookstore.db.model.entity.Customer;

import java.util.List;

/**
 * Created by keshav.gupta on 07/07/16.
 */
public interface CustomerService {

    Customer findById( String code  ) ;
    List<Customer> findAll();
    Customer save(Customer entity);
    Customer update(Customer entity);
    Customer create(Customer entity);
    void delete( String code );

}
