package com.metasoft.billing.bean.cont;

public enum ServiceType {

	home("หน้าร้าน"),
	cont("ต่อเนื่อง"),
	service("บริการ");
	
	private final String value;

    private ServiceType(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
}
