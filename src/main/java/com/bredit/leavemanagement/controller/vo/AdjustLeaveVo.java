package com.bredit.leavemanagement.controller.vo;

public class AdjustLeaveVo {
	private String employeeName;
	private Integer employeeId;
	private Integer departmentId;
	private Float carryForward;
	private Float leaveAvailable;
	private Float totalLeave;
	private String note;
	private Integer leaveProfileId;
	private Integer year;
	
	
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getLeaveProfileId() {
		return leaveProfileId;
	}

	public void setLeaveProfileId(Integer leaveProfileId) {
		this.leaveProfileId = leaveProfileId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}



	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Float getCarryForward() {
		return carryForward;
	}

	public void setCarryForward(Float carryForward) {
		this.carryForward = carryForward;
	}

	public Float getLeaveAvailable() {
		return leaveAvailable;
	}

	public void setLeaveAvailable(Float leaveAvailable) {
		this.leaveAvailable = leaveAvailable;
	}

	public Float getTotalLeave() {
		return totalLeave;
	}

	public void setTotalLeave(Float totalLeave) {
		this.totalLeave = totalLeave;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
