package com.metasoft.claim.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.TblClaimRecoveryDao;
import com.metasoft.claim.model.TblClaimRecovery;

@Repository("tblClaimRecoveryDao")
@Transactional
public class TblClaimRecoveryDaoImpl extends AbstractDaoImpl<TblClaimRecovery, Integer> implements TblClaimRecoveryDao {
	public TblClaimRecoveryDaoImpl() {
		super(TblClaimRecovery.class);
	}
}
