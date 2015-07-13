package com.metasoft.claim.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}