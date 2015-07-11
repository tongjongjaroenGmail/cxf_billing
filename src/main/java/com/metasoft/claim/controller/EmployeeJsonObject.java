package com.metasoft.claim.controller;

import java.util.List;

import com.metasoft.claim.controller.vo.EmployeeLeaveVo;

public class EmployeeJsonObject {
	
	private int iTotalRecords;

	private int iTotalDisplayRecords;

	private String sEcho;

	private String sColumns;

	private List<EmployeeLeaveVo> aaData;

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<EmployeeLeaveVo> getAaData() {
		return aaData;
	}

	public void setAaData(List<EmployeeLeaveVo> aaData) {
		this.aaData = aaData;
	}
}
