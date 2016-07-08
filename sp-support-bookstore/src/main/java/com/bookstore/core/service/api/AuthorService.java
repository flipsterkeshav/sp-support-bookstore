package com.bookstore.core.service.api;

import com.bookstore.db.model.entity.Author;

import java.util.List;

/**
 * Created by keshav.gupta on 07/07/16.
 */
public interface AuthorService {

    Author findById( Integer id  ) ;
    List<Author> findAll();
    Long save(Author author);
    Long update(Author entity);
    Long create(Author author);
    //void delete( Integer id );

}

