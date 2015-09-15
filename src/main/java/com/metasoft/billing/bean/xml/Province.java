package com.metasoft.billing.bean.xml;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElementWrapper;

public class Province {
	private String mainProvinceName;

	@XmlElementWrapper
	private Map<String, amphur> amphorMap = new HashMap<String, amphur>();

	public String getMainProvinceName() {
		return mainProvinceName;
	}

	public void setMainProvinceName(String mainProvinceName) {
		this.mainProvinceName = mainProvinceName;
	}

	public Map<String, amphur> getAmphorMap() {
		return amphorMap;
	}

}
