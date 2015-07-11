/**
 * 
 */
package com.bredit.leavemanagement.service;

import java.util.Date;
import java.util.List;

import com.bredit.leavemanagement.bean.query.ViewLeaveQuery;
import com.bredit.leavemanagement.controller.vo.ResultVo;
import com.bredit.leavemanagement.controller.vo.ViewLeaveLeaveVo;
import com.bredit.leavemanagement.dao.LeaveRequestDao;
import com.bredit.leavemanagement.model.Employee;
import com.bredit.leavemanagement.model.EmployeeStatus;
import com.bredit.leavemanagement.model.LeaveRequest;
import com.bredit.leavemanagement.model.LeaveRequestStatus;

/**
 * @author KaweepattC
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
