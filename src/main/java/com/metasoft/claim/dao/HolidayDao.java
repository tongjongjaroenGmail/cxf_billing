/**
 * 
 */
package com.metasoft.claim.dao;

import java.util.Date;
import java.util.List;

import com.metasoft.claim.model.Holiday;

public interface HolidayDao extends AbstractDao<Holiday, Integer>
{
	public Holiday findByDate(Date date);
	
	public List<Holiday> searchByBetweenDate(Date startDate,Date endDate);
}
