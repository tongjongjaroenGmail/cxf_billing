package com.bredit.leavemanagement.controller.vo;

import java.util.List;

public class HistoryLogVo {
	private String employeeName;
	private String leaveTypeName;
	private String leaveRequestStatus;
	private Integer leaveRequestStatusId;
	private String requestDate;
	private String requestNote;
	private String rejectNote;
	private Integer employeeStatus;

	List<HistoryLogDeailVo> historyLogDetailVos;

	public Integer getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(Integer employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public Integer getLeaveRequestStatusId() {
		return leaveRequestStatusId;
	}

	public void setLeaveRequestStatusId(Integer leaveRequestStatusId) {
		this.leaveRequestStatusId = leaveRequestStatusId;
	}

	public String getRejectNote() {
		return rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}

	public String getRequestNote() {
		return requestNote;
	}

	public void setRequestNote(String requestNote) {
		this.requestNote = requestNote;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getLeaveRequestStatus() {
		return leaveRequestStatus;
	}

	public void setLeaveRequestStatus(String leaveRequestStatus) {
		this.leaveRequestStatus = leaveRequestStatus;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public List<HistoryLogDeailVo> getHistoryLogDetailVos() {
		return historyLogDetailVos;
	}

	public void setHistoryLogDetailVos(List<HistoryLogDeailVo> historyLogDetailVos) {
		this.historyLogDetailVos = historyLogDetailVos;
	}

}
