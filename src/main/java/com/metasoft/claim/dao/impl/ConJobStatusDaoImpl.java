package com.metasoft.claim.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.ConJobStatusDao;
import com.metasoft.claim.model.ConJobStatus;

@Repository("conJobStatusDao")
@Transactional
public class ConJobStatusDaoImpl extends AbstractDaoImpl<ConJobStatus, Integer> implements ConJobStatusDao {
	public ConJobStatusDaoImpl() {
		super(ConJobStatus.class);
	}
}
