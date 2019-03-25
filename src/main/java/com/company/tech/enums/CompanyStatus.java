package com.company.tech.enums;

public enum CompanyStatus {
	
	ACTIVE("ACTIVE"),
	IN_ACTIVE("INACTIVE");
	
	private String status;

	private CompanyStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

}
