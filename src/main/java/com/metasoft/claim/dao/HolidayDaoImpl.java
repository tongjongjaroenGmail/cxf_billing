/**
 * 
 */
package com.metasoft.claim.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.claim.model.Holiday;

@Repository("HolidayDao")
@Transactional
public class HolidayDaoImpl extends AbstractDaoImpl<Holiday, Integer> implements HolidayDao
{
    public HolidayDaoImpl()
    {
        super(Holiday.class);
    }

	@Override
	public Holiday findByDate(Date date) {
		return (Holiday) getCurrentSession().get(entityClass, date);
	}
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Holiday> searchByBetweenDate(Date startDate,Date endDate) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.between("date", startDate, endDate));
        criteria.addOrder(Order.asc("date"));
        return criteria.list();   
	}
}
