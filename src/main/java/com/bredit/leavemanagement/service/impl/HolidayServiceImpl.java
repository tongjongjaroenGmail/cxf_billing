/**
 * 
 */
package com.bredit.leavemanagement.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bredit.leavemanagement.dao.HolidayDao;
import com.bredit.leavemanagement.model.Holiday;
import com.bredit.leavemanagement.service.HolidayService;

@Component
public class HolidayServiceImpl extends ModelBasedServiceImpl<HolidayDao, Holiday, Integer> implements HolidayService
{
    private HolidayDao holidayDao;

    /**
     * 
     * @param entityClass
     */
    @Autowired
    public HolidayServiceImpl(HolidayDao holidayDao)
    {
        super(holidayDao);
        this.holidayDao = holidayDao;
    }

	@Override
	public Holiday findByDate(Date date) {
		return holidayDao.findByDate(date);
	}

	@Override
	public List<Holiday> searchByBetweenDate(Date startDate, Date endDate) {
		return holidayDao.searchByBetweenDate(startDate, endDate);
	}

}
