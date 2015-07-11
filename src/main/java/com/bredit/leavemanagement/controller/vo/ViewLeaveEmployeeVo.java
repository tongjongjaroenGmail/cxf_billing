package com.bredit.leavemanagement.controller.vo;

import java.util.ArrayList;
import java.util.List;

public class ViewLeaveEmployeeVo {

	private Integer employeeId;
	private String employeeName;
	private Integer departmentId;

	private List<ViewLeaveDateVo> viewLeaveDateVos = new ArrayList<ViewLeaveDateVo>();
	private List<ViewLeaveLeaveRequestVo> viewLeaveLeaveRequestVos = new ArrayList<ViewLeaveLeaveRequestVo>();

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public List<ViewLeaveDateVo> getViewLeaveDateVos() {
		return viewLeaveDateVos;
	}

	public void setViewLeaveDateVos(List<ViewLeaveDateVo> viewLeaveDateVos) {
		this.viewLeaveDateVos = viewLeaveDateVos;
	}

	public List<ViewLeaveLeaveRequestVo> getViewLeaveLeaveRequestVos() {
		return viewLeaveLeaveRequestVos;
	}

	public void setViewLeaveLeaveRequestVos(List<ViewLeaveLeaveRequestVo> viewLeaveLeaveRequestVos) {
		this.viewLeaveLeaveRequestVos = viewLeaveLeaveRequestVos;
	}

}
