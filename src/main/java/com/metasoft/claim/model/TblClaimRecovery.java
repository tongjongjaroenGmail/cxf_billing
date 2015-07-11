package com.metasoft.claim.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_claim_recovery database table.
 * 
 */
@Entity
@Table(name="tbl_claim_recovery")
@NamedQuery(name="TblClaimRecovery.findAll", query="SELECT t FROM TblClaimRecovery t")
public class TblClaimRecovery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="claim_recovery_id")
	private int claimRecoveryId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="accident_date")
	private Date accidentDate;

	@Column(name="agent_id")
	private int agentId;

	@Column(name="claim_amount")
	private float claimAmount;

	@Column(name="claim_insurance_amount")
	private float claimInsuranceAmount;

	@Column(name="claim_number")
	private String claimNumber;

	@Column(name="create_by")
	private int createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="job_cancel_remark")
	private String jobCancelRemark;

	@Column(name="job_close_remark")
	private String jobCloseRemark;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="job_date")
	private Date jobDate;

	@Column(name="job_no")
	private String jobNo;

	@Column(name="job_start_remark")
	private String jobStartRemark;

	@Column(name="license_number")
	private String licenseNumber;

	@Column(name="party_claim_number")
	private String partyClaimNumber;

	@Column(name="party_insurance_id")
	private int partyInsuranceId;

	@Column(name="party_license_number")
	private String partyLicenseNumber;

	@Column(name="party_policy_no")
	private String partyPolicyNo;

	@Column(name="policy_no")
	private String policyNo;

	@Column(name="request_amount")
	private float requestAmount;

	private byte responsibility;

	@Column(name="update_by")
	private int updateBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	//bi-directional many-to-one association to StdReceiveType
	@ManyToOne
	@JoinColumn(name="receive_type_id")
	private StdReceiveType stdReceiveType;

	//bi-directional many-to-one association to StdInsurance
	@ManyToOne
	@JoinColumn(name="insurance_id")
	private StdInsurance stdInsurance;

	//bi-directional many-to-one association to ConJobStatus
	@ManyToOne
	@JoinColumn(name="job_status")
	private ConJobStatus conJobStatus;

	//bi-directional many-to-one association to ConClaimType
	@ManyToOne
	@JoinColumn(name="claim_type")
	private ConClaimType conClaimType;

	//bi-directional many-to-one association to TblInvoice
	@OneToMany(mappedBy="tblClaimRecovery")
	private List<TblInvoice> tblInvoices;

	public TblClaimRecovery() {
	}

	public int getClaimRecoveryId() {
		return this.claimRecoveryId;
	}

	public void setClaimRecoveryId(int claimRecoveryId) {
		this.claimRecoveryId = claimRecoveryId;
	}

	public Date getAccidentDate() {
		return this.accidentDate;
	}

	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}

	public int getAgentId() {
		return this.agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public float getClaimAmount() {
		return this.claimAmount;
	}

	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}

	public float getClaimInsuranceAmount() {
		return this.claimInsuranceAmount;
	}

	public void setClaimInsuranceAmount(float claimInsuranceAmount) {
		this.claimInsuranceAmount = claimInsuranceAmount;
	}

	public String getClaimNumber() {
		return this.claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public int getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getJobCancelRemark() {
		return this.jobCancelRemark;
	}

	public void setJobCancelRemark(String jobCancelRemark) {
		this.jobCancelRemark = jobCancelRemark;
	}

	public String getJobCloseRemark() {
		return this.jobCloseRemark;
	}

	public void setJobCloseRemark(String jobCloseRemark) {
		this.jobCloseRemark = jobCloseRemark;
	}

	public Date getJobDate() {
		return this.jobDate;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}

	public String getJobNo() {
		return this.jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getJobStartRemark() {
		return this.jobStartRemark;
	}

	public void setJobStartRemark(String jobStartRemark) {
		this.jobStartRemark = jobStartRemark;
	}

	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getPartyClaimNumber() {
		return this.partyClaimNumber;
	}

	public void setPartyClaimNumber(String partyClaimNumber) {
		this.partyClaimNumber = partyClaimNumber;
	}

	public int getPartyInsuranceId() {
		return this.partyInsuranceId;
	}

	public void setPartyInsuranceId(int partyInsuranceId) {
		this.partyInsuranceId = partyInsuranceId;
	}

	public String getPartyLicenseNumber() {
		return this.partyLicenseNumber;
	}

	public void setPartyLicenseNumber(String partyLicenseNumber) {
		this.partyLicenseNumber = partyLicenseNumber;
	}

	public String getPartyPolicyNo() {
		return this.partyPolicyNo;
	}

	public void setPartyPolicyNo(String partyPolicyNo) {
		this.partyPolicyNo = partyPolicyNo;
	}

	public String getPolicyNo() {
		return this.policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public float getRequestAmount() {
		return this.requestAmount;
	}

	public void setRequestAmount(float requestAmount) {
		this.requestAmount = requestAmount;
	}

	public byte getResponsibility() {
		return this.responsibility;
	}

	public void setResponsibility(byte responsibility) {
		this.responsibility = responsibility;
	}

	public int getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public StdReceiveType getStdReceiveType() {
		return this.stdReceiveType;
	}

	public void setStdReceiveType(StdReceiveType stdReceiveType) {
		this.stdReceiveType = stdReceiveType;
	}

	public StdInsurance getStdInsurance() {
		return this.stdInsurance;
	}

	public void setStdInsurance(StdInsurance stdInsurance) {
		this.stdInsurance = stdInsurance;
	}

	public ConJobStatus getConJobStatus() {
		return this.conJobStatus;
	}

	public void setConJobStatus(ConJobStatus conJobStatus) {
		this.conJobStatus = conJobStatus;
	}

	public ConClaimType getConClaimType() {
		return this.conClaimType;
	}

	public void setConClaimType(ConClaimType conClaimType) {
		this.conClaimType = conClaimType;
	}

	public List<TblInvoice> getTblInvoices() {
		return this.tblInvoices;
	}

	public void setTblInvoices(List<TblInvoice> tblInvoices) {
		this.tblInvoices = tblInvoices;
	}

}