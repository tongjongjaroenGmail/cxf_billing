package com.metasoft.claim.controller.vo;

import java.util.List;

public class EmployeeInfoVo {
	private String firstname;
	private String lastname;
	private Float availableLeave;
	private Float carryForward;
	private List<String> lineManagers;
	private Float takenAnnualLeave;
	private Float takenSickLeave;
	private Float takenOtherLeave;
	private String employeeId;
	private String joinDate;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Float getAvailableLeave() {
		return availableLeave;
	}

	public void setAvailableLeave(Float availableLeave) {
		this.availableLeave = availableLeave;
	}

	public Float getCarryForward() {
		return carryForward;
	}

	public void setCarryForward(Float CarryForward) {
		this.carryForward = CarryForward;
	}


	public Float getTakenAnnualLeave() {
		return takenAnnualLeave;
	}

	public void setTakenAnnualLeave(Float takenAnnualLeave) {
		this.takenAnnualLeave = takenAnnualLeave;
	}

	public Float getTakenSickLeave() {
		return takenSickLeave;
	}

	public void setTakenSickLeave(Float takenSickLeave) {
		this.takenSickLeave = takenSickLeave;
	}

	public Float getTakenOtherLeave() {
		return takenOtherLeave;
	}

	public void setTakenOtherLeave(Float takenOtherLeave) {
		this.takenOtherLeave = takenOtherLeave;
	}

	public List<String> getLineManagers() {
		return lineManagers;
	}

	public void setLineManagers(List<String> lineManagers) {
		this.lineManagers = lineManagers;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	
}
