package com.bookstore.core.service.api;

import com.bookstore.db.model.entity.Book;

import java.util.List;

/**
 * Created by keshav.gupta on 07/07/16.
 */
public interface BookService {

    Book findById( Integer id  ) ;
    List<Book> findAll();
    Book save(Book entity);
    Book update(Book entity);
    Book create(Book entity);
    void delete( Integer id );
}
