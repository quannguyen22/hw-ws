package com.viettel.ocs.bean;

import java.util.HashMap;
import java.util.List;

public class XmlResultObj {
	
	private Integer status;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<HashMap<String, String>> getDataMap() {
		return dataMap;
	}
	public void setDataMap(List<HashMap<String, String>> dataMap) {
		this.dataMap = dataMap;
	}
	private  List<HashMap<String, String>> dataMap;

}
