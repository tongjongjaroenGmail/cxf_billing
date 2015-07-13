package com.metasoft.claim.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.StdReceiveTypeDao;
import com.metasoft.claim.model.StdReceiveType;

@Repository("stdReceiveTypeDao")
@Transactional
public class StdReceiveTypeDaoImpl extends AbstractDaoImpl<StdReceiveType, Integer> implements StdReceiveTypeDao {
	public StdReceiveTypeDaoImpl() {
		super(StdReceiveType.class);
	}
}
