package com.metasoft.claim.controller.vo;

public class ViewLeaveDateDetailVo {
	private Integer leaveRequestId;
	private boolean am;
	private boolean pm;

	public Integer getLeaveRequestId() {
		return leaveRequestId;
	}

	public void setLeaveRequestId(Integer leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}

	public boolean isAm() {
		return am;
	}

	public void setAm(boolean am) {
		this.am = am;
	}

	public boolean isPm() {
		return pm;
	}

	public void setPm(boolean pm) {
		this.pm = pm;
	}

}
