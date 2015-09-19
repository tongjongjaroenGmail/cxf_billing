/**
 * 
 */
package com.metasoft.billing.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author 
 * 
 */
public interface AbstractDao<E, I extends Serializable>
{
    /**
     * 
     * @param id
     * @return
     */
    E findById(I id);

    /**
     * 
     * @param ids
     * @return
     */
    List<E> findByIds(I[] ids);

    /**
     * 
     * @return
     */
    List<E> findAll();

    /**
     * Insert new record into the database
     * @param e
     * @return
     */
    I save(E e);
    
    /**
     * Insert or update record into the database, it will check the ID before process
     * @param e
     * @return
     */
    I saveOrUpdate(E e);

    /**
     * 
     * @param e
     */
    void delete(E e);

    /**
     * 
     * @param id
     * @return
     */
    boolean deleteById(I id);
    
}
