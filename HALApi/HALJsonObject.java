package com.cothing.element.HALApi;

import java.util.List;

public class HALJsonObject {
	private String href;
	private List<HALJsonObject> list;
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public List<HALJsonObject> getList() {
		return list;
	}
	public void setList(List<HALJsonObject> list) {
		this.list = list;
	}
	
	
	
	

}
