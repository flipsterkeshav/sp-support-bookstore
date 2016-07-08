package com.bookstore.core.service.api;

import com.bookstore.db.model.entity.Shop;

import java.util.List;

/**
 * Created by keshav.gupta on 07/07/16.
 */
public interface ShopService {

    /**
     * Loads an entity from the database using its Primary Key
     * @param code
     * @return entity
     */
    Shop findById( String code  ) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<Shop> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    Shop save(Shop entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    Shop update(Shop entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    Shop create(Shop entity);

    /**
     * Deletes an entity using its Primary Key
     * @param code
     */
    void delete( String code );


}

