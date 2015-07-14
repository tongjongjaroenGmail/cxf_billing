/**
 * 
 */
package com.metasoft.claim.service;

import java.util.List;

import com.metasoft.claim.controller.vo.ClaimSearchCriteriaVo;
import com.metasoft.claim.dao.TblClaimRecoveryDao;
import com.metasoft.claim.model.TblClaimRecovery;


public interface TblClaimRecoveryService extends ModelBasedService<TblClaimRecoveryDao, TblClaimRecovery, Integer>
{
	public List<TblClaimRecovery> search(ClaimSearchCriteriaVo criteriaVo);
}
