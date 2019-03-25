package com.company.tech.contoller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.tech.dao.CompanyDao;
import com.company.tech.domain.Company;
import com.company.tech.domain.Image;
import com.company.tech.request.CompanyRequest;
import com.company.tech.response.TechResponse;
import com.company.tech.service.CompanyService;

@RestController
@RequestMapping(value = "/tech/companies")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	CompanyDao companyDao;
	
	Log logger = LogFactory.getLog(CompanyController.class);
	
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
	
	@RequestMapping(method = RequestMethod.GET)
	public TechResponse getAllCompany() {
		
		List<Company> companies = companyService.getCompanies();
		
		TechResponse techResponse = new TechResponse();
		techResponse.setResponseCode("200");
		techResponse.setResponseObject(companies);
		
		return techResponse;
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/getInactive")
	public TechResponse getInactiveCompany() {
		
		List<Company> companies = companyService.getInactiveCompanyList();
		
		TechResponse techResponse = new TechResponse();
		techResponse.setResponseCode("200");
		techResponse.setResponseObject(companies);
		
		return techResponse;
		
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/{companyId}/deleteCompany")
	public TechResponse deleteCompany(@PathVariable("companyId") int companyId) {
		
		HashMap<String, String> hashMap = companyService.deleteCompany(companyId);
		
		TechResponse techResponse = new TechResponse();
		techResponse.setResponseCode("200");
		techResponse.setResponseObject(hashMap);
		
		return techResponse;
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/uploadImage")
	@Transactional
	public TechResponse uploadImage() {
		
		File path = new File("C:\\Users\\SachithT.LOITL-ITSE39\\Pictures\\download.jpg");
		byte[] logo;
		
		try {
			logo = Base64.decodeBase64(path.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		
		Image image1 = new Image();
		image1.setPicture(logo);
		
		Image image = companyDao.uploadImage(image1);
		
		TechResponse techResponse = new TechResponse();
		techResponse.setResponseCode("200");
		techResponse.setResponseObject(image);
		
		return techResponse;
	}
}
