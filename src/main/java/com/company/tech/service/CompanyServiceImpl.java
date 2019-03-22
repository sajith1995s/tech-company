package com.company.tech.service;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.tech.dao.CompanyDao;
import com.company.tech.domain.Company;
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
		newCompany.setCity(companyRequest.getCity());
		newCompany.setAddress(companyRequest.getAddress());
		
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
			
			Company updateCompany = new Company();
			updateCompany.setName(companyRequest.getCompanyName());
			updateCompany.setCity(companyRequest.getCity());
			updateCompany.setAddress(companyRequest.getAddress());
			updateCompany.setId(id);
			
			Company updated = companyDao.updateCompany(updateCompany);
			if(updated != null) {
				HashMap<String, String> hashMap = new HashMap<>();
				hashMap.put("message", "Company Uupdated");
				
				return hashMap;
			}else {
				throw new TechException(TechCompanyErrors.COMPANY_CAN_NOT_UPDATE);
			}
			
			
		}else {
			throw new TechException(TechCompanyErrors.COMPANY_CAN_NOT_FIND);
		}
		
	}

}
