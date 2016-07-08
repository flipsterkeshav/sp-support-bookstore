package com.bookstore.db.dao.jpa;

import com.bookstore.db.dao.api.BaseDao;
import com.bookstore.db.model.entity.BaseEntity;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by keshav.gupta on 08/07/16.
 */
public class BaseDaoJpaImpl<T extends BaseEntity, ID extends Serializable> implements BaseDao<T, ID> {


    private final Provider<EntityManager> emf;
    protected Class<T> entityClass;
    private final int batchSize = 50;

    @Inject
    public BaseDaoJpaImpl(Provider<EntityManager> entityManagerProvider) {
        this.emf = entityManagerProvider;
    }

    @Override
    @Transactional
    public ID save(T entity) {
        EntityManager em = getEntityManager();
        if (em.contains(entity) ) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }
        em.flush();
        return (ID) entity.getId();
    }

    @Override
    @Transactional
    public T findOne(final long id) {
        EntityManager em = getEntityManager();

        T entity = em.find(getEntityClass(), id);
        em.flush();
        return entity;
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.get();
    }

    @Override
    public Class<T> getEntityClass() {
        if (entityClass == null) {
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType paramType = (ParameterizedType) type;
                entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
            } else {
                throw new IllegalArgumentException("Could not guess entity class by reflection");
            }
        }
        return entityClass;
    }

    @Override
    @Transactional
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    @Transactional
    public List<T> findByQuery(final int firstResult, final int maxResults, final String queryStr, Object... params) {
        Query query = getEntityManager().createQuery(queryStr);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }

        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        final List<T> result = (List<T>) query.getResultList();
        return result;
    }

    @Override
    @Transactional
    public List<T> findByQueryAndNamedParams(final Integer firstResult, final Integer maxResults, final String queryStr, final Map<String, ?> params) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery(queryStr);

        for (final Map.Entry<String, ? extends Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        if(firstResult!=null) query.setFirstResult(firstResult);
        if(maxResults!=null) query.setMaxResults(maxResults);
        final List<T> result = (List<T>) query.getResultList();
        return result;
    }

    @Override
    @Transactional
    public List<T> findByExample(final T exampleInstance) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        final List<T> result = crit.list();
        return result;
    }

    protected List<T> findByCriteria(final Criterion... criterion) {
        return findByCriteria(0, 10, criterion);
    }

    @Override
    public List<T> findByCriteria(final int firstResult, final int maxResults, final Criterion... criterion) {
        return findByCriteria(firstResult, maxResults, null, criterion);
    }

    @Override
    public List<T> findByCriteria(final int firstResult, final int maxResults, final List<Order> orders, final Criterion... criterion) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        Optional.ofNullable(orders).ifPresent(l -> l.forEach(item -> {
            crit.addOrder(item);
        }));

        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }

        if (maxResults > 0) {
            crit.setMaxResults(maxResults);
        }

        final List<T> result = crit.list();
        return result;
    }

    @Override
    @Transactional
    public long countAll() {
        return countByCriteria();
    }

    @Override
    @Transactional
    public long countByExample(final T exampleInstance) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        crit.setProjection(Projections.rowCount());
        crit.add(Example.create(exampleInstance));
        return (Long) crit.list().get(0);
    }

    protected long countByCriteria(final Criterion... criterion) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        crit.setProjection(Projections.rowCount());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        return (Long)crit.list().get(0);
    }

    @Override
    @Transactional
    //This will not insert in batch for entity whose primary key is generated
    // using identity identifier generator
    public List<T> save(Iterable<T> entities) {
        List<T> result = new ArrayList<>();
        if(entities == null)
            return result;

        EntityManager em = getEntityManager();

        int i = 0;
        for(T entity : entities){
            if (em.contains(entity) ) {
                result.add(em.merge(entity));
            } else {
                em.persist(entity);
                result.add(entity);
            }

            if( ++i % batchSize == 0){
                em.flush();
            }
        }
        em.flush();
        return result;
    }

}
