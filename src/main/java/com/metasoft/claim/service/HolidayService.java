/**
 * 
 */
package com.metasoft.claim.service;

import java.util.Date;
import java.util.List;

import com.metasoft.claim.dao.HolidayDao;
import com.metasoft.claim.model.Holiday;

public interface HolidayService extends ModelBasedService<HolidayDao, Holiday, Integer>
{
	public Holiday findByDate(Date date);
	
	public List<Holiday> searchByBetweenDate(Date startDate,Date endDate);
}
