package com.metasoft.claim.controller.vo;

import java.util.List;

public class EmployeeLeaveVo {

	private Integer cancelEmployeeId;
	private String leaveType;
	private String fromDate;
	private String toDate;
	private Float period;
	private Integer statusId;
	private String status;
	private String by;
	private String cancel;
	private Boolean cancelStatus;
	private Integer leaveRequestId;
	private String requestNote;
	private String rejectNote;

	List<EmployeeSubLeaveVo> employeeSubLeaveVos;

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getRequestNote() {
		return requestNote;
	}

	public void setRequestNote(String requestNote) {
		this.requestNote = requestNote;
	}

	public String getRejectNote() {
		return rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}

	public Integer getCancelEmployeeId() {
		return cancelEmployeeId;
	}

	public void setCancelEmployeeId(Integer cancelEmployeeId) {
		this.cancelEmployeeId = cancelEmployeeId;
	}

	public List<EmployeeSubLeaveVo> getEmployeeSubLeaveVos() {
		return employeeSubLeaveVos;
	}

	public void setEmployeeSubLeaveVos(List<EmployeeSubLeaveVo> employeeSubLeaveVos) {
		this.employeeSubLeaveVos = employeeSubLeaveVos;
	}

	public Integer getLeaveRequestId() {
		return leaveRequestId;
	}

	public void setLeaveRequestId(Integer leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Float getPeriod() {
		return period;
	}

	public void setPeriod(Float period) {
		this.period = period;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public Boolean getCancelStatus() {
		return cancelStatus;
	}

	public void setCancelStatus(Boolean cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

}
