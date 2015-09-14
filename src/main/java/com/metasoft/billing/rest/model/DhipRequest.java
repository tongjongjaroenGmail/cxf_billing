package com.metasoft.billing.rest.model;

public class DhipRequest {
	private String surveyId;// รหัสพนักงาน
	private String branch;// สาขา/ศูนย์
	private String amphur;// อำเภอที่ตรวจสอบ
	private String province;// จังหวัดที่ตรวจสอบ
	private String areaType;// พื้นที่
	private String claimType;// ประเภทเคลม
	private String dutyType;// เวร
	private String outArea;// นอกพื้นที่
	private String disperse;// แยกย้าย
	private String serviceType;// บริการ
	private Integer photoCount;// จำนวนรูปถ่าย
	private Integer dailyCount;// จำนวนข้อประจำวัน
	private String thirdPartyType;// เงื่อนไขฝ่ายถูก
	private String claimFee;// เงินเรียกร้อง
	private String insureFee;// ค่าประกันตัว
	private String otherFee;// ค่าใช้จ่ายอื่นๆ

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAmphur() {
		return amphur;
	}

	public void setAmphur(String amphur) {
		this.amphur = amphur;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getDutyType() {
		return dutyType;
	}

	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}

	public String getOutArea() {
		return outArea;
	}

	public void setOutArea(String outArea) {
		this.outArea = outArea;
	}

	public String getDisperse() {
		return disperse;
	}

	public void setDisperse(String disperse) {
		this.disperse = disperse;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(Integer photoCount) {
		this.photoCount = photoCount;
	}

	public Integer getDailyCount() {
		return dailyCount;
	}

	public void setDailyCount(Integer dailyCount) {
		this.dailyCount = dailyCount;
	}

	public String getThirdPartyType() {
		return thirdPartyType;
	}

	public void setThirdPartyType(String thirdPartyType) {
		this.thirdPartyType = thirdPartyType;
	}

	public String getClaimFee() {
		return claimFee;
	}

	public void setClaimFee(String claimFee) {
		this.claimFee = claimFee;
	}

	public String getInsureFee() {
		return insureFee;
	}

	public void setInsureFee(String insureFee) {
		this.insureFee = insureFee;
	}

	public String getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}

}
