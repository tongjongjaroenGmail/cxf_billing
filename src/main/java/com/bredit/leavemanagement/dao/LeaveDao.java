/**
 * 
 */
package com.bredit.leavemanagement.dao;

import java.util.Date;
import java.util.List;

import com.bredit.leavemanagement.model.Leave;
import com.bredit.leavemanagement.model.LeaveRequestStatus;

/**
 * @author KaweepattC
 * 
 */
public interface LeaveDao extends AbstractDao<Leave, Integer>
{

	 public List<Leave> searchByEmployeeIdAndDateAndStatuses(Integer employeeId, Date startDate, Date endDate,List<LeaveRequestStatus> statuses);

	 public List<Leave> searchByEmployeeIdAndDateAndStatusesAndLeaveTypes(Integer employeeId, Date startDate, Date endDate,List<LeaveRequestStatus> statuses,List<Integer> leaveTypeIds);
}
