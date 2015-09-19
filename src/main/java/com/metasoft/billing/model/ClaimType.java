package com.metasoft.billing.model;

public enum ClaimType {

	follow(0, "ติดตาม");

	private int id;
	private String name;

	private ClaimType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static ClaimType getById(int id) {
		for (ClaimType e : values()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
}
