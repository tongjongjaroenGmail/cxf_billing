/**
 * 
 */
package com.metasoft.billing.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.billing.dao.AbstractDaoImpl;
import com.metasoft.billing.dao.BranchDao;
import com.metasoft.billing.model.Branch;

/**
 * @author
 * 
 */
@Repository("branchDao")
@Transactional
public class BranchDaoImpl extends AbstractDaoImpl<Branch, Integer> implements BranchDao {

	public BranchDaoImpl() {
		super(Branch.class);
	}
}
