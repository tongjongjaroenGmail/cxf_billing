/**
 * 
 */
package com.metasoft.claim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.claim.dao.ConClaimTypeDao;
import com.metasoft.claim.model.ConClaimType;
import com.metasoft.claim.service.ConClaimTypeService;

@Service
public class ConClaimTypeServiceImpl extends ModelBasedServiceImpl<ConClaimTypeDao, ConClaimType, Integer> implements ConClaimTypeService
{
    private ConClaimTypeDao conClaimTypeDao;
    /**
     * 
     * @param entityClass
     */
    @Autowired
    public ConClaimTypeServiceImpl(ConClaimTypeDao dao)
    {
        super(dao);
        this.conClaimTypeDao = dao;
    }
}
