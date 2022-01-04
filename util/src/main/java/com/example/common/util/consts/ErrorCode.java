package com.example.common.util.consts;

/**
 */
public enum ErrorCode {
	
	SUCCESS("0","ok"),
	ERROR("-1", "error"),
	
	COMMON_01("common_01","方法不支持"),
	COMMON_02("common_02","未指定错误"),
	COMMON_03("common_03","参数错误");
	
	
	
	private String code;
	private String value;

	ErrorCode(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getCode() {
		return code;
	}
}
