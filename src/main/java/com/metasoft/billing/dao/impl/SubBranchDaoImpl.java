/**
 * 
 */
package com.metasoft.billing.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.billing.dao.AbstractDaoImpl;
import com.metasoft.billing.dao.AmphurDao;
import com.metasoft.billing.dao.BranchDao;
import com.metasoft.billing.dao.SubBranchDao;
import com.metasoft.billing.model.Amphur;
import com.metasoft.billing.model.Branch;
import com.metasoft.billing.model.SubBranch;

/**
 * @author
 * 
 */
@Repository("subBranchDao")
@Transactional
public class SubBranchDaoImpl extends AbstractDaoImpl<SubBranch, Integer> implements SubBranchDao {
	
	@Autowired 
	AmphurDao amphurDao;
	
	@Autowired 
	BranchDao branchDao;
	
	public SubBranchDaoImpl() {
		super(SubBranch.class);
	}
	
	@Override
	public SubBranch findByBranchIdAndAmphurId(int branchId, int amphurId) {
		Branch branch = branchDao.findById(branchId);
		Amphur amphur = amphurDao.findById(amphurId);
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("branch", branch));
		criteria.add(Restrictions.eq("amphur", amphur));	
		return (SubBranch) criteria.uniqueResult();	
	}
}
