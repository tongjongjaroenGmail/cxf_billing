/**
 * 
 */
package com.metasoft.claim.service;

import java.util.Date;
import java.util.List;

import com.metasoft.claim.bean.query.ViewLeaveQuery;
import com.metasoft.claim.controller.vo.ResultVo;
import com.metasoft.claim.controller.vo.ViewLeaveLeaveVo;
import com.metasoft.claim.dao.LeaveRequestDao;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.EmployeeStatus;
import com.metasoft.claim.model.LeaveRequest;
import com.metasoft.claim.model.LeaveRequestStatus;

/**
 * @author 
 * 
 */
public interface LeaveRequestService extends ModelBasedService<LeaveRequestDao, LeaveRequest, Integer> {
	public ResultVo validateBeforeAdd(Integer employeeId, Integer leaveTypeId, List<ViewLeaveLeaveVo> viewLeaveLeaveVos);

	public ResultVo validateBeforeUpdate(LeaveRequest leaveRequest, Integer newLeaveStatus, Employee loginEmployee);

	public List<LeaveRequest> searchByEmployeeNameAndLeaveTypeAndLeaveDateAndLeaveStatus(String employeeName,
			Integer leaveTypeId, Date leaveDateStart , Date leaveDateEnd,Integer leaveStatus);
	
	public List<ViewLeaveQuery> searchViewLeave(Date startDate, Date endDate,List<LeaveRequestStatus> leaveRequestStatuses,List<EmployeeStatus> employeeStatuses);
	
	public List<LeaveRequest> searchByEmployeeIdAndDateAndStatuses(Integer employeeId, Date startDate, Date endDate,
			List<LeaveRequestStatus> leaveRequestStatuses);
}
