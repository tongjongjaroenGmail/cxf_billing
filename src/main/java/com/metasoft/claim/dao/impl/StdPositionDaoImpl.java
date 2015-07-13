package com.metasoft.claim.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.StdPositionDao;
import com.metasoft.claim.model.StdPosition;

@Repository("stdPositionDao")
@Transactional
public class StdPositionDaoImpl extends AbstractDaoImpl<StdPosition, Integer> implements StdPositionDao {
	public StdPositionDaoImpl() {
		super(StdPosition.class);
	}
}
