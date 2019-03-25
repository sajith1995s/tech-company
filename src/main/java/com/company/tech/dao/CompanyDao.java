package com.company.tech.dao;

import java.util.List;

import com.company.tech.domain.Company;
import com.company.tech.domain.Image;

public interface CompanyDao {
	
	public Company addNewCompany(Company company);
	public Company findCompanyByName(String companyName);
	public Company updateCompany(Company company);
	public Company findCompanyById(int id);
	public List<Company> getCompanies();
	public List<Company> getInactiveCompanies();
	public Image uploadImage(Image image);
}
