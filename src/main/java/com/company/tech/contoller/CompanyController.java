package com.company.tech.contoller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.tech.request.CompanyRequest;
import com.company.tech.response.TechResponse;
import com.company.tech.service.CompanyService;

@RestController
@RequestMapping(value = "/tech/companies")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@RequestMapping(method = RequestMethod.POST , value = "/create")
	public TechResponse addCompany(@RequestBody CompanyRequest companyRequest) {
		
		HashMap<String, String> hashMap  = companyService.addNewCompany(companyRequest);
		
		TechResponse techResponse = new TechResponse();
		techResponse.setResponseCode("201");
		techResponse.setResponseObject(hashMap);
		
		return techResponse;
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/{id}/update")
	public TechResponse updateCompany(@PathVariable("id") int id , @RequestBody CompanyRequest companyRequest) {
		
		HashMap<String, String> hashMap = companyService.updateCompany(id, companyRequest);
		
		TechResponse techResponse = new TechResponse();
		techResponse.setResponseCode("204");
		techResponse.setResponseObject(hashMap);
		
		return techResponse;
	}
}
