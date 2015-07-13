/**
 * 
 */
package com.metasoft.claim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.claim.dao.SecUserDao;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.service.SecUserService;

@Service
public class SecUserServiceImpl extends ModelBasedServiceImpl<SecUserDao, SecUser, Integer> implements SecUserService
{
    private SecUserDao secUserDao;
    /**
     * 
     * @param entityClass
     */
    @Autowired
    public SecUserServiceImpl(SecUserDao dao)
    {
        super(dao);
        this.secUserDao = dao;
    }

	@Override
	public SecUser findByUserName(String userName) {		
		return secUserDao.findByUserName(userName);
	}
}
