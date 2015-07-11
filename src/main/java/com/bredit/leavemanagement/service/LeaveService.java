/**
 * 
 */
package com.bredit.leavemanagement.service;

import java.util.Date;
import java.util.List;

import com.bredit.leavemanagement.dao.LeaveDao;
import com.bredit.leavemanagement.model.Leave;
import com.bredit.leavemanagement.model.LeaveRequestStatus;


public interface LeaveService extends ModelBasedService<LeaveDao, Leave, Integer>
{ 
	public Float calcRemainLeaveDays(Integer employeeId, Integer leaveTypeId, Integer year);
	
	public Float calcUsedLeaveDaysInCurrentYear(Integer employeeId, Integer leaveTypeId);
	
	public Float calcUsedLeaveDaysInCurrentYear(Integer employeeId, List<Integer> leaveTypeIds);
	
	public Float calcUsedLeaveDays(Integer employeeId, Integer leaveTypeId, Integer year);
	
	public Float calcUsedLeaveDays(Integer employeeId, List<Integer> leaveTypeIds, Integer year);
	
	public List<Leave> searchByEmployeeIdAndDateAndStatuses(Integer employeeId, Date startDate, Date endDate,List<LeaveRequestStatus> statuses);
}
