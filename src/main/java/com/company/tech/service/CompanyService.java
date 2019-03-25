package com.company.tech.service;

import java.util.HashMap;
import java.util.List;

import com.company.tech.domain.Company;
import com.company.tech.request.CompanyRequest;


public interface CompanyService {
	
	public HashMap<String, String> addNewCompany(CompanyRequest companyRequest);
	public HashMap<String, String> updateCompany( int id , CompanyRequest companyRequest);
	public List<Company> getCompanies();
}
