/**
 * 
 */
package com.metasoft.claim.service;

import java.io.Serializable;
import java.util.List;

import com.metasoft.claim.model.BaseModel;

/**
 * @author 
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
