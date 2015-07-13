package com.metasoft.claim.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.ConClaimTypeDao;
import com.metasoft.claim.model.ConClaimType;

@Repository("conClaimTypeDao")
@Transactional
public class ConClaimTypeDaoImpl extends AbstractDaoImpl<ConClaimType, Integer> implements ConClaimTypeDao {
	public ConClaimTypeDaoImpl() {
		super(ConClaimType.class);
	}
}
