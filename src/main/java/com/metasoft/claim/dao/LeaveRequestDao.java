/**
 * 
 */
package com.metasoft.claim.dao;

import java.util.Date;
import java.util.List;

import com.metasoft.claim.bean.query.ViewLeaveQuery;
import com.metasoft.claim.model.EmployeeStatus;
import com.metasoft.claim.model.LeaveRequest;
import com.metasoft.claim.model.LeaveRequestStatus;

/**
 * @author 
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
