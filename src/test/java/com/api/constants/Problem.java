package com.api.constants;

public enum Problem {
	
	 SMARTPHONE_IS_RUNNING_SLOW(1),  POOR_BATTERY_LIFE(2),  PHONE_OR_APP_CRASHES(3),  SYNC_ISSUE(4), 
	 MICROSD_CARD_IS_NOT_WORKING_ON_YOUR_PHONE(5),  OVERHEATING(6),  
	 CONNECTING_PROBLEM_WITH_BLUETOOTH_WIFI_CELLULAR_NETWORK(7),
	 CRACKED_SCREEN(8),  OTHER(9),  CAMERA_ISSUE(10), 
	 CHARGER_NOT_WORKING(11),   SOFTWARE_BOOTING_ISSUE(12);
	 
	 private int code; 
	
	Problem(int code){
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
