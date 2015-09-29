package com.metasoft.billing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

/**
 * The persistent class for the sec_user database table.
 * 
 */
@Entity
@Table(name = "amphur")
@NamedQuery(name = "Amphur.findAll", query = "SELECT s FROM Amphur s")
public class Amphur extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "province_id", nullable = false)
	private Province province;

	@OneToOne(mappedBy = "amphur")
	private Branch branch;
	
	@OneToOne(mappedBy = "amphur")
	private SubBranch subBranch;

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

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public SubBranch getSubBranch() {
		return subBranch;
	}

	public void setSubBranch(SubBranch subBranch) {
		this.subBranch = subBranch;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}