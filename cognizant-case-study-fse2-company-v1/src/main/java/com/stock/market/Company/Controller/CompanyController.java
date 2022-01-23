package com.stock.market.Company.Controller;

import com.stock.market.Company.Model.Company;
import com.stock.market.Company.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/company/register")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) 
    {
    	UUID randomUUID = UUID.randomUUID();

    	int maxId = companyService.getAllCompanies()
    			.stream()
                .max(Comparator.comparing(Company::getCompanyId))
                .get()
                .getCompanyId();
    	
    	if(maxId==0)
    	{ 	
    		company.setCompanyId(1);
        	company.setCompanyCode(randomUUID);   
        	Company addnewCompany = companyService.addCompany(company);
            System.out.println("Company added "+addnewCompany);
            return new ResponseEntity<>(addnewCompany, HttpStatus.OK); 
    	}
    	else {
        	company.setCompanyId(maxId+1);
        	company.setCompanyCode(randomUUID);   
        	Company addotherCompany = companyService.addCompany(company);
        	System.out.println("Company added "+addotherCompany);
            return new ResponseEntity<>(addotherCompany, HttpStatus.OK);
    	}
    }
    
    @DeleteMapping("/company/delete/{id}")
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
