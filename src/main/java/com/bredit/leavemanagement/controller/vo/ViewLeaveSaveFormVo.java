package com.bredit.leavemanagement.controller.vo;

import java.util.List;

public class ViewLeaveSaveFormVo {
	private Integer employeeId;
	private Integer leaveTypeId;
	private Float totalNoDays;
	private String requestNote;
	private List<ViewLeaveLeaveVo> viewLeaveLeaveVos;
	
	public String getRequestNote() {
		return requestNote;
	}

	public void setRequestNote(String requestNote) {
		this.requestNote = requestNote;
	}

	public Float getTotalNoDays() {
		return totalNoDays;
	}

	public void setTotalNoDays(Float totalNoDays) {
		this.totalNoDays = totalNoDays;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(Integer leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public List<ViewLeaveLeaveVo> getViewLeaveLeaveVos() {
		return viewLeaveLeaveVos;
	}

	public void setViewLeaveLeaveVos(List<ViewLeaveLeaveVo> viewLeaveLeaveVos) {
		this.viewLeaveLeaveVos = viewLeaveLeaveVos;
	}

}
