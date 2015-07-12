package com.metasoft.claim.dao;

import com.metasoft.claim.model.SecUser;

public interface SecUserDao extends AbstractDao<SecUser, Integer>{

	SecUser findByUserName(String username);

}