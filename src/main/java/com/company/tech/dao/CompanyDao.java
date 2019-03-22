package com.company.tech.dao;

import java.util.List;

import com.company.tech.domain.Company;

public interface CompanyDao {
	
	public Company addNewCompany(Company company);
	public Company findCompanyByName(String companyName);
	public Company updateCompany(Company company);
	public Company findCompanyById(int id);
	public List<Company> getCompanies();
}
