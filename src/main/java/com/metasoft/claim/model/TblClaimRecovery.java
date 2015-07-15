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
@Table(name = "tbl_claim_recovery")
@NamedQuery(name = "TblClaimRecovery.findAll", query = "SELECT t FROM TblClaimRecovery t")
public class TblClaimRecovery extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "accident_date")
	private Date accidentDate;

	@ManyToOne
	@JoinColumn(name = "agent_id")
	private SecUser agent;

	@Column(name = "claim_amount")
	private float claimAmount;

	@Column(name = "claim_insurance_amount")
	private float claimInsuranceAmount;

	@Column(name = "claim_number")
	private String claimNumber;

	@Column(name = "create_by")
	private int createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "job_cancel_remark")
	private String jobCancelRemark;

	@Column(name = "job_close_remark")
	private String jobCloseRemark;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "job_date")
	private Date jobDate;

	@Column(name = "job_no")
	private String jobNo;

	@Column(name = "job_start_remark")
	private String jobStartRemark;

	@Column(name = "license_number")
	private String licenseNumber;

	@Column(name = "party_claim_number")
	private String partyClaimNumber;

	@Column(name = "party_insurance_id")
	private int partyInsuranceId;

	@Column(name = "party_license_number")
	private String partyLicenseNumber;

	@Column(name = "party_policy_no")
	private String partyPolicyNo;

	@Column(name = "policy_no")
	private String policyNo;

	@Column(name = "request_amount")
	private float requestAmount;

	private byte responsibility;

	@Column(name = "update_by")
	private int updateBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;

	@Enumerated(EnumType.ORDINAL)
	private ReceiveMoneyType receiveMoneyType;

	// bi-directional many-to-one association to StdInsurance
	@ManyToOne
	@JoinColumn(name = "insurance_id")
	private StdInsurance stdInsurance;

	@Enumerated(EnumType.ORDINAL)
	private JobStatus jobStatus = JobStatus.RECEIVED;

	@Enumerated(EnumType.ORDINAL)
	private ClaimType claimType;

	// bi-directional many-to-one association to TblInvoice
	@OneToMany(mappedBy = "tblClaimRecovery")
	private List<TblInvoice> tblInvoices;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "maturity_date")
	private Date maturityDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}

	public SecUser getAgent() {
		return agent;
	}

	public void setAgent(SecUser agent) {
		this.agent = agent;
	}

	public float getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}

	public float getClaimInsuranceAmount() {
		return claimInsuranceAmount;
	}

	public void setClaimInsuranceAmount(float claimInsuranceAmount) {
		this.claimInsuranceAmount = claimInsuranceAmount;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getJobCancelRemark() {
		return jobCancelRemark;
	}

	public void setJobCancelRemark(String jobCancelRemark) {
		this.jobCancelRemark = jobCancelRemark;
	}

	public String getJobCloseRemark() {
		return jobCloseRemark;
	}

	public void setJobCloseRemark(String jobCloseRemark) {
		this.jobCloseRemark = jobCloseRemark;
	}

	public Date getJobDate() {
		return jobDate;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getJobStartRemark() {
		return jobStartRemark;
	}

	public void setJobStartRemark(String jobStartRemark) {
		this.jobStartRemark = jobStartRemark;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getPartyClaimNumber() {
		return partyClaimNumber;
	}

	public void setPartyClaimNumber(String partyClaimNumber) {
		this.partyClaimNumber = partyClaimNumber;
	}

	public int getPartyInsuranceId() {
		return partyInsuranceId;
	}

	public void setPartyInsuranceId(int partyInsuranceId) {
		this.partyInsuranceId = partyInsuranceId;
	}

	public String getPartyLicenseNumber() {
		return partyLicenseNumber;
	}

	public void setPartyLicenseNumber(String partyLicenseNumber) {
		this.partyLicenseNumber = partyLicenseNumber;
	}

	public String getPartyPolicyNo() {
		return partyPolicyNo;
	}

	public void setPartyPolicyNo(String partyPolicyNo) {
		this.partyPolicyNo = partyPolicyNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public float getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(float requestAmount) {
		this.requestAmount = requestAmount;
	}

	public byte getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(byte responsibility) {
		this.responsibility = responsibility;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public ReceiveMoneyType getReceiveMoneyType() {
		return receiveMoneyType;
	}

	public void setReceiveMoneyType(ReceiveMoneyType receiveMoneyType) {
		this.receiveMoneyType = receiveMoneyType;
	}

	public StdInsurance getStdInsurance() {
		return stdInsurance;
	}

	public void setStdInsurance(StdInsurance stdInsurance) {
		this.stdInsurance = stdInsurance;
	}

	public JobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public ClaimType getClaimType() {
		return claimType;
	}

	public void setClaimType(ClaimType claimType) {
		this.claimType = claimType;
	}

	public List<TblInvoice> getTblInvoices() {
		return tblInvoices;
	}

	public void setTblInvoices(List<TblInvoice> tblInvoices) {
		this.tblInvoices = tblInvoices;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}