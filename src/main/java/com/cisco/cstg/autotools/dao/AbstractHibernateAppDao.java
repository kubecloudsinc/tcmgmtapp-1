
package com.cisco.cstg.autotools.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class AbstractHibernateAppDao<E>{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @SuppressWarnings("unchecked")
    private final Class<E> domainClass = (Class<E>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];

    @Qualifier(value="factdbSessionFactory")
    private SessionFactory factdbSessionFactory;

    @Autowired
    public void setFactdbSessionFactory(SessionFactory factdbSessionFactory) {
        this.factdbSessionFactory = factdbSessionFactory;
    }
    
    protected Session getFactdbSession() throws HibernateException {
        return this.factdbSessionFactory.getCurrentSession();
    }

    @Transactional(readOnly = false, value="factdbTxManager")
    public void saveInFactDB(E entity) throws DataAccessException {
        this.logger.trace("Saving {}", entity);
        Session session = this.getFactdbSession();
        session.saveOrUpdate(entity);
        session.flush(); // force insert/update
        this.logger.debug("Saved {}", entity);
    }

    @Transactional(readOnly = false, value="factdbTxManager")
    public void deleteInFactdb(E entity) throws DataAccessException {
        Session session = this.getFactdbSession();
        session.delete(entity);
        session.flush(); // force delete
        this.logger.debug("Deleted {}", entity);
    }

    @Transactional(readOnly = true, value="factdbTxManager")
    @SuppressWarnings("unchecked")
    public E getByIdInFactdb(Long id) throws DataAccessException {
        Object result = this.getFactdbSession().get(this.domainClass, id);
        this.logger.debug("Got {} by id {}", result, id);
        return (E) result;
    }

    @Transactional(readOnly = true, value="factdbTxManager")
    @SuppressWarnings("unchecked")
    public List<E> getAllInFactdb() {
        // this default implementation does not support any special sorting
        return this.getFactdbSession().createCriteria(this.domainClass).list();
    }

    @SuppressWarnings("unchecked")
    protected List<E> findAllInFactdb(String hqlQuery, Object... params) throws DataAccessException {
        Query query = this.getFactdbSession().createQuery(hqlQuery);
        this.initQueryParams(query, params);
        List<?> result = query.list();
        this.logger.debug("Found {} entities by query {}", +result.size(), hqlQuery);
        return (List<E>) result;
    }

    @SuppressWarnings("unchecked")
    protected E findOneInFactdb(String hqlQuery, Object... params) throws DataAccessException {
        Query query = this.getFactdbSession().createQuery(hqlQuery);
        this.initQueryParams(query, params);
        Object result = query.uniqueResult();
        this.logger.debug("Found {} by query {}", result, hqlQuery);
        return (E) result;
    }
   
    private void initQueryParams(Query query, Object... params) {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
    }
    
    public void clearSessions(){
	    if (getFactdbSession() != null) {
	        getFactdbSession().clear(); // internal cache clear
	    }
	    Cache cache = factdbSessionFactory.getCache();
	    if (cache != null) {
	        cache.evictAllRegions(); // Evict data from all query regions.
	    }
    }
}