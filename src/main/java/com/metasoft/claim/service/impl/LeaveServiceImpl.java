/**
 * 
 */
package com.metasoft.claim.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metasoft.claim.dao.EmployeeDao;
import com.metasoft.claim.dao.LeaveDao;
import com.metasoft.claim.dao.LeaveTypeDao;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.Leave;
import com.metasoft.claim.model.LeaveProfile;
import com.metasoft.claim.model.LeaveRequestStatus;
import com.metasoft.claim.model.LeaveType;
import com.metasoft.claim.service.LeaveService;

@Component
public class LeaveServiceImpl extends ModelBasedServiceImpl<LeaveDao, Leave, Integer> implements LeaveService
{
    private LeaveDao leaveDao;

	@Autowired
	private LeaveTypeDao leaveTypeDao;

	@Autowired
	private EmployeeDao employeeDao;

    /**
     * 
     * @param entityClass
     */
    @Autowired
    public LeaveServiceImpl(LeaveDao leaveDao)
    {
        super(leaveDao);
        this.leaveDao = leaveDao;
    }
    
    @Override
    public Float calcUsedLeaveDaysInCurrentYear(Integer employeeId, Integer leaveTypeId) {
		List<Integer> leaveTypeIds = new ArrayList<Integer>();
		leaveTypeIds.add(leaveTypeId);
		return calcUsedLeaveDays(employeeId, leaveTypeIds , Calendar.getInstance().get(Calendar.YEAR));
	}
    
    @Override
    public Float calcUsedLeaveDaysInCurrentYear(Integer employeeId, List<Integer> leaveTypeIds) {
		return calcUsedLeaveDays(employeeId, leaveTypeIds , Calendar.getInstance().get(Calendar.YEAR));
	}
    
    @Override
    public Float calcUsedLeaveDays(Integer employeeId, Integer leaveTypeId, Integer year) {
		List<Integer> leaveTypeIds = new ArrayList<Integer>();
		leaveTypeIds.add(leaveTypeId);
		return calcUsedLeaveDays(employeeId, leaveTypeIds , year);
	}
    
    @Override
	public Float calcUsedLeaveDays(Integer employeeId, List<Integer> leaveTypeIds, Integer year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		Date startDate = cal.getTime();

		// set date to last day of year
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 11); // 11 = december
		cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND,59);
		Date endDate = cal.getTime();

		List<LeaveRequestStatus> statuses = new ArrayList<LeaveRequestStatus>();
		statuses.add(LeaveRequestStatus.NEW);
		statuses.add(LeaveRequestStatus.VIEWED);
		statuses.add(LeaveRequestStatus.APPROVED);

		List<Leave> leaves = leaveDao.searchByEmployeeIdAndDateAndStatusesAndLeaveTypes
				(employeeId, startDate, endDate, statuses, leaveTypeIds);

		float takenLeave = 0;
		for (Leave leave : leaves) {
			if(leave.getAm()){
				takenLeave += 0.5;
			}
			if(leave.getPm()){
				takenLeave += 0.5;
			}
		}

		return takenLeave;
	}
	@Override
    public Float calcRemainLeaveDays(Integer employeeId, Integer leaveTypeId , Integer year) {
		Float takenLeave = calcUsedLeaveDays(employeeId, leaveTypeId , year);

		LeaveType leaveType = leaveTypeDao.findById(leaveTypeId);
		Employee employee = employeeDao.findById(employeeId);

		// if leaveType.getMaxLeaveDays() == null. It mean this leave type no limit day.
		float totalCanLeaveDays = leaveType.getMaxLeaveDays() == null ? 366 : leaveType.getMaxLeaveDays();
		
		if (leaveType.getId().intValue() == 1) {
			LeaveProfile leaveProfile = employee.getLeaveProfileByYear(year);
			if(leaveProfile != null){
				totalCanLeaveDays = leaveProfile.getLeaveAvailable() + leaveProfile.getCarryForward();
			}else{
				totalCanLeaveDays = 0;
			}
		}

		return (totalCanLeaveDays - takenLeave);
	}

	@Override
	public List<Leave> searchByEmployeeIdAndDateAndStatuses(Integer employeeId, Date startDate, Date endDate,List<LeaveRequestStatus> statuses) {
		return leaveDao.searchByEmployeeIdAndDateAndStatuses(employeeId, startDate, endDate,statuses);
	}
}
