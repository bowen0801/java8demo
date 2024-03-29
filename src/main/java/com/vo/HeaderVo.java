package com.vo;

import java.io.Serializable;

public class HeaderVo implements Serializable{
	/**
	 * @dell 
	 */
	private static final long serialVersionUID = -8038655560302326675L;
	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public HeaderVo(String key, String value) {
		this.key = key;
		this.value = value;
	}

}
