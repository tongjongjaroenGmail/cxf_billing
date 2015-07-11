/**
 * 
 */
package com.bredit.leavemanagement.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.LobHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

import com.bredit.leavemanagement.model.BaseModel;

/**
 * @author KaweepattC
 * 
 */
@Transactional
public abstract class AbstractDaoImpl<E extends BaseModel, I extends Serializable> implements AbstractDao<E, I>
{

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    protected Class<E> entityClass;

    /**
     * 
     * @param entityClass
     */
    protected AbstractDaoImpl(Class<E> entityClass)
    {
        this.entityClass = entityClass;
    }

    /**
     * @param sessionFactory
     *            the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 
     * @return
     */
    public Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public I save(E e)
    {
        return (I) getCurrentSession().save(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vialink.echantiers.dao.AbstractDao#saveOrUpdate(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public I saveOrUpdate(E e)
    {
        if (e.getId() == null)
        {
            return (I) getCurrentSession().save(e);
        }
        else
        {
            getCurrentSession().saveOrUpdate(e);
            return (I) e.getId();
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vialink.echantiers.dao.AbstractDao#delete(java.lang.Object)
     */
    @Override
    public void delete(E e)
    {
        getCurrentSession().delete(e);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean deleteById(I id)
    {
        E persistentInstance = (E) getCurrentSession().load(entityClass, id);
        if (persistentInstance != null)
        {
            delete(persistentInstance);
            return true;
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vialink.echantiers.dao.AbstractDao#findById(java.io.Serializable)
     */
    @Override
    @SuppressWarnings("unchecked")
    public E findById(I id)
    {
        return (E) getCurrentSession().get(entityClass, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vialink.echantiers.dao.AbstractDao#findByIds(java.io.Serializable[])
     */
    @Override
    public List<E> findByIds(I[] ids)
    {
        List<E> result = new ArrayList<E>();
        for (I id : ids)
        {
            result.add(findById(id));
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vialink.echantiers.dao.AbstractDao#findAll()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<E> findAll()
    {
        return getCurrentSession().createCriteria(entityClass).list();
    }

    /**
     * 
     * @param criterion
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<E> findByCriteria(Criterion criterion)
    {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }

    /**
     * 
     * @param query
     * @param parameters
     * @return
     */
    @SuppressWarnings("rawtypes")
    protected Query setQueryParametersByMap(Query query, LinkedHashMap<String, Object> parameters)
    {
        String[] keys = parameters.keySet().toArray(new String[parameters.keySet().size()]);
        Object[] values = parameters.values().toArray();

        for (int i = 0; i < keys.length; i++)
        {
            Object value = values[i];
            if (value instanceof Collection)
            {
                query.setParameterList(keys[i], (Collection) value);
            }
            else if (value instanceof Integer[])
            {
                query.setParameterList(keys[i], (Integer[]) value);
            }
            else
            {
                query.setParameter(keys[i], value);
            }

        }

        return query;
    }

    /**
     * 
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    protected Blob parseFileToBlob(File file) throws FileNotFoundException
    {
        LobHelper lobHelper = getCurrentSession().getLobHelper();
        return lobHelper.createBlob(new FileInputStream(file), file.length());
    }

    /**
     * 
     * @param bytes
     * @return
     * @throws FileNotFoundException
     */
    protected Blob parseByteArrayToBlob(byte[] bytes)
    {
        LobHelper lobHelper = getCurrentSession().getLobHelper();
        return lobHelper.createBlob(bytes);
    }
}
