package com.bookstore.core.service.api;

import com.bookstore.db.model.entity.Order;

import java.util.List;

/**
 * Created by keshav.gupta on 07/07/16.
 */
public interface OrderService {

    /**
     * Loads an entity from the database using its Primary Key
     * @param id
     * @return entity
     */
    Order findById( Integer id  ) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<Order> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    Order save(Order entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    Order update(Order entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    Order create(Order entity);

    /**
     * Deletes an entity using its Primary Key
     * @param id
     */
    void delete( Integer id );


}
