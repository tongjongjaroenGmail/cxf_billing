package com.metasoft.billing.model;

public enum AreaType {

	bkk(0, "กทม"),
	perimeter(1, "ปริมณฑล"),
	country(2, "ตจว");

	private int id;
	private String name;

	private AreaType(int id, String name) {
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

	public static AreaType getById(int id) {
		for (AreaType e : values()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
}
