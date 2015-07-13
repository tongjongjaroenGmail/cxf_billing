/**
 * 
 */
package com.metasoft.claim.service;

import com.metasoft.claim.dao.SecUserDao;
import com.metasoft.claim.model.SecUser;


public interface SecUserService extends ModelBasedService<SecUserDao, SecUser, Integer>
{
	SecUser findByUserName(String username);
}
