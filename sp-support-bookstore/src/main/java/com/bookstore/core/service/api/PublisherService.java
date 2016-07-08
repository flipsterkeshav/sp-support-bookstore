package com.bookstore.core.service.api;

import com.bookstore.db.model.entity.Publisher;

import java.util.List;

/**
 * Created by keshav.gupta on 07/07/16.
 */
public interface PublisherService {

    /**
     * Loads an entity from the database using its Primary Key
     * @param code
     * @return entity
     */
    Publisher findById( Integer code  ) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<Publisher> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    Publisher save(Publisher entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    Publisher update(Publisher entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    Publisher create(Publisher entity);

    /**
     * Deletes an entity using its Primary Key
     * @param code
     */
    void delete( Integer code );


}
