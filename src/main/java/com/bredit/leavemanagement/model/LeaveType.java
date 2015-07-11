package com.bredit.leavemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leave_type")
public class LeaveType extends BaseModel {
	private static final long serialVersionUID = 1581007912030856413L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "max_leave_days")
	private Float maxLeaveDays;

	@Column(name = "color", nullable = false)
	private String color;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "parent_id", nullable = true)
	private LeaveType parent;

	@Column(name = "order_number", nullable = false)
	private Integer orderNumber;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getMaxLeaveDays() {
		return maxLeaveDays;
	}

	public void setMaxLeaveDays(Float maxLeaveDays) {
		this.maxLeaveDays = maxLeaveDays;
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LeaveType getParent() {
		return parent;
	}

	public void setParent(LeaveType parent) {
		this.parent = parent;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
