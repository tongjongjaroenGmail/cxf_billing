package com.metasoft.claim.controller.vo;

import java.util.ArrayList;
import java.util.List;

public class ViewLeaveDateVo {
	private String date;
	private boolean today;
	private boolean holiday;
	private boolean weekend;
	
	private List<ViewLeaveDateDetailVo> viewLeaveDateDetailVos = new ArrayList<ViewLeaveDateDetailVo>();

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isToday() {
		return today;
	}

	public void setToday(boolean today) {
		this.today = today;
	}

	public boolean isHoliday() {
		return holiday;
	}

	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}

	public boolean isWeekend() {
		return weekend;
	}

	public void setWeekend(boolean weekend) {
		this.weekend = weekend;
	}

	public List<ViewLeaveDateDetailVo> getViewLeaveDateDetailVos() {
		return viewLeaveDateDetailVos;
	}

	public void setViewLeaveDateDetailVos(List<ViewLeaveDateDetailVo> viewLeaveDateDetailVos) {
		this.viewLeaveDateDetailVos = viewLeaveDateDetailVos;
	}

}
