package com.gyanblog.gyanmongo.model;

import org.springframework.data.annotation.Id;

public class Employee {

	private String id;
	private Object data;
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
