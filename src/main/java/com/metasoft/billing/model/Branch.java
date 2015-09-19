package com.metasoft.billing.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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

	@Column(name = "name",nullable=false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	private List<Province> Provinces;

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

	public List<Province> getProvinces() {
		return Provinces;
	}

	public void setProvinces(List<Province> provinces) {
		Provinces = provinces;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}