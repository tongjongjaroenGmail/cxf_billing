/**
 * 
 */
package com.metasoft.billing.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.billing.dao.AbstractDaoImpl;
import com.metasoft.billing.dao.AmphurDao;
import com.metasoft.billing.model.Amphur;

/**
 * @author
 * 
 */
@Repository("amphurDao")
@Transactional
public class AmphurDaoImpl extends AbstractDaoImpl<Amphur, Integer> implements AmphurDao {

	public AmphurDaoImpl() {
		super(Amphur.class);
	}
}
