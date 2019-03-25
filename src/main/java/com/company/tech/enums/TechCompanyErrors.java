package com.company.tech.enums;

public enum TechCompanyErrors {
	
	COMPANY_ALREADY_EXISTS("001","Company already exist. Please try another company name."),
	COMPANY_NOT_CREATED("002","Can not create company"),
	COMPANY_CAN_NOT_FIND("003","Can not find company"),
	COMPANY_CAN_NOT_UPDATE("004","Can not update company"),
	ANY_COMPANY_IS_REGISTERED_NOT_YET("005","Any company not yet registered ");
	
	private String statusCode;
	private String statusMessage;
	
	private TechCompanyErrors(String statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	
}
