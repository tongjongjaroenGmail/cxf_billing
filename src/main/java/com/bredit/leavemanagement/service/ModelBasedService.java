/**
 * 
 */
package com.bredit.leavemanagement.service;

import java.io.Serializable;
import java.util.List;

import com.bredit.leavemanagement.model.BaseModel;

/**
 * @author KaweepattC
 * 
 */
public interface ModelBasedService<T, E extends BaseModel, I extends Serializable> extends BaseService
{
    public E findById(I id);

    public List<E> findByIds(I[] ids);

    public List<E> findAll();

    public I save(E e);

    public I saveOrUpdate(E e);

    public void delete(E e);

    public boolean deleteById(I id);
}
