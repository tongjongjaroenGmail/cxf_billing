/**
 * 
 */
package com.bredit.leavemanagement.dao;

import java.util.Date;
import java.util.List;

import com.bredit.leavemanagement.bean.query.ViewLeaveQuery;
import com.bredit.leavemanagement.model.EmployeeStatus;
import com.bredit.leavemanagement.model.LeaveRequest;
import com.bredit.leavemanagement.model.LeaveRequestStatus;

/**
 * @author KaweepattC
 * 
 */
public interface LeaveRequestDao extends AbstractDao<LeaveRequest, Integer>
{

    /**
     * 
     * @param employeeId
     * @return
     */
    List<LeaveRequest> findByEmployeeId(Integer employeeId);
	
	public List<LeaveRequest> searchByEmployeeNameAndLeaveTypeAndLeaveDateAndLeaveStatus(String employeeName, Integer leaveTypeId, Date leaveDateStart,
            Date leaveDateEnd,Integer leaveStatus);
	
	public List<ViewLeaveQuery> searchViewLeave(Date startDate, Date endDate,List<LeaveRequestStatus> leaveRequestStatuses,List<EmployeeStatus> employeeStatuses);
	
	public List<LeaveRequest> searchByEmployeeIdAndDateAndStatuses(Integer employeeId,Date startDate, Date endDate,List<LeaveRequestStatus> leaveRequestStatuses);
}
