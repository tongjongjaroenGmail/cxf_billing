package com.metasoft.billing.xml.bean;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement (name="provinces")
public class Provinces {

    private Map<String, Province> provinceMap = new HashMap<String, Province>();

	public Map<String, Province> getProvinceMap() {
		return provinceMap;
	}

	public void setProvinceMap(Map<String, Province> provinceMap) {
		this.provinceMap = provinceMap;
	}
}