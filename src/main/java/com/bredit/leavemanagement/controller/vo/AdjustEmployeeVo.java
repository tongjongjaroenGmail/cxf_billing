package com.bredit.leavemanagement.controller.vo;

public class AdjustEmployeeVo {
	private Integer employeeId;
	private String employeeName;
	private Integer departmentId;
	private Integer roleId;
	private Integer workingPolicyId;

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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getWorkingPolicyId() {
		return workingPolicyId;
	}

	public void setWorkingPolicyId(Integer workingPolicyId) {
		this.workingPolicyId = workingPolicyId;
	}
}
