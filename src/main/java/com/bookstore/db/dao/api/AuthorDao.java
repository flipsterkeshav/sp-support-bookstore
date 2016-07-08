package com.bookstore.db.dao.api;

import com.bookstore.db.dao.jpa.AuthorDaoImpl;
import com.bookstore.db.model.entity.Author;
import com.google.inject.ImplementedBy;

/**
 * Created by keshav.gupta on 08/07/16.
 */
@ImplementedBy(AuthorDaoImpl.class)
public interface AuthorDao extends BaseDao<Author, Long>, BaseEntityDao<Author, Long> {
}