package com.metasoft.billing.bean.cont;

public enum AreaType {

	bkk("กทม"),
	perimeter("ปริมณฑล"),
	country("ตจว.");
	
	private final String value;

    private AreaType(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
}
