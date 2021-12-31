package com.stock.market.Company.Controller;

import com.stock.market.Company.Model.Company;
import com.stock.market.Company.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/register")
    public Company addCompany(@RequestBody Company company) {
        System.out.println("Added Company from Spring Boot - "+company);
        return companyService.addCompany(company);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCompany(@PathVariable int id){
        companyService.deleteCompany(id);
        return "Company with id - "+id+" deleted";
    }

    @GetMapping("getCompany/{id}")
    public Optional<Company> getCompanyById(@PathVariable int id){
        return companyService.getCompanyById(id);
    }

    @GetMapping("/getCompanies")
    public List<Company> getAllCompanies()
    {
        List<Company> companies = companyService.getAllCompanies();
        companies.forEach(System.out::println);
        return companies;
    }
}
