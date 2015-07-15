package com.metasoft.claim.dao.security;

import com.metasoft.claim.dao.AbstractDao;
import com.metasoft.claim.model.SecUser;

public interface UserDao extends AbstractDao<SecUser, Integer>{

	SecUser findByUserName(String userName);

}