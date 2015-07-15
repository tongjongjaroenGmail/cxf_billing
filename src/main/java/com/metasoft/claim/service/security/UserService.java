/**
 * 
 */
package com.metasoft.claim.service.security;

import com.metasoft.claim.dao.security.UserDao;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.service.ModelBasedService;


public interface UserService extends ModelBasedService<UserDao, SecUser, Integer>
{
	SecUser findByUserName(String username);
}
