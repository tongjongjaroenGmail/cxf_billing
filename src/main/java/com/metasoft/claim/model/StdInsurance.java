package com.metasoft.claim.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the std_insurance database table.
 * 
 */
@Entity
@Table(name="std_insurance")
@NamedQuery(name="StdInsurance.findAll", query="SELECT s FROM StdInsurance s")
public class StdInsurance extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="insurance_id")
	private int insuranceId;

	@Column(name="insurance_name")
	private String insuranceName;

	public StdInsurance() {
	}

	public int getInsuranceId() {
		return this.insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getInsuranceName() {
		return this.insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	@Override
	public Serializable getId() {
		return insuranceId;
	}
}