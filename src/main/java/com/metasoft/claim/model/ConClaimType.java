package com.metasoft.claim.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the con_claim_type database table.
 * 
 */
@Entity
@Table(name = "con_claim_type")
@NamedQuery(name = "ConClaimType.findAll", query = "SELECT c FROM ConClaimType c")
public class ConClaimType extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5545699722315208285L;

	@Id
	@Column(name = "claim_type")
	private String claimType;

	@Column(name = "claim_name")
	private String claimName;

	public ConClaimType() {
	}

	public String getClaimType() {
		return this.claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getClaimName() {
		return this.claimName;
	}

	public void setClaimName(String claimName) {
		this.claimName = claimName;
	}

	@Override
	public Serializable getId() {
		return claimType;
	}
}