package com.metasoft.claim.controller.vo;

public class TrackingSearchCriteriaVo {
	private String jobDateStart;
	private String jobDateEnd;
	private Integer insuranceId;
	private Integer claimTypeId;
	
	
	private Integer draw;
	private Long recordsTotal;
	private Long recordsFiltered;
	
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public String getJobDateStart() {
		return jobDateStart;
	}
	public void setJobDateStart(String jobDateStart) {
		this.jobDateStart = jobDateStart;
	}
	public String getJobDateEnd() {
		return jobDateEnd;
	}
	public void setJobDateEnd(String jobDateEnd) {
		this.jobDateEnd = jobDateEnd;
	}
	public Integer getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}
	
	public Integer getClaimTypeId() {
		return claimTypeId;
	}
	public void setClaimTypeId(Integer claimTypeId) {
		this.claimTypeId = claimTypeId;
	}
	
	
	
}
