/**
 * 
 */
package com.metasoft.billing.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.billing.dao.AbstractDaoImpl;
import com.metasoft.billing.dao.ProvinceDao;
import com.metasoft.billing.model.Province;

/**
 * @author
 * 
 */
@Repository("provinceDao")
@Transactional
public class ProvinceDaoImpl extends AbstractDaoImpl<Province, Integer> implements ProvinceDao {

	public ProvinceDaoImpl() {
		super(Province.class);
	}
}
