/**
 * 
 */
package com.metasoft.claim.service.impl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.claim.dao.security.UserDao;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.service.impl.ModelBasedServiceImpl;
import com.metasoft.claim.service.security.UserService;

@Service("userService")
public class UserServiceImpl extends ModelBasedServiceImpl<UserDao, SecUser, Integer> implements UserService
{
    private UserDao userDao;
    /**
     * 
     * @param entityClass
     */
    @Autowired
    public UserServiceImpl(UserDao dao)
    {
        super(dao);
        this.userDao = dao;
    }

	@Override
	public SecUser findByUserName(String userName) {		
		return userDao.findByUserName(userName);
	}
}
