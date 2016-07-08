package com.bookstore.db.dao.api;

import com.bookstore.db.model.entity.BaseEntity;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by keshav.gupta on 07/07/16.
 */
public interface BaseDao<T extends BaseEntity, ID extends Serializable> {

    ID save(T entity);

    T findOne(long id);

    Class<T> getEntityClass();

    EntityManager getEntityManager();

    List<T> findAll();

    List<T> findByQuery(int firstResult, int maxResults, String queryName, Object... params);

    List<T> findByQueryAndNamedParams(Integer firstResult, Integer maxResults, String queryName, Map<String, ? extends Object> params);

    List<T> findByExample(T exampleInstance);

    long countAll();

    long countByExample(T exampleInstance);

    List<T> findByCriteria(final int firstResult, final int maxResults, final Criterion... criterion);

    List<T> findByCriteria(final int firstResult, final int maxResults, final List<Order> orders, final Criterion... criterion);

    List<T> save(Iterable<T> itr);
}
