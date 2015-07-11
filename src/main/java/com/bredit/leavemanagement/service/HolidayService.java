/**
 * 
 */
package com.bredit.leavemanagement.service;

import java.util.Date;
import java.util.List;

import com.bredit.leavemanagement.dao.HolidayDao;
import com.bredit.leavemanagement.model.Holiday;

public interface HolidayService extends ModelBasedService<HolidayDao, Holiday, Integer>
{
	public Holiday findByDate(Date date);
	
	public List<Holiday> searchByBetweenDate(Date startDate,Date endDate);
}
