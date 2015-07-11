/**
 * 
 */
package com.metasoft.claim.dao;

import java.util.Date;
import java.util.List;

import com.metasoft.claim.model.Leave;
import com.metasoft.claim.model.LeaveRequestStatus;

/**
 * @author 
 * 
 */
public interface LeaveDao extends AbstractDao<Leave, Integer>
{

	 public List<Leave> searchByEmployeeIdAndDateAndStatuses(Integer employeeId, Date startDate, Date endDate,List<LeaveRequestStatus> statuses);

	 public List<Leave> searchByEmployeeIdAndDateAndStatusesAndLeaveTypes(Integer employeeId, Date startDate, Date endDate,List<LeaveRequestStatus> statuses,List<Integer> leaveTypeIds);
}
