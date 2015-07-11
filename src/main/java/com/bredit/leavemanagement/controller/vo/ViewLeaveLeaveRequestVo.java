package com.bredit.leavemanagement.controller.vo;

import java.util.ArrayList;
import java.util.List;


public class ViewLeaveLeaveRequestVo {
	private Integer leaveRequestId;
	private Integer leaveTypeId;
	private String leaveTypeName;
	private String leaveTypeColor;
	private Integer statusId;
	private String statusName;
	private String requestDate;
	private String requestNote;
	
	private List<ViewLeaveLeaveVo> viewLeaveLeaveVos = new ArrayList<ViewLeaveLeaveVo>();

	public Integer getLeaveRequestId() {
		return leaveRequestId;
	}

	public void setLeaveRequestId(Integer leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}

	public Integer getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(Integer leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public String getLeaveTypeColor() {
		return leaveTypeColor;
	}

	public void setLeaveTypeColor(String leaveTypeColor) {
		this.leaveTypeColor = leaveTypeColor;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestNote() {
		return requestNote;
	}

	public void setRequestNote(String requestNote) {
		this.requestNote = requestNote;
	}

	public List<ViewLeaveLeaveVo> getViewLeaveLeaveVos() {
		return viewLeaveLeaveVos;
	}

	public void setViewLeaveLeaveVos(List<ViewLeaveLeaveVo> viewLeaveLeaveVos) {
		this.viewLeaveLeaveVos = viewLeaveLeaveVos;
	}
	
	
}
