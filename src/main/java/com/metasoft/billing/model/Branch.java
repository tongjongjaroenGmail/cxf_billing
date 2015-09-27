package com.metasoft.billing.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

/**
 * The persistent class for the sec_user database table.
 * 
 */
@Entity
@Table(name = "branch")
@NamedQuery(name = "Branch.findAll", query = "SELECT s FROM Branch s")
public class Branch extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	private String name;

	@OneToOne
	@JoinColumn(name = "amphur_id", nullable = false)
	private Amphur amphur;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	private List<SubBranch> subBranchs;

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

	public Amphur getAmphur() {
		return amphur;
	}

	public void setAmphur(Amphur amphur) {
		this.amphur = amphur;
	}

	public List<SubBranch> getSubBranchs() {
		return subBranchs;
	}

	public void setSubBranchs(List<SubBranch> subBranchs) {
		this.subBranchs = subBranchs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}