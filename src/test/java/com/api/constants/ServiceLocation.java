package com.api.constants;

public enum ServiceLocation {

	SERVICE_LOCATION_A(1),
	SERVICE_LOCATION_B(2),
	SERVICE_LOCATION_C(3);
	
	private int code;
	
	ServiceLocation(int code){
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
