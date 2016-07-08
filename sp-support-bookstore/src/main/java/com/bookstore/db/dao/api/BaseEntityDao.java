package com.bookstore.db.dao.api;

import com.bookstore.db.model.entity.BaseEntity;
import org.hibernate.exception.ConstraintViolationException;

import java.io.Serializable;

/**
 * Created by keshav.gupta on 08/07/16.
 */
public interface BaseEntityDao<T extends BaseEntity, ID extends Serializable> {

    ID saveEntity(T entity) throws ConstraintViolationException,Exception;

    T fetchEntity(ID entityId);

}