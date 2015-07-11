package com.metasoft.claim.bean.query;

import java.io.Serializable;

import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.LeaveRequest;

public class ViewLeaveQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -876121300367987650L;
	private LeaveRequest leaveRequest;
	private Employee employee;

	public LeaveRequest getLeaveRequest() {
		return leaveRequest;
	}

	public void setLeaveRequest(LeaveRequest leaveRequest) {
		this.leaveRequest = leaveRequest;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
