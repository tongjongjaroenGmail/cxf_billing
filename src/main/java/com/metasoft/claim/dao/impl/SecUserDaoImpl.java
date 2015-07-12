/**
 * 
 */
package com.metasoft.claim.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.SecUserDao;
import com.metasoft.claim.model.SecUser;

/**
 * @author
 * 
 */
@Repository("secUserDao")
@Transactional
public class SecUserDaoImpl extends AbstractDaoImpl<SecUser, Integer> implements SecUserDao {

	public SecUserDaoImpl() {
		super(SecUser.class);
	}

	@Override
	public SecUser findByUserName(String userName) {
		return (SecUser) getCurrentSession().createCriteria(entityClass).add(Restrictions.eq("userName", userName)).uniqueResult();
	}

}
