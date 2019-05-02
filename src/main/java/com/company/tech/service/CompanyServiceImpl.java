package com.company.tech.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.plaf.basic.BasicTreeUI.TreeHomeAction;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.tech.dao.CompanyDao;
import com.company.tech.domain.Company;
import com.company.tech.enums.CompanyStatus;
import com.company.tech.enums.TechCompanyErrors;
import com.company.tech.exception.TechException;
import com.company.tech.request.CompanyRequest;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	CompanyDao companyDao;
	
	@Transactional
	@Override
	public HashMap<String, String> addNewCompany(CompanyRequest companyRequest) {
		
		Company newCompany = new Company();
		newCompany.setName(companyRequest.getCompanyName());
		newCompany.setAddress(companyRequest.getAddress());
		newCompany.setContactNumber(companyRequest.getContactNumber());
		newCompany.setWebsite(companyRequest.getWebsite());
		newCompany.setStatus(CompanyStatus.ACTIVE.getStatus());
		
		if(companyDao.findCompanyByName(companyRequest.getCompanyName()) == null) {
			Company company = companyDao.addNewCompany(newCompany);
			HashMap<String, String> result = new HashMap<>();
			
			if(company != null) {
				result.put("message", "company created");
				return result;
			}
			else {
				throw new TechException(TechCompanyErrors.COMPANY_NOT_CREATED);
			}
		}
		else {
			throw new TechException(TechCompanyErrors.COMPANY_ALREADY_EXISTS);
		}
	}
	
	@Transactional
	@Override
	public HashMap<String, String> updateCompany(int id , CompanyRequest companyRequest) {
		
		if(companyDao.findCompanyById(id) != null) {
			
			Company company = companyDao.findCompanyById(id);
			company.setName(companyRequest.getCompanyName());
			company.setAddress(companyRequest.getAddress());
			company.setContactNumber(companyRequest.getContactNumber());
			company.setWebsite(companyRequest.getWebsite());
			Company updated = companyDao.updateCompany(company);
			if(updated != null) {
				HashMap<String, String> hashMap = new HashMap<>();
				hashMap.put("message", "Company Updated");
				
				return hashMap;
			}else {
				throw new TechException(TechCompanyErrors.COMPANY_CAN_NOT_UPDATE);
			}
			
			
		}else {
			throw new TechException(TechCompanyErrors.COMPANY_CAN_NOT_FIND);
		}
		
	}

	@Override
	public List<Company> getCompanies() {
		
		List<Company> companies = companyDao.getCompanies();
		
		if(companies != null && !companies.isEmpty()) {
			
			List<Company> companiesList = new ArrayList<>();
			
			for(Company company : companies) {
				if(company.getStatus().equalsIgnoreCase("ACTIVE")) {
					Company activeCompany = new Company();
					
					activeCompany.setId(company.getId());
					activeCompany.setName(company.getName());
					activeCompany.setAddress(company.getAddress());
					activeCompany.setStatus(company.getStatus());
					activeCompany.setWebsite(company.getWebsite());
					activeCompany.setContactNumber(company.getContactNumber());
					
					companiesList.add(activeCompany);
				}
			}
			
			return companiesList;
		}
		else {
			throw new TechException(TechCompanyErrors.ANY_COMPANY_IS_REGISTERED_NOT_YET);
		}
	}

	@Override
	public List<Company> getInactiveCompanyList() {
		
		List<Company> companies = companyDao.getInactiveCompanies();
		
		if(companies != null && !companies.isEmpty()) {
			return companies;
		}
		else {
			throw new TechException(TechCompanyErrors.CAN_NOT_FIND_INACTIVATE_COMPANY);
		}
		
	}

	@Override
	@Transactional
	public HashMap<String, String> deleteCompany(int companyId) {
		
		Company company = companyDao.findCompanyById(companyId);
		
		if(company != null && company.getStatus().equalsIgnoreCase("ACTIVE")) {
			
			company.setStatus(CompanyStatus.IN_ACTIVE.getStatus());
			
			Company deletedCompany = companyDao.updateCompany(company);
			HashMap<String, String> hashMap = new HashMap<>();
			
			if(deletedCompany != null) {
				hashMap.put("message", "Company Deleted Succesully");
				
				return hashMap;
			}
			else {
				throw new TechException(TechCompanyErrors.COMPANY_DELETE_UNSUCCESS);
			}
		}
		else {
			throw new TechException(TechCompanyErrors.COMPANY_CAN_NOT_FIND);
		}
		
	}

}
