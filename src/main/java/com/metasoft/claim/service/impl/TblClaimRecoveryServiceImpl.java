/**
 * 
 */
package com.metasoft.claim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.claim.controller.vo.ClaimSearchCriteriaVo;
import com.metasoft.claim.dao.TblClaimRecoveryDao;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.TblClaimRecoveryService;

@Service
public class TblClaimRecoveryServiceImpl extends ModelBasedServiceImpl<TblClaimRecoveryDao, TblClaimRecovery, Integer> implements TblClaimRecoveryService
{
    private TblClaimRecoveryDao tblClaimRecoveryDao;
    /**
     * 
     * @param entityClass
     */
    @Autowired
    public TblClaimRecoveryServiceImpl(TblClaimRecoveryDao dao)
    {
        super(dao);
        this.tblClaimRecoveryDao = dao;
    }
	@Override
	public List<TblClaimRecovery> search(ClaimSearchCriteriaVo criteriaVo) {
		// TODO Auto-generated method stub
		return null;
	}
}
