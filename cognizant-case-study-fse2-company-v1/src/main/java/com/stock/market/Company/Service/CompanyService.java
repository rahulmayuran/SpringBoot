package com.stock.market.Company.Service;

import com.stock.market.Company.Model.Company;
import com.stock.market.Company.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public void deleteCompany(int id){
        System.out.println("Company with id - "+id+" deleted");
        companyRepository.deleteById(id);
        return;
    }

    public Optional<Company> getCompanyById(int id){
        return companyRepository.findById(id);
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }


}
