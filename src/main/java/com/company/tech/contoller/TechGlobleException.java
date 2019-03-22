package com.company.tech.contoller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.company.tech.exception.TechError;
import com.company.tech.exception.TechException;
import com.company.tech.response.TechResponse;

@ControllerAdvice
@RestController
public class TechGlobleException {
	
	Log logger = LogFactory.getLog(TechGlobleException.class);
	
	@ExceptionHandler(value = TechException.class)
	public TechResponse handleGlobleException(TechException techException) {
		
		TechError techError = new TechError();
		techError.setCode(techException.getErrorCode());
		techError.setMessage(techException.getErrorMessage());
		
		TechResponse techResponse = new TechResponse();
		techResponse.setResponseCode("000");
		techResponse.setResponseObject(techError);
		
		return techResponse;
	}
}
