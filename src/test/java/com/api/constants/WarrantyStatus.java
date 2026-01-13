package com.api.constants;

public enum WarrantyStatus {
	IN_WARRANTY_STATUS(1), OUT_WARRANTY_STATUS(2);
	
	private int code;
	
	WarrantyStatus(int code){
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
