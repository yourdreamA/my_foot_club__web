package com.abd.mfc.vo;

public enum PaginationEnum {

	FIRST("f"), PREVIOUS("p"), NEXT("n"), LAST("l");
	
	private String code;

	private PaginationEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
