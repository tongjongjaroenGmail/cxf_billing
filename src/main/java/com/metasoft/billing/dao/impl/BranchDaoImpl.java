/**
 * 
 */
package com.metasoft.billing.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.billing.dao.AbstractDaoImpl;
import com.metasoft.billing.dao.AmphurDao;
import com.metasoft.billing.dao.BranchDao;
import com.metasoft.billing.dao.ProvinceDao;
import com.metasoft.billing.model.Amphur;
import com.metasoft.billing.model.Branch;
import com.metasoft.billing.model.Province;

/**
 * @author
 * 
 */
@Repository("branchDao")
@Transactional
public class BranchDaoImpl extends AbstractDaoImpl<Branch, Integer> implements BranchDao {
	
	@Autowired 
	AmphurDao amphurDao;
	
	@Autowired 
	ProvinceDao provinceDao;
	
	public BranchDaoImpl() {
		super(Branch.class);
	}
	
	@Override
	public Long countByAmhurId(int amphurId) {
		Amphur amphur = amphurDao.findById(amphurId);

		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("amphur", amphur));	
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();	
	}
	
	@Override
	public Long countByProvinceId(int provinceId) {
		Province province = provinceDao.findById(provinceId);

		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.createAlias("amphur", "amphur");
		criteria.add(Restrictions.eq("amphur.province", province));	
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();	
	}
}
