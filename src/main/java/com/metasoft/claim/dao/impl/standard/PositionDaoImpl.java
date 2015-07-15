package com.metasoft.claim.dao.impl.standard;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.dao.AbstractDaoImpl;
import com.metasoft.claim.dao.standard.PositionDao;
import com.metasoft.claim.model.StdPosition;

@Repository("positionDao")
@Transactional
public class PositionDaoImpl extends AbstractDaoImpl<StdPosition, Integer> implements PositionDao {
	public PositionDaoImpl() {
		super(StdPosition.class);
	}
}
