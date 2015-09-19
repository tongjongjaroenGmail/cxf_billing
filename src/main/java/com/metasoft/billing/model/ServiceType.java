package com.metasoft.billing.model;

public enum ServiceType {

	home(0, "หน้าร้าน"), cont(1, "ต่อเนื่อง"), service(2, "บริการ"), sameArea(3, "พื้นที่เดียวกัน");

	private int id;
	private String name;

	private ServiceType(int id, String name) {
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

	public static ServiceType getById(int id) {
		for (ServiceType e : values()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}

}
